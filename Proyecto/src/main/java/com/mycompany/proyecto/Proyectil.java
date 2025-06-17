/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
public class Proyectil {
    private int dano;
    private int posicion; // columna actual en el tablero (izq -> der)
    private int carril;   // fila o carril en el que se mueve

    public Proyectil(int dano, int carril) {
        this.dano = dano;
        this.carril = carril;
        this.posicion = 0; // inicia desde la izquierda
    }

    Proyectil(int ataque) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void avanzar() {
        posicion++;
    }

    public int getDano() {
        return dano;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getCarril() {
        return carril;
    }
}


