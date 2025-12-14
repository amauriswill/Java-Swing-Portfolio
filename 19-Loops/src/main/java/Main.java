package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BuclesLogica modelo = new BuclesLogica();
            BuclesView vista = new BuclesView();
            new BuclesController(vista, modelo);
            vista.setVisible(true);
        });
    }
}