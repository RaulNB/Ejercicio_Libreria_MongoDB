/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.descending;
import java.util.ArrayList;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

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
            producto.setPvp(Float.parseFloat(doc.getString("pvp")));
            producto.setStockActual(doc.getInteger("stockActual"));
            producto.setStockMinimo(doc.getInteger("stockMinimo"));
        }
        
        return producto;
    }
    
    public static int obtenerNumeroProducto(){
        int numId = 0;
        Document doc = (Document) db.getCollection("Producto").find().sort(descending("id")).first();
        
        if (!doc.isEmpty()){
            numId = doc.getInteger("idProducto") + 1;
        }
        
        return numId;
    }
    
    public static int obtenerNumeroPedido(){
        //En este método se devuelve el numero del siguiente pedido a insertar
        return 0;
    }
    
    public static int obtenerNumeroVenta(){
        //En este método se devuelve el numero de la siguiente venta a insertar
        return 0;
    }
    
    public boolean insertaProducto(Producto producto){
        
        return true; //Si todo correcto
    }
    
    public boolean insertaPedido(Producto producto, int cantidad){
        
        return true; //Si todo correcto
    }
    
    public boolean insertarVenta(Producto producto, int cantidad, String observaciones){
        return insertarVenta(producto.getIdProducto(), cantidad, observaciones);
    }
    
    public boolean insertarVenta(int idProducto, int cantidad, String observaciones){
        
        return true; //Si todo correcto
    }
    
    private static java.sql.Date getFechaActual(){
        return new java.sql.Date(new java.util.Date().getTime());
    }
    
    private void actualizarStock(Producto producto, int cantidad){
        
    }
    
    public ArrayList<Producto> datosProductos(){
        return null;
    }
    
    public Producto convertirDocumentoProducto(Document docu){
        //Le cargamos las etiquetas BSON a los atributos de la clase Productos
        return null;
    }
}
