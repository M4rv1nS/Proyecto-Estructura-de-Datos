package com.mycompany.proyecto;

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

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public abstract void actuar();
}
