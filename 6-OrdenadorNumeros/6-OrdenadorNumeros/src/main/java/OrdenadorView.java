package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrdenadorView extends JFrame {

    private JTextField txtNum1, txtNum2;
    private JButton btnOrdenar;
    private JLabel lblResultado;
    private JPanel pnlResultado;

    public OrdenadorView() {
        // Desactivamos LookAndFeel para garantizar colores de accesibilidad
        // try { UIManager.setLookAndFeel(...); } catch... (Omitido)

        setTitle("Ordenador Numérico Accesible");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Principal
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8")); // Gris azulado suave
        setContentPane(mainPanel);

        // --- ZONA DE ENTRADA ---
        JPanel inputPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        inputPanel.setOpaque(false);

        agregarCampo(inputPanel, "Primer Número:", txtNum1 = new JTextField());
        agregarCampo(inputPanel, "Segundo Número:", txtNum2 = new JTextField());

        // Espacio
        inputPanel.add(Box.createVerticalStrut(10));

        // BOTÓN ACCESIBLE (Azul Profundo)
        btnOrdenar = new JButton("ORDENAR NÚMEROS");
        btnOrdenar.setFont(new Font("Arial", Font.BOLD, 16));
        btnOrdenar.setBackground(new Color(0, 34, 68));
        btnOrdenar.setForeground(Color.WHITE);
        btnOrdenar.setOpaque(true);
        btnOrdenar.setBorderPainted(false);
        btnOrdenar.setFocusPainted(true);
        btnOrdenar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inputPanel.add(btnOrdenar);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // --- ZONA DE RESULTADO ---
        pnlResultado = new JPanel(new GridBagLayout());
        pnlResultado.setBackground(Color.WHITE);
        pnlResultado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        lblResultado = new JLabel("Esperando datos...");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 22));
        lblResultado.setForeground(Color.GRAY);

        pnlResultado.add(lblResultado);
        mainPanel.add(pnlResultado, BorderLayout.CENTER);
    }

    // Helper de diseño
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

    public void setBotonListener(ActionListener listener) {
        btnOrdenar.addActionListener(listener);
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