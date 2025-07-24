package com.mycompany.proyecto;

public class NodoZombie {

    public AlienZombie valor;
    public NodoZombie siguiente;

    public int fila;
    public int columna;

    public NodoZombie(AlienZombie valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}
