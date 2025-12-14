package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistroView vista = new RegistroView();
            new RegistroController(vista);
            vista.setVisible(true);
        });
    }
}