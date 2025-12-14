package org.example;

import java.util.ArrayList;
import java.util.List;

public class BuclesLogica {

    private static final int LIMITE = 100;

    // Método 1: Usando WHILE
    public List<Integer> generarConWhile(int inicio) {
        List<Integer> lista = new ArrayList<>();
        int i = inicio;
        // Evalúa la condición ANTES de entrar
        while (i <= LIMITE) {
            lista.add(i);
            i++;
        }
        return lista;
    }

    // Método 2: Usando DO-WHILE
    public List<Integer> generarConDoWhile(int inicio) {
        List<Integer> lista = new ArrayList<>();
        if (inicio > LIMITE) return lista; // Protección inicial

        int i = inicio;
        // Ejecuta al menos UNA vez, luego evalúa
        do {
            lista.add(i);
            i++;
        } while (i <= LIMITE);
        return lista;
    }

    // Método 3: Usando FOR
    public List<Integer> generarConFor(int inicio) {
        List<Integer> lista = new ArrayList<>();
        // Estructura compacta: Inicialización; Condición; Incremento
        for (int i = inicio; i <= LIMITE; i++) {
            lista.add(i);
        }
        return lista;
    }
}