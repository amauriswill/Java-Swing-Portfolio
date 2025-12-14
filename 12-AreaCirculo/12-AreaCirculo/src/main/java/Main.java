package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CirculoLogica modelo = new CirculoLogica();
            CirculoView vista = new CirculoView();
            new CirculoController(vista, modelo);
            vista.setVisible(true);
        });
    }
}