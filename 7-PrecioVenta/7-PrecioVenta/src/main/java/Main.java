package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrecioVentaLogica modelo = new PrecioVentaLogica();
            PrecioVentaView vista = new PrecioVentaView();
            new PrecioVentaController(vista, modelo);
            vista.setVisible(true);
        });
    }
}