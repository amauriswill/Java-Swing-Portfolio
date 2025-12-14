package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NotasController implements ActionListener {

    private final NotasView vista;
    private final NotasLogica modelo;

    public NotasController(NotasView vista, NotasLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();

        if (fuente == vista.getBtnAgregar()) {
            agregarNota();
        } else if (fuente == vista.getBtnCalcular()) {
            calcularEstadisticas();
        } else if (fuente == vista.getBtnReiniciar()) {
            reiniciar();
        }
    }

    private void agregarNota() {
        try {
            String input = vista.getNotaInput();
            if (input.isEmpty()) return;

            double nota = Double.parseDouble(input);

            if (nota < 0 || nota > 100) {
                vista.mostrarError("La nota debe estar entre 0 y 100.");
                return;
            }

            // Agregar al modelo
            modelo.agregarNota(nota);

            // Agregar a la vista
            vista.agregarFila(modelo.getCantidadAlumnos(), nota);
            vista.limpiarInput();

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese un valor numérico válido.");
        }
    }

    private void calcularEstadisticas() {
        if (modelo.getCantidadAlumnos() == 0) {
            vista.mostrarError("No hay alumnos registrados.");
            return;
        }

        // 1. Calcular promedio
        double promedio = modelo.calcularPromedio();
        vista.mostrarPromedio(promedio);

        // 2. Determinar quiénes superaron el promedio
        List<Boolean> resultados = modelo.obtenerSuperiores(promedio);

        // 3. Actualizar la tabla visualmente
        for (int i = 0; i < resultados.size(); i++) {
            vista.actualizarResultadoFila(i, resultados.get(i));
        }
    }

    private void reiniciar() {
        modelo.limpiar();
        vista.limpiarTabla();
        vista.limpiarInput();
    }
}