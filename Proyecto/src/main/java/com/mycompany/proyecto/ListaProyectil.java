/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
public class ListaProyectil {
    private NodoProyectil cabeza;

    public void agregar(Proyectil p) {
        NodoProyectil nuevo = new NodoProyectil(p);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoProyectil actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    // Elimina proyectiles que hayan llegado al final del tablero
    public void limpiarProyectilesInactivos(int limiteTablero) {
        while (cabeza != null && cabeza.valor.getPosicion() > limiteTablero) {
            cabeza = cabeza.siguiente;
        }

        NodoProyectil actual = cabeza;
        while (actual != null && actual.siguiente != null) {
            if (actual.siguiente.valor.getPosicion() > limiteTablero) {
                actual.siguiente = actual.siguiente.siguiente;
            } else {
                actual = actual.siguiente;
            }
        }
    }

    public NodoProyectil getCabeza() {
        return cabeza;
    }
}
