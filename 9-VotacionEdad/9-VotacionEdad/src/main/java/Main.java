package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VotacionLogica modelo = new VotacionLogica();
            VotacionView vista = new VotacionView();
            new VotacionController(vista, modelo);
            vista.setVisible(true);
        });
    }
}