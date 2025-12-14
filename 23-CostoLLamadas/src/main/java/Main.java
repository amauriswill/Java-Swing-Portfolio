package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LlamadasView vista = new LlamadasView();
            new LlamadasController(vista);
            vista.setVisible(true);
        });
    }
}