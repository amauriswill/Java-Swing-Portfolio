package org.example;

import javax.swing.*;
import java.awt.*;

public class SistemaView extends JFrame {

    public SistemaView(String usuario) {
        setTitle("Sistema Principal - Bienvenido");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        JLabel lblBienvenida = new JLabel("<html><div style='text-align: center;'>" +
                "<h1 style='color:#003366; font-size:30px;'>¡BIENVENIDO AL SISTEMA!</h1>" +
                "<h3 style='color:#555;'>Usuario: " + usuario.toUpperCase() + "</h3>" +
                "</div></html>");

        mainPanel.add(lblBienvenida);
    }
}