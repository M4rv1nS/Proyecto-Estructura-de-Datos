/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */



public class ColaZombies {
    private NodoZombie frente;
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
        if (frente == null) return null;
        AlienZombie valor = frente.valor;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return valor;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}

