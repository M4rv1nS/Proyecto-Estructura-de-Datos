package com.mycompany.proyecto;

public class ListaProyectil {

    public NodoProyectil cabeza;

    public ListaProyectil() {
        cabeza = null;
    }

    public void agregar(Proyectil proyectil) {
        NodoProyectil nuevo = new NodoProyectil(proyectil);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoProyectil actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public NodoProyectil getCabeza() {
        return cabeza;
    }

    public class NodoProyectil {

        public Proyectil valor;
        public NodoProyectil siguiente;

        public NodoProyectil(Proyectil valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }
}
