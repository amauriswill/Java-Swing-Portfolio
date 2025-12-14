package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComparadorController implements ActionListener {

    private final ComparadorView vista;
    private final ComparadorLogica modelo;

    public ComparadorController(ComparadorView vista, ComparadorLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String strA = vista.getA();
            String strB = vista.getB();

            if (strA.isEmpty() || strB.isEmpty()) {
                vista.mostrarError("Ingrese ambos valores.");
                return;
            }

            int a = Integer.parseInt(strA);
            int b = Integer.parseInt(strB);

            // Obtener todos los resultados de una vez
            ComparadorLogica.ResultadoComparacion res = modelo.comparar(a, b);

            // Actualizar la vista (Tarjeta por tarjeta)
            vista.actualizarTarjeta(vista.getLblMayor(), res.mayor);
            vista.actualizarTarjeta(vista.getLblMenor(), res.menor);
            vista.actualizarTarjeta(vista.getLblMayorIgual(), res.mayorIgual);
            vista.actualizarTarjeta(vista.getLblMenorIgual(), res.menorIgual);
            vista.actualizarTarjeta(vista.getLblIgual(), res.igual);
            vista.actualizarTarjeta(vista.getLblDistinto(), res.distinto);

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese solo números enteros.");
        }
    }
}