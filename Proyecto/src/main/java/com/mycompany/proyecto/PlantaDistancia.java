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
        setPosicion(fila, columna);

    }

    public boolean puedeDisparar() {
        return !proyectilActivo;
    }

    public void disparar(ListaProyectil lista) {
        if (puedeDisparar()) {
            lista.agregar(new Proyectil(ataque));
            proyectilActivo = true;
        }
    }

    public void proyectilDestruido() {
        proyectilActivo = false;
    }

    @Override
    public void actuar() {
        // Implementación más específica en contexto del juego
    }
}

