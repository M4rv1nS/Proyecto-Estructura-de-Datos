/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
// clase base para cualquier tipo de tropa (planta o zombie)
public abstract class Tropa {
    protected int vida;
    protected int ataque;
    protected int fila;
    protected int columna;

    // indica si la tropa esta viva
    public boolean estaViva() {
        return vida > 0;
    }

    // metodo para recibir danio y reducir vida
    public void recibirDanio(int cantidad) {
        vida -= cantidad;
    }

    // devuelve la cantidad de vida actual
    public int getVida() {
        return vida;
    }

    // devuelve el poder de ataque
    public int getAtaque() {
        return ataque;
    }

    // devuelve la fila donde esta la tropa
    public int getFila() {
        return fila;
    }

    // devuelve la columna donde esta la tropa
    public int getColumna() {
        return columna;
    }

    // asigna la posicion de la tropa
    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    // metodo abstracto que las subclases deben implementar
    public abstract void actuar();
}
