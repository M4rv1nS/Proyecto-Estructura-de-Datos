/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
public class NodoZombie {
    public AlienZombie valor;
    public NodoZombie siguiente;

    public NodoZombie(AlienZombie valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}