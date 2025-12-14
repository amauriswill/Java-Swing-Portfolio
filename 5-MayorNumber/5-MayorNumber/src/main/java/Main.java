package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MayorLogica modelo = new MayorLogica();
            MayorView vista = new MayorView();
            new MayorController(vista, modelo);
            vista.setVisible(true);
        });
    }
}