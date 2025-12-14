package org.example;

public class ClasificadorLogica {

    public enum Signo {
        POSITIVO, NEGATIVO, CERO
    }

    // Lógica IF-ELSE IF clasica
    public Signo clasificarSigno(int numero) {
        if (numero == 0) {
            return Signo.CERO;
        } else if (numero > 0) {
            return Signo.POSITIVO;
        } else {
            return Signo.NEGATIVO;
        }
    }

    // Lógica con Operador TERNARIO (condicion ? verdadero : falso)
    public int obtenerMayor(int a, int b) {
        // Si a > b retorna a, sino retorna b
        return (a > b) ? a : b;
    }
}