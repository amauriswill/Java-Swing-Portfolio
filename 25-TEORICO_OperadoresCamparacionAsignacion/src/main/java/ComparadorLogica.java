package org.example;

public class ComparadorLogica {

    // Clase interna para transportar todos los resultados juntos
    public static class ResultadoComparacion {
        public final boolean mayor;
        public final boolean menor;
        public final boolean mayorIgual;
        public final boolean menorIgual;
        public final boolean igual;
        public final boolean distinto;

        public ResultadoComparacion(int a, int b) {
            this.mayor = a > b;
            this.menor = a < b;
            this.mayorIgual = a >= b;
            this.menorIgual = a <= b;
            this.igual = a == b;
            this.distinto = a != b;
        }
    }

    public ResultadoComparacion comparar(int a, int b) {
        return new ResultadoComparacion(a, b);
    }
}