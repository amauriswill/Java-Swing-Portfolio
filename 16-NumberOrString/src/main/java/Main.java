package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DigitoLogica modelo = new DigitoLogica();
            DigitoView vista = new DigitoView();
            new DigitoController(vista, modelo);
            vista.setVisible(true);
        });
    }
}