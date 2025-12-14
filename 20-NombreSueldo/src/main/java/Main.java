package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NominaLogica modelo = new NominaLogica();
            NominaView vista = new NominaView();
            new NominaController(vista, modelo);
            vista.setVisible(true);
        });
    }
}