/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
public class GameEngine {

    private int monedas;
    private int oleadaActual;
    private int turnoActual;

    private ListaCarriles carriles; // estructura de carriles enlazados
    private ColaZombies zombiesEnEspera; // cola de zombies que aun no aparecen
    private ListaProyectil proyectiles;
    private Tablero tablero;

    public GameEngine() {
        monedas = 800;
        oleadaActual = 1;
        turnoActual = 0;

        carriles = new ListaCarriles(); // lista de carriles con tropas
        tablero = new Tablero();

        zombiesEnEspera = new ColaZombies();
        proyectiles = new ListaProyectil();

        generarZombiesOleada();
    }

    // genera los zombies para la oleada actual usando la formula oleada*2 + 3
    public void generarZombiesOleada() {
        int cantidad = (oleadaActual * 2) + 3;
        for (int i = 0; i < cantidad; i++) {
            zombiesEnEspera.agregar(new AlienZombie());
        }
    }

    // coloca una planta si hay monedas suficientes y la casilla esta libre
    public boolean comprarYColocarPlanta(int fila, int columna, Tropa planta, int costo) {
        if (monedas >= costo && tablero.colocarTropa(fila, columna, planta, false)) {
            monedas -= costo;
            planta.setPosicion(fila, columna);
            carriles.obtenerCarril(fila).agregar(planta);
            return true;
        }
        return false;
    }

    // ejecuta un turno completo: proyectiles, plantas, zombies y agrega nuevo zombie cada 2 turnos
    public void ejecutarTurno() {
        turnoActual++;

        moverProyectiles();
        ataquePlantas();
        movimientoZombies();

        if (turnoActual % 2 == 0 && !zombiesEnEspera.estaVacia()) {
            AlienZombie nuevo = zombiesEnEspera.sacar();
            int fila = (int)(Math.random() * 5);
            nuevo.setPosicion(fila, tablero.getColumnas() - 1);
            tablero.colocarTropa(fila, tablero.getColumnas() - 1, nuevo, true);
            carriles.obtenerCarril(fila).agregar(nuevo);
        }
    }

    // mueve los proyectiles y aplica dano si impactan un zombie
    private void moverProyectiles() {
        NodoProyectil actual = proyectiles.getCabeza();
        while (actual != null) {
            Proyectil p = actual.valor;
            p.avanzar();

            Casilla destino = tablero.obtenerCasilla(p.getCarril(), p.getPosicion());
            if (destino != null && destino.estaOcupada() && destino.esZombie) {
                destino.tropa.recibirDanio(p.getDano());
                if (destino.tropa.getVida() <= 0) {
                    tablero.eliminarTropa(p.getCarril(), p.getPosicion());
                    monedas += 300;
                }
                proyectiles.eliminar(actual);
                break;
            }
            if (p.getPosicion() >= tablero.getColumnas()) {
                proyectiles.eliminar(actual);
                break;
            }
            actual = actual.siguiente;
        }
    }

    // ejecuta los ataques de las plantas si hay enemigos al frente
    private void ataquePlantas() {
        for (int i = 0; i < 5; i++) {
            NodoTropa nodo = carriles.obtenerCarril(i).getCabeza(); // se cambio carriles[i] por metodo obtenerCarril
            while (nodo != null) {
                if (nodo.valor instanceof PlantaDistancia p) {
                    if (p.puedeDisparar()) {
                        proyectiles.agregar(new Proyectil(p.getAtaque(), i));
                        p.disparar(proyectiles); // se anadio la lista como argumento porque el metodo lo requiere
                    }
                } else if (nodo.valor instanceof PlantaMelee m) {
                    int col = m.getColumna();
                    Casilla frente = tablero.obtenerCasilla(i, col + 1);
                    if (frente != null && frente.estaOcupada() && frente.esZombie) {
                        frente.tropa.recibirDanio(m.getAtaque());
                        if (frente.tropa.getVida() <= 0) {
                            tablero.eliminarTropa(i, col + 1);
                            monedas += 300;
                        }
                    }
                }
                nodo = nodo.siguiente;
            }
        }
    }

    // mueve los zombies o ataca si hay una planta adelante
    private void movimientoZombies() {
        for (int i = 0; i < 5; i++) {
            NodoTropa nodo = carriles.obtenerCarril(i).getCabeza();
            while (nodo != null) {
                if (nodo.valor instanceof AlienZombie z) {
                    int col = z.getColumna();
                    Casilla adelante = tablero.obtenerCasilla(i, col - 1);
                    if (adelante != null && adelante.estaOcupada() && !adelante.esZombie) {
                        adelante.tropa.recibirDanio(z.getAtaque());
                        if (adelante.tropa.getVida() <= 0) {
                            tablero.eliminarTropa(i, col - 1);
                        }
                    } else {
                        tablero.eliminarTropa(i, col);
                        z.setPosicion(i, col - 1);
                        tablero.colocarTropa(i, col - 1, z, true);
                    }
                }
                nodo = nodo.siguiente;
            }
        }
    }

    // verifica si hay zombies en la columna 0 (pierde el jugador)
    public boolean verificarDerrota() {
        for (int i = 0; i < 5; i++) {
            Casilla c = tablero.obtenerCasilla(i, 0);
            if (c.estaOcupada() && c.esZombie) return true;
        }
        return false;
    }

    public int getMonedas() {
        return monedas;
    }

    public int getOleadaActual() {
        return oleadaActual;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public ListaProyectil getProyectiles() {
        return proyectiles;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
