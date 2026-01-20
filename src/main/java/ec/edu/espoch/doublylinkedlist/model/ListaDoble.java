package ec.edu.espoch.doublylinkedlist.model;

import java.util.ArrayList;
import java.util.List;

public class ListaDoble {
    private NodoDoble cabeza;
    private NodoDoble cola;

    public ListaDoble() {
        cabeza = null;
        cola = null;
    }

    // ================================
    // INSERTAR AL INICIO
    // ================================
    public void insertarInicio(int dato) {
        NodoDoble nuevo = new NodoDoble(dato);

        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
    }

    // ================================
    // INSERTAR AL FINAL
    // ================================
    public void insertarFinal(int dato) {
        NodoDoble nuevo = new NodoDoble(dato);

        if (cola == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }

    // ================================
    // INSERTAR DESPUÉS DE UN DATO
    // ================================
    public boolean insertarDespues(int referencia, int dato) {
        NodoDoble actual = cabeza;

        while (actual != null) {
            if (actual.dato == referencia) {

                NodoDoble nuevo = new NodoDoble(dato);
                nuevo.siguiente = actual.siguiente;
                nuevo.anterior = actual;

                if (actual.siguiente != null) {
                    actual.siguiente.anterior = nuevo;
                } else {
                    cola = nuevo;
                }

                actual.siguiente = nuevo;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    // ================================
    // BUSCAR
    // ================================
    public int indexOf(int dato) {
        NodoDoble actual = cabeza;
        int index = 0;

        while (actual != null) {
            if (actual.dato == dato) {
                return index;
            }
            actual = actual.siguiente;
            index++;
        }
        return -1;
    }

    // ================================
    // ELIMINAR
    // ================================
    public void eliminar(int dato) {
        NodoDoble actual = cabeza;

        while (actual != null) {
            if (actual.dato == dato) {

                if (actual == cabeza) {
                    cabeza = actual.siguiente;
                    if (cabeza != null) {
                        cabeza.anterior = null;
                    }
                } else if (actual == cola) {
                    cola = actual.anterior;
                    cola.siguiente = null;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                }
                return;
            }
            actual = actual.siguiente;
        }
    }

    // ================================
    // SNAPSHOT PARA EL CANVAS
    // ================================
    public List<Integer> toList() {
        List<Integer> datos = new ArrayList<>();
        NodoDoble actual = cabeza;

        while (actual != null) {
            datos.add(actual.dato);
            actual = actual.siguiente;
        }
        return datos;
    }
}
