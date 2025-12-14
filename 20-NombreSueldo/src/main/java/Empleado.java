package org.example;

import java.math.BigDecimal;

public class Empleado {
    private String nombre;
    private BigDecimal sueldo;

    public Empleado(String nombre, BigDecimal sueldo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public String getNombre() { return nombre; }
    public BigDecimal getSueldo() { return sueldo; }

    // Para mostrar en la tabla fácilmente
    public Object[] toArray() {
        return new Object[]{nombre, String.format("$ %.2f", sueldo)};
    }
}