package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LogicaBooleana modelo = new LogicaBooleana();
            LogicaView vista = new LogicaView();
            new LogicaController(vista, modelo);
            vista.setVisible(true);
        });
    }
}