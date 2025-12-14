package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CifrasLogica modelo = new CifrasLogica();
            CifrasView vista = new CifrasView();
            new CifrasController(vista, modelo);
            vista.setVisible(true);
        });
    }
}