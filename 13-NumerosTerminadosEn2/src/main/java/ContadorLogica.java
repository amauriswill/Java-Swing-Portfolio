package org.example;

public class ContadorLogica {

    private int cantidadEncontrada;

    public ContadorLogica() {
        this.cantidadEncontrada = 0;
    }

    // Lógica pura: ¿Termina en 2?
    // Matemáticamente: Si el residuo de dividir entre 10 es 2 (o -2 para negativos si aplicara)
    public boolean terminaEnDos(int numero) {
        return Math.abs(numero) % 10 == 2;
    }

    public void incrementarCuenta() {
        this.cantidadEncontrada++;
    }

    public int getCantidadEncontrada() {
        return cantidadEncontrada;
    }

    public void reiniciar() {
        this.cantidadEncontrada = 0;
    }
}