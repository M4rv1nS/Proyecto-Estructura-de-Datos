package com.mycompany.proyecto;

public class Casilla {

    public Tropa tropa;
    public boolean esZombie;

    public Casilla() {
        this.tropa = null;
        this.esZombie = false;
    }

    public boolean estaOcupada() {
        return tropa != null;
    }

    public void colocarTropa(Tropa tropa, boolean esZombie) {
        this.tropa = tropa;
        this.esZombie = esZombie;
    }

    public void eliminarTropa() {
        this.tropa = null;
        this.esZombie = false;
    }
}
