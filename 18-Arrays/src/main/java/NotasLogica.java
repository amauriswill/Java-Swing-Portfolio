package org.example;

import java.util.ArrayList;
import java.util.List;

public class NotasLogica {

    private final List<Double> notas;

    public NotasLogica() {
        this.notas = new ArrayList<>();
    }

    public void agregarNota(double nota) {
        notas.add(nota);
    }

    public void limpiar() {
        notas.clear();
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) return 0.0;

        double suma = 0;
        for (double n : notas) {
            suma += n;
        }
        return suma / notas.size();
    }

    // Devuelve una lista de booleanos: true si el alumno superó el promedio
    public List<Boolean> obtenerSuperiores(double promedio) {
        List<Boolean> resultados = new ArrayList<>();
        for (double nota : notas) {
            resultados.add(nota > promedio);
        }
        return resultados;
    }

    public int getCantidadAlumnos() {
        return notas.size();
    }
}