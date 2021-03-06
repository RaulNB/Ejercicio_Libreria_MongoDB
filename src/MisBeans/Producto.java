/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Alumno
 */
public class Producto implements Serializable {
        
    private String descripcion;
    private int idProducto;
    private int stockActual;
    private int stockMinimo;
    private float pvp;
    
    private PropertyChangeSupport propertySupport;
    
    public Producto() {
        propertySupport = new PropertyChangeSupport(this);
    }

    public Producto(String descripcion, int idProducto, int stockActual, int stockMinimo, float pvp) {
        this.descripcion = descripcion;
        this.idProducto = idProducto;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.pvp = pvp;
        
        propertySupport = new PropertyChangeSupport(this);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        int valorAnterior = this.stockActual;
        this.stockActual = stockActual;
        
        if(this.stockActual < this.getStockMinimo()) {
            propertySupport.firePropertyChange("stockActual", valorAnterior, this.stockActual);
            this.stockActual = valorAnterior;
        }
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}
