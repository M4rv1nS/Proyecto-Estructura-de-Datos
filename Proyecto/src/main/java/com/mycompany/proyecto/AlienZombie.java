/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
public class AlienZombie extends Tropa {

    private int recompensa;

    public AlienZombie() {
        this.vida = 6;
        this.ataque = 3;
        this.recompensa = 300;
        this.fila = -1;
        this.columna = -1;
    }

    public int getRecompensa() {
        return recompensa;
    }

    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void avanzar() {
        this.columna -= 1;
    }

    @Override
    public void actuar() {
        // Su comportamiento está manejado desde GameEngine.
    }

    public void recibirDanio(int cantidad) {
        this.vida -= cantidad;
    }

    public int getVida() {
        return this.vida;
    }

    public int getAtaque() {
        return this.ataque;
    }
}
