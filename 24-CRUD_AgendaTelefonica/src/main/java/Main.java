package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgendaLogica modelo = new AgendaLogica();
            AgendaView vista = new AgendaView();
            new AgendaController(vista, modelo);
            vista.setVisible(true);
        });
    }
}