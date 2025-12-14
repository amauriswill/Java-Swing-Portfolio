package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsumoLogica modelo = new ConsumoLogica();
            ConsumoView vista = new ConsumoView();
            new ConsumoController(vista, modelo);
            vista.setVisible(true);
        });
    }
}