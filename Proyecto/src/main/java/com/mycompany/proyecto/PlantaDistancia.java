/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
// Planta que lanza proyectiles en línea recta
public class PlantaDistancia extends Tropa {
    private boolean proyectilActivo;
    

    public PlantaDistancia() {
        this.vida = 5;
        this.ataque = 2;
        this.proyectilActivo = false;
        this.fila=-1;
        this.columna = -1;

    }

    public boolean puedeDisparar() {
        return !proyectilActivo;
    }

    public void disparar(ListaProyectil lista) {
            proyectilActivo = true;
        }
    
    public void proyectilDestruido() {
        proyectilActivo = false;
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

    @Override
    public void actuar() {
        // La acción (disparo) se ejecuta desde GameEngine
    }
}