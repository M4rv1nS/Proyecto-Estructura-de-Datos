/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
// lista enlazada personalizada para proyectiles
public class ListaProyectil {
    private NodoProyectil cabeza;

    // agrega un proyectil al final de la lista
    public void agregar(Proyectil proyectil) {
        NodoProyectil nuevo = new NodoProyectil(proyectil);
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

    // elimina un nodo especifico de la lista
    public void eliminar(NodoProyectil nodoEliminar) {
        if (cabeza == null || nodoEliminar == null) return;

        if (cabeza == nodoEliminar) {
            cabeza = cabeza.siguiente;
            return;
        }

        NodoProyectil actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente == nodoEliminar) {
                actual.siguiente = actual.siguiente.siguiente;
                return;
            }
            actual = actual.siguiente;
        }
    }

    // devuelve el primer nodo de la lista
    public NodoProyectil getCabeza() {
        return cabeza;
    }
}
