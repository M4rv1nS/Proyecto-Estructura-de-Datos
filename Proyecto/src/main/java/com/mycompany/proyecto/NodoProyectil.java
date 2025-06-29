/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
// nodo para la lista de proyectiles
public class NodoProyectil {
    public Proyectil valor;
    public NodoProyectil siguiente;

    public NodoProyectil(Proyectil valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}
