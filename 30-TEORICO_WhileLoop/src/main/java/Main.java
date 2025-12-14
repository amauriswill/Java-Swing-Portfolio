package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WhileLogica modelo = new WhileLogica();
            WhileView vista = new WhileView();
            new WhileController(vista, modelo);
            vista.setVisible(true);
        });
    }
}