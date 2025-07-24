package com.mycompany.proyecto;

public class Tablero {

    Lista<Lista<Casilla>> carriles;
    private final int filas = 5;
    private final int columnas = 9;

    public Tablero() {
        
        carriles = new Lista<>();
        
        for (int i = 0; i < filas; i++) {
            
            Lista<Casilla> carril = new Lista<>();
            
            for (int j = 0; j < columnas; j++) {
                
                carril.agregarFinal(new Casilla());
                
            }
            carriles.agregarFinal(carril);
        }
    }

    public Casilla obtenerCasilla(int fila, int columna) {
        
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            
            return carriles.obtener(fila).obtener(columna);
            
        }
        return null;
    }

    public boolean colocarTropa(int fila, int columna, Tropa tropa, boolean esZombie) {
        
        Casilla c = obtenerCasilla(fila, columna);
        
        if (c != null && !c.estaOcupada()) {
            
            c.tropa = tropa;
            c.esZombie = esZombie;
            tropa.setPosicion(fila, columna);
            return true;
        }
        
        return false;
    }

    public void eliminarTropa(int fila, int columna) {
        
        Casilla c = obtenerCasilla(fila, columna);
        
        if (c != null) {
            
            c.tropa = null;
            c.esZombie = false;
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
