package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContadorController implements ActionListener {

    private final ContadorView vista;
    private final ContadorLogica modelo;

    public ContadorController(ContadorView vista, ContadorLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = vista.getNumeroInput();

            if (input.isEmpty()) {
                vista.mostrarError("Por favor ingrese un número.");
                return;
            }

            int numero = Integer.parseInt(input);

            // LOGICA DEL VIDEO: Si es negativo, se acaba la sesión.
            if (numero < 0) {
                // 1. Mostrar resultado final
                vista.mostrarMensajeFinal(modelo.getCantidadEncontrada());

                // 2. Reiniciar sistema
                modelo.reiniciar();
                vista.actualizarContador(0);
                vista.limpiarHistorial();
                vista.limpiarInput();
                vista.agregarAlHistorial("--- SESIÓN REINICIADA ---");
                return;
            }

            // Si es positivo, procesamos
            boolean match = modelo.terminaEnDos(numero);
            String log = "Entrada: " + numero;

            if (match) {
                modelo.incrementarCuenta();
                log += " [TERMINA EN 2] (✓)";
            } else {
                log += " (X)";
            }

            // Actualizar Vista
            vista.agregarAlHistorial(log);
            vista.actualizarContador(modelo.getCantidadEncontrada());
            vista.limpiarInput();

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese solo números enteros.");
        }
    }
}