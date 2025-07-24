package com.mycompany.proyecto;

public class GameEngine {

    private int monedas;
    private int maxTurnos = 30;
    private int oleadaActual;
    private int turnoActual;
    private boolean enPreparacion;
    private Tablero tablero;
    private ColaZombies zombiesEnEspera;
    ListaProyectil proyectiles;
    private boolean victoriaMostrada = false;

    public GameEngine() {
        monedas = 800;
        oleadaActual = 1;
        turnoActual = 0;
        enPreparacion = true;
        tablero = new Tablero();
        zombiesEnEspera = new ColaZombies();
        proyectiles = new ListaProyectil();
        generarZombiesOleada();
    }

    public void generarZombiesOleada() {
        int cantidad = (oleadaActual * 2) + 3;
        for (int i = 0; i < cantidad; i++) {
            zombiesEnEspera.agregar(new AlienZombie());
        }
    }

    public void iniciarOleada() {
        enPreparacion = false;
    }

    public void setPreparacion(boolean enPreparacion) {
        this.enPreparacion = enPreparacion;
    }

    public boolean isEnPreparacion() {
        return enPreparacion;
    }

    public boolean comprarYColocarPlanta(int fila, int columna, String tipoPlanta) {
        int costo = tipoPlanta.equals("distancia") ? 250 : 400;
        Tropa planta = tipoPlanta.equals("distancia") ? new PlantaDistancia() : new PlantaMelee();

        if (monedas < costo) {
            return false;
        }

        boolean exito = tablero.colocarTropa(fila, columna, planta, false);
        if (!exito) {
            return false;
        }

        monedas -= costo;
        return true;
    }

    public void ejecutarTurno() {
        if (enPreparacion || estaDerrotado() || victoriaMostrada) return;

        turnoActual++;

        moverProyectiles();
        ataquePlantas();
        movimientoZombies();

        // Zombies solo aparecen si aún no llegamos al turno 30
        if (turnoActual <= maxTurnos && turnoActual % 2 == 0 && !zombiesEnEspera.estaVacia()) {
            AlienZombie nuevo = zombiesEnEspera.sacar();
            int fila = (int) (Math.random() * 5);
            nuevo.setPosicion(fila, tablero.getColumnas() - 1);
            tablero.colocarTropa(fila, tablero.getColumnas() - 1, nuevo, true);
        }

        // Verificar derrota
        if (verificarDerrota()) return;

        // Verificar victoria después del turno 30
        if (turnoActual >= maxTurnos && !hayZombiesEnTablero() && zombiesEnEspera.estaVacia()) {
            victoriaMostrada = true;
        }

        // Si terminó la oleada y aún faltan oleadas antes del turno 30
        if (!hayZombiesEnTablero() && zombiesEnEspera.estaVacia() && turnoActual < maxTurnos) {
            if (oleadaActual < 3) {
                enPreparacion = true;
                oleadaActual++;
                generarZombiesOleada();
            }
        }
    }

