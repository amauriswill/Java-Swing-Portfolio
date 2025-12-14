package org.example;

import java.util.ArrayList;
import java.util.List;

public class WhileLogica {

    // Ejemplo 1: El Bucle While Clásico
    // Genera una secuencia de números hasta el límite indicado
    public List<Integer> generarSecuencia(int limite) {
        List<Integer> secuencia = new ArrayList<>();
        int contador = 1; // Inicialización

        // BUCLE WHILE: Se repite mientras la condición sea verdadera
        while (contador <= limite) {
            secuencia.add(contador);
            contador++; // Incremento importante para evitar bucles infinitos
        }
        return secuencia;
    }

    // Ejemplo 2: Lógica de Evaluación
    public String evaluarNumero(int numero) {
        if (numero == 0) return "FIN (Cero)";
        return (numero > 0) ? "POSITIVO" : "NEGATIVO";
    }
}