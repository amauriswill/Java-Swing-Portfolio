package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClasificadorLogica modelo = new ClasificadorLogica();
            ClasificadorView vista = new ClasificadorView();
            new ClasificadorController(vista, modelo);
            vista.setVisible(true);
        });
    }
}