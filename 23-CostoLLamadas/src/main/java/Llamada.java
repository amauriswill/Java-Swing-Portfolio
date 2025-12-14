package org.example;

import java.math.BigDecimal;

public class Llamada {

    // Enum para manejar las tarifas de forma centralizada
    public enum Compania {
        CLARO("Claro", new BigDecimal("10.00")),
        ALTICE("Altice", new BigDecimal("7.00")),
        VIVA("Viva", new BigDecimal("5.00"));

        private final String nombre;
        private final BigDecimal precioPorMinuto;

        Compania(String nombre, BigDecimal precio) {
            this.nombre = nombre;
            this.precioPorMinuto = precio;
        }

        public BigDecimal calcularCosto(int minutos) {
            return precioPorMinuto.multiply(new BigDecimal(minutos));
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    private int minutos;
    private Compania compania;
    private BigDecimal total;

    public Llamada(int minutos, Compania compania) {
        this.minutos = minutos;
        this.compania = compania;
        this.total = compania.calcularCosto(minutos);
    }

    public Object[] toArray() {
        return new Object[]{compania.nombre, minutos + " min", "$" + total};
    }

    public BigDecimal getTotal() { return total; }
}