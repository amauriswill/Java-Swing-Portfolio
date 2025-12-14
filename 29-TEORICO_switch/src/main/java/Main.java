package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarioLogica modelo = new CalendarioLogica();
            CalendarioView vista = new CalendarioView();
            new CalendarioController(vista, modelo);
            vista.setVisible(true);
        });
    }
}