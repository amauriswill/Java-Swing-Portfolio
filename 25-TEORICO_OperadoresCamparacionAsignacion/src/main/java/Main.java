package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ComparadorLogica modelo = new ComparadorLogica();
            ComparadorView vista = new ComparadorView();
            new ComparadorController(vista, modelo);
            vista.setVisible(true);
        });
    }
}