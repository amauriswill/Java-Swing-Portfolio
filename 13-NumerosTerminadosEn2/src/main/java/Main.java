package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContadorLogica modelo = new ContadorLogica();
            ContadorView vista = new ContadorView();
            new ContadorController(vista, modelo);
            vista.setVisible(true);
        });
    }
}