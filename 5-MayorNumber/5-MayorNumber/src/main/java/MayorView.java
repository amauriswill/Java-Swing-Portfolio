package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MayorView extends JFrame {

    private JTextField txtNum1, txtNum2, txtNum3;
    private JButton btnCalcular;
    private JLabel lblResultado;
    private JPanel pnlResultado;

    public MayorView() {
        // Desactivamos LookAndFeel de Windows para garantizar Alto Contraste
        // try { UIManager.setLookAndFeel(...); } catch... (Omitido)

        setTitle("Comparador Accesible");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Principal
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ZONA DE ENTRADAS ---
        JPanel inputPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        inputPanel.setOpaque(false);

        // Helper para crear pares Label/Input rápidamente
        agregarCampo(inputPanel, "Número 1:", txtNum1 = new JTextField());
        agregarCampo(inputPanel, "Número 2:", txtNum2 = new JTextField());
        agregarCampo(inputPanel, "Número 3:", txtNum3 = new JTextField());

        // Espacio
        inputPanel.add(Box.createVerticalStrut(10));

        // BOTÓN ACCESIBLE
        btnCalcular = new JButton("ENCONTRAR EL MAYOR");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 16));
        btnCalcular.setBackground(new Color(0, 34, 68)); // Azul Marino Oscuro
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setOpaque(true);
        btnCalcular.setBorderPainted(false);
        btnCalcular.setFocusPainted(true);
        btnCalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inputPanel.add(btnCalcular);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // --- ZONA DE RESULTADO ---
        pnlResultado = new JPanel(new GridBagLayout());
        pnlResultado.setBackground(Color.WHITE);
        pnlResultado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        lblResultado = new JLabel("Esperando números...");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 20));
        lblResultado.setForeground(Color.GRAY);

        pnlResultado.add(lblResultado);
        mainPanel.add(pnlResultado, BorderLayout.CENTER);
    }

    // Método auxiliar para no repetir código de diseño
    private void agregarCampo(JPanel panel, String texto, JTextField campo) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
        panel.add(label);

        campo.setFont(new Font("Arial", Font.PLAIN, 18));
        campo.setHorizontalAlignment(JTextField.CENTER);
        campo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(campo);
    }

    // --- GETTERS Y SETTERS ---
    public String getNum1() { return txtNum1.getText(); }
    public String getNum2() { return txtNum2.getText(); }
    public String getNum3() { return txtNum3.getText(); }

    public void setBotonListener(ActionListener listener) {
        btnCalcular.addActionListener(listener);
    }

    public void mostrarResultado(String texto, Color fondo, Color textoColor) {
        lblResultado.setText(texto);
        lblResultado.setForeground(textoColor);
        pnlResultado.setBackground(fondo);
        pnlResultado.requestFocusInWindow();
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Datos", JOptionPane.ERROR_MESSAGE);
    }
}