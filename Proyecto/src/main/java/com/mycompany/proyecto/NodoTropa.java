package com.mycompany.proyecto;

public class NodoTropa {
// Clase Nodo para lista enlazada de tropas (plantas o zombies)
    public Tropa valor;// Referencia a la tropa (plantas o zombies)
    public NodoTropa siguiente; // Enlace al siguiente nodo

    public NodoTropa(Tropa valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}