    private void moverProyectiles() {
        ListaProyectil.NodoProyectil actual = proyectiles.getCabeza();
        ListaProyectil.NodoProyectil anterior = null;

        while (actual != null) {
            Proyectil p = actual.valor;
            p.avanzar();
            boolean eliminar = false;

            if (p.getPosicion() >= tablero.getColumnas()) {
                eliminar = true;
            } else {
                Casilla destino = tablero.obtenerCasilla(p.getCarril(), p.getPosicion());
                if (destino != null && destino.estaOcupada() && destino.esZombie) {
                    destino.tropa.recibirDanio(p.getDano());

                    if (destino.tropa.getVida() <= 0) {
                        int recompensa = ((AlienZombie) destino.tropa).getRecompensa();
                        tablero.eliminarTropa(p.getCarril(), p.getPosicion());
                        monedas += recompensa;
                    }

                    eliminar = true;
                }
            }

            if (eliminar) {
                if (anterior == null) {
                    proyectiles.cabeza = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
            } else {
                anterior = actual;
            }

            actual = actual.siguiente;
        }
    }

    private void ataquePlantas() {
        Lista<Lista<Casilla>>.Nodo<Lista<Casilla>> carrilNodo = tablero.carriles.getCabeza();

        while (carrilNodo != null) {
            Lista<Casilla> carril = carrilNodo.valor;
            Lista<Casilla>.Nodo<Casilla> casillaNodo = carril.getCabeza();

            while (casillaNodo != null) {
                Casilla c = casillaNodo.valor;

                if (c.estaOcupada() && !c.esZombie) {
                    Tropa t = c.tropa;

                    if (t instanceof PlantaDistancia pd && pd.puedeDisparar(turnoActual)) {
                        proyectiles.agregar(new Proyectil(pd.getAtaque(), pd.getFila(), pd.getColumna()));
                        pd.disparar(turnoActual);
                    } else if (t instanceof PlantaMelee pm) {
                        int colFrente = t.getColumna() + 1;

                        if (colFrente < 9) {
                            Casilla frente = tablero.obtenerCasilla(t.getFila(), colFrente);
                            if (frente != null && frente.estaOcupada() && frente.esZombie) {
                                frente.tropa.recibirDanio(pm.getAtaque());
                                if (frente.tropa.getVida() <= 0) {
                                    tablero.eliminarTropa(t.getFila(), colFrente);
                                    monedas += 300;
                                }
                            }
                        }
                    }
                }

                casillaNodo = casillaNodo.siguiente;
            }

            carrilNodo = carrilNodo.siguiente;
        }
    }

    private void movimientoZombies() {
        Lista<Lista<Casilla>>.Nodo<Lista<Casilla>> carrilNodo = tablero.carriles.getCabeza();

        while (carrilNodo != null) {
            Lista<Casilla> carril = carrilNodo.valor;
            Lista<Casilla>.Nodo<Casilla> casillaNodo = carril.getCabeza();

            while (casillaNodo != null) {
                Casilla c = casillaNodo.valor;

                if (c.estaOcupada() && c.esZombie) {
                    AlienZombie z = (AlienZombie) c.tropa;
                    int colActual = z.getColumna();
                    int colFrente = colActual - 1;

                    if (colFrente >= 0) {
                        Casilla frente = tablero.obtenerCasilla(z.getFila(), colFrente);
                        if (frente != null && frente.estaOcupada() && !frente.esZombie) {
                            frente.tropa.recibirDanio(z.getAtaque());
                            if (frente.tropa.getVida() <= 0) {
                                tablero.eliminarTropa(z.getFila(), colFrente);
                            }
                        } else {
                            tablero.eliminarTropa(z.getFila(), colActual);
                            z.setPosicion(z.getFila(), colFrente);
                            tablero.colocarTropa(z.getFila(), colFrente, z, true);
                        }
                    }
                }

                casillaNodo = casillaNodo.siguiente;
            }

            carrilNodo = carrilNodo.siguiente;
        }
    }

    public boolean hayZombiesEnTablero() {
        for (int i = 0; i < 5; i++) {
            Lista<Casilla> carril = tablero.carriles.obtener(i);
            for (int j = 0; j < 9; j++) {
                Casilla c = carril.obtener(j);
                if (c.estaOcupada() && c.esZombie) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarDerrota() {
        for (int i = 0; i < 5; i++) {
            Casilla c = tablero.obtenerCasilla(i, 0);
            if (c.estaOcupada() && c.esZombie) {
                return true;
            }
        }
        return false;
    }

    // Getters públicos
    public int getMonedas() {
        return monedas;
    }

    public int getOleadaActual() {
        return oleadaActual;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public ColaZombies getZombiesEnEspera() {
        return zombiesEnEspera;
    }

    public boolean enPreparacion() {
        return enPreparacion;
    }

    public boolean estaDerrotado() {
        return verificarDerrota();
    }

    public boolean isVictoriaMostrada() {
        return victoriaMostrada;
    }
}
