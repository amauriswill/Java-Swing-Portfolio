package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrestamoLogica modelo = new PrestamoLogica();
            PrestamoView vista = new PrestamoView();
            new PrestamoController(vista, modelo);
            vista.setVisible(true);
        });
    }
}