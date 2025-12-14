package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Crear el Modelo
            NotasLogica modelo = new NotasLogica();

            // 2. Crear la Vista (inicialmente invisible)
            CalculoNotas vista = new CalculoNotas();

            // 3. Crear el Controlador (uniendo los dos anteriores)
            new NotasController(vista, modelo);

            // 4. Mostrar la aplicación
            vista.setVisible(true);
        });
    }
}