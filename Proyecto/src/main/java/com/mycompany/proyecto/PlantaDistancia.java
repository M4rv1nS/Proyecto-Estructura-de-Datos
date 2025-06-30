package com.mycompany.proyecto;

public class PlantaDistancia extends Tropa {

    private boolean proyectilActivo;
    private int ultimoDisparo;

    public PlantaDistancia() {
        this.vida = 5;
        this.ataque = 2;
        this.proyectilActivo = false;
        this.ultimoDisparo = -2;
        this.fila = -1;
        this.columna = -1;
    }

    public boolean puedeDisparar(int turnoActual) {
        return !proyectilActivo && (turnoActual - ultimoDisparo >= 2);
    }

    public void disparar(int turnoActual) {
        proyectilActivo = true;
        ultimoDisparo = turnoActual;
    }

    public void proyectilDestruido() {
        proyectilActivo = false;
    }

    @Override
    public void actuar() {
    }
}
