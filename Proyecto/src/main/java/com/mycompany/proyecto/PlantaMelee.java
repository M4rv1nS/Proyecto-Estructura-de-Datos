/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
public class PlantaMelee extends Tropa {

    public PlantaMelee() {
        this.vida = 7;
        this.ataque = 3;
        this.fila = -1;
        this.columna = -1;
    }

    @Override
    public void actuar() {
        // Su comportamiento se gestiona desde GameEngine
    }

    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return this.fila;
    }

    public int getColumna() {
        return this.columna;
    }

    public int getAtaque() {
        return this.ataque;
    }

    public int getVida() {
        return this.vida;
    }

    public void recibirDanio(int cantidad) {
        this.vida -= cantidad;
    }
}
