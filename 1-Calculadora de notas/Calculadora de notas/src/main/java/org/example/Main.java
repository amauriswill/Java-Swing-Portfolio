package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
       
            NotasLogica modelo = new NotasLogica();
            
            CalculoNotas vista = new CalculoNotas();

            new NotasController(vista, modelo);

            vista.setVisible(true);
        });
    }
}
