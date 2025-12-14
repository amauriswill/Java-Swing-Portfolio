package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AccesoLogica modelo = new AccesoLogica();
            AccesoView vista = new AccesoView();
            new AccesoController(vista, modelo);
            vista.setVisible(true);
        });
    }
}