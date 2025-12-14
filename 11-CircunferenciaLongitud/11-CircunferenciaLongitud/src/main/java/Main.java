package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CircunferenciaLogica modelo = new CircunferenciaLogica();
            CircunferenciaView vista = new CircunferenciaView();
            new CircunferenciaController(vista, modelo);
            vista.setVisible(true);
        });
    }
}