package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear las partes del MVC para el Login
            LoginAuth modelo = new LoginAuth();
            LoginView vistaLogin = new LoginView();

            // El controlador une todo y prepara la lógica
            new LoginController(vistaLogin, modelo);

            // Mostrar la ventana de inicio
            vistaLogin.setVisible(true);
        });
    }
}