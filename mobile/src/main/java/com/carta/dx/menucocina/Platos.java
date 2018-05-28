package com.carta.dx.menucocina;

/**
 * Created by jennyandrea on 9/11/2016.
 */

public class Platos {
    String tipo;
    int cantidad;
    int precio;
    String producto;



    public Platos(String tipo, int cantidad, int precio, String producto) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto=producto;
    }

    public Platos() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}


