package com.mycompany.proyecto;

public class ColaZombies {

    NodoZombie frente;
    private NodoZombie fin;

    public ColaZombies() {
        frente = null;
        fin = null;
    }

    public void agregar(AlienZombie z) {
        NodoZombie nuevo = new NodoZombie(z);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public AlienZombie sacar() {
        if (frente == null) {
            return null;
        }
        AlienZombie valor = frente.valor;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return valor;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}
