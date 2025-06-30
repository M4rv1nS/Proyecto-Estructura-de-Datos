package com.mycompany.proyecto;

public class PlantaDistancia extends Tropa {

    private int ultimoTurnoDisparo;

    public PlantaDistancia() {
        this.vida = 4;
        this.ataque = 2;
        this.ultimoTurnoDisparo = -2;
    }

    public boolean puedeDisparar(int turnoActual) {
        return (turnoActual - ultimoTurnoDisparo) >= 2;
    }

    public void disparar(int turnoActual) {
        this.ultimoTurnoDisparo = turnoActual;
    }

    @Override
    public void actuar() {
    }
}
