/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import java.util.ArrayList;

/**
 *
 * @author Alumno
 */
public class Prueba {
    public static void main(String[] args){
        BaseDatos bd = new BaseDatos();
        
        bd.inicializarBaseDatosMiBaseDatos();
        
        Producto prod = new Producto("prueba", 0, 10, 1, 200);
        
        bd.insertaProducto(prod);
        bd.insertaPedido(prod, 50);
        bd.insertarVenta(prod, 5, "ventaaa");
        
        ArrayList<Producto> array = bd.datosProductos();
        
        System.out.println(array.size());
    }
}
