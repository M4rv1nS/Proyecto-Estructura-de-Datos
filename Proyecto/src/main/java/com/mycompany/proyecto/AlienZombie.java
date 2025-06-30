package com.mycompany.proyecto;

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

    @Override
    public void actuar() {
    }
}
