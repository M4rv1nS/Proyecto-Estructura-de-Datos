package com.mycompany.proyecto;

public class Proyectil {

    private int dano;
    private int carril;
    private int posicion;

    public Proyectil(int dano, int carril, int posicion) {
        this.dano = dano;
        this.carril = carril;
        this.posicion = posicion;
    }

    public void avanzar() {
        posicion++;
    }

    public int getDano() {
        return dano;
    }

    public int getCarril() {
        return carril;
    }

    public int getPosicion() {
        return posicion;
    }
}
