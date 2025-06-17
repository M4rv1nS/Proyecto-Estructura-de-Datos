/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
// Clase base para cualquier tipo de tropa (planta o zombie)
public abstract class Tropa {
    protected int vida;
    protected int ataque;
    protected int fila;
    protected int columna;

    public boolean estaViva() {
        return vida > 0;
    }

    public void recibirDanio(int cantidad) {
        vida -= cantidad;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }

    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public abstract void actuar();
}
//Hola

