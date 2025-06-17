/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */

// Lista enlazada específica para manejar tropas en el tablero
public class ListaTropa {
    private NodoTropa cabeza;

    public ListaTropa() {
        cabeza = null;
    }

    // Inserta una tropa al final de la lista
    public void agregar(Tropa valor) {
        NodoTropa nuevo = new NodoTropa(valor);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoTropa actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    // Elimina una tropa específica
    public void eliminar(Tropa valor) {
        if (cabeza == null) return;
        if (cabeza.valor == valor) {
            cabeza = cabeza.siguiente;
            return;
        }
        NodoTropa actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.valor != valor) {
            actual = actual.siguiente;
        }
            if (actual.siguiente != null) {
                actual.siguiente = actual.siguiente.siguiente;
        }
    }

    // Acceso externo a la cabeza (para recorrer)
    public NodoTropa getCabeza() {
        return cabeza;
    }

    void agregar(AlienZombie nuevo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}



    

