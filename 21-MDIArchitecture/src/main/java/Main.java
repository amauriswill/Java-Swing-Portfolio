package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthService auth = new AuthService();
            LoginView login = new LoginView();
            new AppController(login, auth);
            login.setVisible(true);
        });
    }
}