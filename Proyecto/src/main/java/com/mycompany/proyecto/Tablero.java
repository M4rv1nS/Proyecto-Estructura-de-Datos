/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
public class Tablero {
    private final int filas = 5;
    private final int columnas = 9;
    private Casilla[][] casillas;

    public Tablero() {
        casillas = new Casilla[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    public Casilla obtenerCasilla(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return casillas[fila][columna];
        }
        return null;
    }

    public boolean colocarTropa(int fila, int columna, Tropa tropa, boolean esZombie) {
        Casilla c = obtenerCasilla(fila, columna);
        if (c != null && !c.estaOcupada()) {
            c.tropa = tropa;
            c.esZombie = esZombie;
            return true;
        }
        return false;
    }

    public void eliminarTropa(int fila, int columna) {
        Casilla c = obtenerCasilla(fila, columna);
        if (c != null) {
            c.tropa = null;
            c.esZombie = false;
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    void colocarTropa(int fila, int i, AlienZombie z, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
