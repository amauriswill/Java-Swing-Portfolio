package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnalizadorLogica modelo = new AnalizadorLogica();
            AnalizadorView vista = new AnalizadorView();
            new AnalizadorController(vista, modelo);
            vista.setVisible(true);
        });
    }
}