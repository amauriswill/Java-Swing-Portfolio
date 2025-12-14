package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DivisionLogica modelo = new DivisionLogica();
            DivisionView vista = new DivisionView();
            new DivisionController(vista, modelo);
            vista.setVisible(true);
        });
    }
}