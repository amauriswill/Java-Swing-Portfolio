package org.example;

public class AnalizadorLogica {

    // Retorna true si es par, false si es impar
    public boolean esPar(int numero) {
        return numero % 2 == 0;
    }

    // Retorna un String describiendo el signo: POSITIVO, NEGATIVO o NEUTRO
    public String determinarSigno(int numero) {
        if (numero > 0) return "POSITIVO";
        if (numero < 0) return "NEGATIVO";
        return "NEUTRO"; // Caso del 0
    }
}