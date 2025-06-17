/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
public class Casilla {
    public Tropa tropa; // Puede ser planta o zombie
    public boolean esZombie; // True si es enemigo, false si es planta

    public Casilla() {
        this.tropa = null;
        this.esZombie = false;
    }

    public boolean estaOcupada() {
        return tropa != null;
    }
}

