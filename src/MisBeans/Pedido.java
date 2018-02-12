/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Alumno
 */
public class Pedido implements Serializable, PropertyChangeListener {
    
    private int numeroPedido;
    private Producto producto;
    private Date fecha;
    private int cantidad;

    public Pedido() {
    }

    public Pedido(int numeroPedido, Producto producto, Date fecha, int cantidad) {
        this.numeroPedido = numeroPedido;
        this.producto = producto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        System.out.println("Stock anterior: " + pce.getOldValue());
        System.out.println("Stock actual: " + pce.getNewValue());
        System.out.println("REALIZAR PEDIDO EN PRODUCTO: " + producto.getDescripcion());
    }

    
}
