package com.mycompany.proyecto;

// nodo para la lista de proyectiles
public class NodoProyectil {
    public Proyectil valor;
    public NodoProyectil siguiente;

    public NodoProyectil(Proyectil valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}
