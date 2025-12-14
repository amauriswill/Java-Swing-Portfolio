package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuclesController implements ActionListener {

    private final BuclesView vista;
    private final BuclesLogica modelo;

    public BuclesController(BuclesView vista, BuclesLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vista.getBtnLimpiar()) {
            vista.limpiarTodasLasListas();
            vista.limpiarInput();
            return;
        }

        // Para los botones de bucle, necesitamos el número
        try {
            String input = vista.getInputNumero();
            if (input.isEmpty()) {
                vista.mostrarError("Ingrese un número inicial.");
                return;
            }

            int inicio = Integer.parseInt(input);

            if (inicio > 100) {
                vista.mostrarError("El número debe ser menor o igual a 100.");
                return;
            }

            // Ejecutar la lógica correspondiente
            if (source == vista.getBtnWhile()) {
                List<Integer> resultado = modelo.generarConWhile(inicio);
                vista.actualizarListaWhile(resultado);
            }
            else if (source == vista.getBtnDoWhile()) {
                List<Integer> resultado = modelo.generarConDoWhile(inicio);
                vista.actualizarListaDoWhile(resultado);
            }
            else if (source == vista.getBtnFor()) {
                List<Integer> resultado = modelo.generarConFor(inicio);
                vista.actualizarListaFor(resultado);
            }

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese solo números enteros válidos.");
        }
    }
}