/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
public class NodoTropa {
// Clase Nodo para lista enlazada de tropas (plantas o zombies)
    public Tropa valor;// Referencia a la tropa (plantas o zombies)
    public NodoTropa siguiente; // Enlace al siguiente nodo

    public NodoTropa(Tropa valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}
