package com.carta.dx.menucocina;

import java.util.ArrayList;

/**
 * Created by Dx on 02/04/2016.
 */
public class Pedido {
    private ArrayList<Platos> Entradas;
    private ArrayList<Platos> PlatoFuerte;
    private ArrayList<Platos> bebidas;
    private ArrayList<Platos> postres;

    private int valor;

    public ArrayList<Platos> getEntradas() {
        return Entradas;
    }

    public void setEntradas(ArrayList<Platos> entradas) {
        Entradas = entradas;
    }

    public ArrayList<Platos> getPlatoFuerte() {
        return PlatoFuerte;
    }

    public void setPlatoFuerte(ArrayList<Platos> platoFuerte) {
        PlatoFuerte = platoFuerte;
    }

    public ArrayList<Platos> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Platos> bebidas) {
        this.bebidas = bebidas;
    }

    public ArrayList<Platos> getPostres() {
        return postres;
    }

    public void setPostres(ArrayList<Platos> postres) {
        this.postres = postres;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Pedido() {
    }

    public Pedido(ArrayList<Platos> entradas, ArrayList<Platos> platoFuerte, ArrayList<Platos> bebidas, ArrayList<Platos> postres, int valor) {
        Entradas = entradas;
        PlatoFuerte = platoFuerte;
        this.bebidas = bebidas;
        this.postres = postres;
        this.valor = valor;
    }


}


