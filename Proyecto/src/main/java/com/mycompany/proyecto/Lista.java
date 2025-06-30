package com.mycompany.proyecto;

public class Lista<T> {

    private Nodo<T> cabeza;
    private int tamano;

    public Lista() {
        cabeza = null;
        tamano = 0;
    }

    public void agregar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamano++;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamano) {
            return null;
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.valor;
    }

    public int tamano() {
        return tamano;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public class Nodo<T> {

        public T valor;
        public Nodo<T> siguiente;

        Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }
}
