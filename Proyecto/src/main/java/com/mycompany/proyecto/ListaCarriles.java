package com.mycompany.proyecto;

public class ListaCarriles {
    private NodoCarril cabeza;

    public ListaCarriles() {
        // Crear 5 carriles de forma enlazada
        for (int i = 4; i >= 0; i--) {
            NodoCarril nuevo = new NodoCarril(i);
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        }
    }

    public ListaTropa obtenerCarril(int fila) {
        NodoCarril actual = cabeza;
        while (actual != null) {
            if (actual.fila == fila) {
                return actual.lista;
            }
            actual = actual.siguiente;
        }
        return null; // si no existe
    }

    private static class NodoCarril {
        int fila;
        ListaTropa lista;
        NodoCarril siguiente;

        public NodoCarril(int fila) {
            this.fila = fila;
            this.lista = new ListaTropa();
            this.siguiente = null;
        }
    }
}
