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

    private ListaTropa[] carriles; // Lista de tropas aliadas por carril (ej: 5 carriles)
    private ColaZombies zombiesEnEspera; // Cola de zombies que aún no aparecen
    private ListaProyectil proyectiles;
    private Tablero tablero;

    public GameEngine() {
        monedas = 800;
        oleadaActual = 1;
        turnoActual = 0;
        carriles = new ListaTropa[5]; // cada carril es una lista enlazada de tropas
        tablero = new Tablero();

        for (int i = 0; i < 5; i++) {
            carriles[i] = new ListaTropa();
        }

        zombiesEnEspera = new ColaZombies();
        proyectiles = new ListaProyectil();

        generarZombiesOleada();
    }

    /**
     * Genera los zombies para la oleada actual.
     * La fórmula es: cantidad = oleada * 2 + 3
     */
    public void generarZombiesOleada() {
        int cantidad = (oleadaActual * 2) + 3;
        for (int i = 0; i < cantidad; i++) {
            zombiesEnEspera.agregar(new AlienZombie());
        }
    }

    /**
     * Coloca una planta en un carril determinado si hay suficiente dinero.
     * @param fila
     * @param columna
     * @param planta
     * @param costo
     * @return 
     */
public boolean comprarYColocarPlanta(int fila, int columna, Tropa planta, int costo) {
    if (monedas >= costo && tablero.colocarTropa(fila, columna, planta, false)) {
        monedas -= costo;
        planta.setPosicion(fila, columna);
        return true;
    }
    return false;
}

public void aparecerZombieEn(int fila) {
    AlienZombie z = zombiesEnEspera.sacar();
    if (z != null) {
        tablero.colocarTropa(fila, tablero.getColumnas() - 1, z, true);
        z.setPosicion(fila, tablero.getColumnas() - 1);
    }
}

    /**
     * Ejecuta un turno completo: proyectiles → ataque de plantas → zombies.
     */
    public void ejecutarTurno() {
        turnoActual++;

        moverProyectiles();
        ataquePlantas();
        movimientoZombies();

        // Cada 2 turnos aparece un zombie nuevo
        if (turnoActual % 2 == 0 && !zombiesEnEspera.estaVacia()) {
            AlienZombie nuevo = zombiesEnEspera.sacar();
            int carril = (int)(Math.random() * 5); // Selecciona carril aleatorio
            carriles[carril].agregar(nuevo);
        }
    }

    /**
     * Mueve proyectiles y aplica daño si impactan a un zombie.
     */
    private void moverProyectiles() {
        NodoProyectil actual = proyectiles.getCabeza();
        while (actual != null) {
            // Lógica: buscar enemigo en trayectoria y aplicar daño
            // En esta versión base no se implementa el mapa exacto, se simula
            actual = actual.siguiente;
        }
    }

    /**
     * Ejecuta ataques de plantas si hay enemigos en frente.
     */
    private void ataquePlantas() {
        for (int i = 0; i < 5; i++) {
            NodoTropa nodo = carriles[i].getCabeza();
            while (nodo != null) {
                if (nodo.valor instanceof PlantaDistancia p) {
                    p.disparar(proyectiles);
                } else if (nodo.valor instanceof PlantaMelee) {
                    // Buscar si hay enemigo justo al frente y atacarlo
                }
                nodo = nodo.siguiente;
            }
        }
    }

    /**
     * Mueve los zombies una casilla, o atacan si hay una planta delante.
     */
    private void movimientoZombies() {
        for (int i = 0; i < 5; i++) {
            NodoTropa nodo = carriles[i].getCabeza();
            while (nodo != null) {
                if (nodo.valor instanceof AlienZombie) {
                    // Lógica de avanzar o atacar
                }
                nodo = nodo.siguiente;
            }
        }
    }

    /**
     * Verifica si algún zombie ha llegado al final del tablero.
     */
    public boolean verificarDerrota() {
        // Lógica para definir si algún zombie llegó al extremo izquierdo
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
}


