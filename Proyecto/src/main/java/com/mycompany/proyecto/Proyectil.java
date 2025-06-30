package com.mycompany.proyecto;

public class Proyectil {

    private int dano;
    private int posicion;
    private int carril;

    public Proyectil(int dano, int carril, int columnaInicial) {
        this.dano = dano;
        this.carril = carril;
        this.posicion = columnaInicial + 1;
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
