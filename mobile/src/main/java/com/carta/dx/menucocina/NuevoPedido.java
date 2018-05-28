package com.carta.dx.menucocina;

import java.sql.Time;

/**
 * Created by jennyandrea on 9/11/2016.
 */

public class NuevoPedido {

    private String idPedido;
    private String idEmpleado;
    private String precio;
    private String duracion;
    private String numMesa;

    public NuevoPedido() {
    }

    public NuevoPedido(String idPedido, String idEmpleado, String precio, String duracion, String numMesa) {
        this.idPedido = idPedido;
        this.idEmpleado = idEmpleado;
        this.precio = precio;
        this.duracion = duracion;
        this.numMesa = numMesa;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(String numMesa) {
        this.numMesa = numMesa;
    }
}

