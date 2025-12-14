package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ParImparLogica modelo = new ParImparLogica();
            ParImparView vista = new ParImparView();
            new ParImparController(vista, modelo);
            vista.setVisible(true);
        });
    }
}