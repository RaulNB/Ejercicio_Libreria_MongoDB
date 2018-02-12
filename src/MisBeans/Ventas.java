/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Alumno
 */
public class Ventas implements Serializable, PropertyChangeListener {

    private int numeroVenta;
    private int idProducto;
    private Date fechaVenta;
    private int cantidad;
    private String observaciones;

    public Ventas() {
    }

    public Ventas(int numeroVenta, int idProducto, Date fechaVenta, int cantidad, String observaciones) {
        this.numeroVenta = numeroVenta;
        this.idProducto = idProducto;
        this.fechaVenta = fechaVenta;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
    }

    public int getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(int numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        System.out.println("Pendiente de pedido por falta de stock");
    }
    
}