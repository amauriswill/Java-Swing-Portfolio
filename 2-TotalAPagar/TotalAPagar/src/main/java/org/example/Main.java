package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Instanciamos la Lógica (Modelo)
            VentasLogica modelo = new VentasLogica();

            // 2. Instanciamos la Estética (Vista)
            TotalPagarView vista = new TotalPagarView();

            // 3. Instanciamos el Comportamiento (Controlador) y los unimos
            new VentasController(vista, modelo);

            // 4. Mostramos la ventana
            vista.setVisible(true);
        });
    }
}