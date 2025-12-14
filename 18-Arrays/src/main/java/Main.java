package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NotasLogica modelo = new NotasLogica();
            NotasView vista = new NotasView();
            new NotasController(vista, modelo);
            vista.setVisible(true);
        });
    }
}