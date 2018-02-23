/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.descending;
import static com.mongodb.client.model.Updates.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.bson.Document;

/**
 *
 * @author Alumno
 */
public class BaseDatos {
    static MongoClient cliente;
    static MongoDatabase db;

    public BaseDatos() {
        cliente = new MongoClient();
        db = cliente.getDatabase("mibasedatos");
    }
    
    public static MongoClient getClient(){
        return cliente;
    }
    
    public static MongoDatabase getDb(){
        return db;
    }
    
    public static Producto obtenerProductoBD(int idproducto){
        Producto producto = null;
        Document doc = (Document) db.getCollection("Producto").find(eq("idProducto", idproducto)).first();
        
        if(!doc.isEmpty()){
            producto = new Producto();
            producto.setDescripcion(doc.getString("descripcion"));
            producto.setIdProducto(doc.getInteger("idProducto"));
            producto.setPvp(Float.parseFloat(doc.get("pvp").toString()));
            producto.setStockActual(doc.getInteger("stockActual"));
            producto.setStockMinimo(doc.getInteger("stockMinimo"));
        }
        
        return producto;
    }
    
    public static int obtenerNumeroProducto(){
        Document doc = (Document) db.getCollection("Producto").find().sort(descending("idProducto")).first();
        
        if (doc != null){
           return doc.getInteger("idProducto") + 1;
        }
        
        return 0;
    }
    
    public static int obtenerNumeroPedido(){
        Document doc = (Document) db.getCollection("Pedido").find().sort(descending("numeroPedido")).first();
        
        if (doc != null){
            return doc.getInteger("numeroPedido") + 1;
        }
        
        return 0;
    }
    
    public static int obtenerNumeroVenta(){
        Document doc = (Document) db.getCollection("Ventas").find().sort(descending("numeroVenta")).first();
        
        if (doc != null){
            return doc.getInteger("numeroVenta") + 1;
        }
        
        return 0;
    }
    
    public boolean insertaProducto(Producto producto){
        try{
            MongoCollection<Document> collect = db.getCollection("Producto");
            Document docProd = new Document();

            docProd.append("idProducto", producto.getIdProducto())
                    .append("descripcion", producto.getDescripcion())
                    .append("stockActual", producto.getStockActual())
                    .append("stockMinimo", producto.getStockMinimo())
                    .append("pvp", producto.getPvp());

            collect.insertOne(docProd);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean insertaPedido(Producto producto, int cantidad){
        try{
            Producto prod = obtenerProductoBD(producto.getIdProducto());
            
            MongoCollection<Document> collect = db.getCollection("Pedido");
            Document docPed = new Document();
            
            docPed.append("numeroPedido", obtenerNumeroPedido())
                    .append("producto",
                            new Document("idProducto", prod.getIdProducto())
                            .append("descripcion", prod.getDescripcion())
                            .append("stockActual", prod.getStockActual())
                            .append("stockMinimo", prod.getStockMinimo())
                            .append("pvp", prod.getPvp()))
                    .append("fecha", getFechaActual())
                    .append("cantidad", cantidad);

            collect.insertOne(docPed);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean insertarVenta(Producto producto, int cantidad, String observaciones){
        return insertarVenta(producto.getIdProducto(), cantidad, observaciones);
    }
    
    public boolean insertarVenta(int idProducto, int cantidad, String observaciones){
        try{
            MongoCollection<Document> collect = db.getCollection("Ventas");
            Document docVen = new Document();
            
            docVen.append("numeroVenta", obtenerNumeroVenta())
                    .append("idProducto", idProducto)
                    .append("fechaVenta", getFechaActual())
                    .append("cantidad", cantidad)
                    .append("observaciones", observaciones);

            collect.insertOne(docVen);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    private static java.sql.Date getFechaActual(){
        return new java.sql.Date(new java.util.Date().getTime());
    }
    
    private void actualizarStock(Producto producto, int cantidad){
        Producto prod = obtenerProductoBD(producto.getIdProducto());
        
        cantidad += prod.getStockActual();
        
        MongoCollection<Document> docProd = db.getCollection("Producto");
        docProd.updateOne(eq("idProducto", prod.getIdProducto()), set("stockActual", cantidad));
    }
    
    public ArrayList<Producto> datosProductos(){
        MongoCollection<Document> productos = db.getCollection("Producto");
        Iterator iterator = productos.find().iterator();
        ArrayList<Producto> listProds = new ArrayList<>();
        
        while(iterator.hasNext()){
            Document docProd = (Document) iterator.next();
            
            Producto prod = new Producto();
            prod.setIdProducto(docProd.getInteger("idProducto"));
            prod.setDescripcion(docProd.getString("descripcion"));
            prod.setPvp(Float.parseFloat(docProd.getString("pvp")));
            prod.setStockMinimo(docProd.getInteger("stockMinimo"));
            prod.setStockActual(docProd.getInteger("stockActual"));
            
            listProds.add(prod);
        }
        
        return listProds;
    }
    
    public Producto convertirDocumentoProducto(Document docu){
        try{
            Producto prod = new Producto();
            
            prod.setIdProducto(docu.getInteger("idProducto"));
            prod.setIdProducto(docu.getInteger("idProducto"));
            prod.setDescripcion(docu.getString("descripcion"));
            prod.setPvp(Float.parseFloat(docu.getString("pvp")));
            prod.setStockMinimo(docu.getInteger("stockMinimo"));
            prod.setStockActual(docu.getInteger("stockActual"));
            
            return prod;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public boolean inicializarBaseDatosMiBaseDatos(){
        try{
            db.createCollection("Producto");
            db.createCollection("Pedido");
            db.createCollection("Ventas");
            
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean borrarProducto(Producto producto){
        
        return borrarProducto(producto.getIdProducto());
    }
    
    public boolean borrarProducto(int idProducto){
        try{    
            MongoCollection<Document> collect = db.getCollection("Producto");
            collect.deleteOne(eq("idProducto", idProducto));
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
