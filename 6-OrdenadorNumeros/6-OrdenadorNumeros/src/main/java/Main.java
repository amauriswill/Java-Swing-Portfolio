package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrdenadorLogica modelo = new OrdenadorLogica();
            OrdenadorView vista = new OrdenadorView();
            new OrdenadorController(vista, modelo);
            vista.setVisible(true);
        });
    }
}