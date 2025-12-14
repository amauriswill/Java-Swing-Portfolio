package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class DivisionView extends JFrame {

    private JTextField txtNum1, txtNum2;
    private JButton btnCalcular;
    private JLabel lblMensaje, lblEcuacion;
    private JPanel pnlResultado;

    public DivisionView() {
        setTitle("Divisor Inteligente Accesible");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA ---
        JPanel inputPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        inputPanel.setOpaque(false);

        agregarCampo(inputPanel, "Primer Número:", txtNum1 = new JTextField());
        agregarCampo(inputPanel, "Segundo Número:", txtNum2 = new JTextField());

        // Espacio
        inputPanel.add(Box.createVerticalStrut(10));

        // BOTÓN ACCESIBLE
        btnCalcular = new JButton("CALCULAR");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 16));
        btnCalcular.setBackground(new Color(0, 34, 68));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setOpaque(true);
        btnCalcular.setBorderPainted(false);
        btnCalcular.setFocusPainted(true);
        btnCalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inputPanel.add(btnCalcular);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // --- RESULTADO ---
        pnlResultado = new JPanel(new GridLayout(2, 1));
        pnlResultado.setBackground(Color.WHITE);
        pnlResultado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        lblMensaje = new JLabel("Esperando datos...");
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 18));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setForeground(Color.GRAY);

        lblEcuacion = new JLabel("");
        lblEcuacion.setFont(new Font("Arial", Font.BOLD, 24));
        lblEcuacion.setHorizontalAlignment(SwingConstants.CENTER);
        lblEcuacion.setForeground(Color.decode("#003366"));

        pnlResultado.add(lblMensaje);
        pnlResultado.add(lblEcuacion);

        mainPanel.add(pnlResultado, BorderLayout.CENTER);

        this.getRootPane().setDefaultButton(btnCalcular);
    }

    private void agregarCampo(JPanel panel, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.BLACK);

        field.setFont(new Font("Arial", Font.PLAIN, 18));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        panel.add(label);
        panel.add(field);
    }

    // --- MÉTODOS PÚBLICOS ---
    public String getNum1() { return txtNum1.getText(); }
    public String getNum2() { return txtNum2.getText(); }

    public void setBotonListener(ActionListener listener) {
        btnCalcular.addActionListener(listener);
    }

    public void mostrarExito(String mensaje, String ecuacion) {
        pnlResultado.setBackground(new Color(209, 231, 221)); // Verde Claro
        lblMensaje.setText(mensaje);
        lblMensaje.setForeground(new Color(15, 81, 50)); // Verde Oscuro

        lblEcuacion.setText(ecuacion);
        lblEcuacion.setVisible(true);
    }

    public void mostrarError(String mensaje) {
        pnlResultado.setBackground(new Color(255, 235, 238)); // Rojo Claro
        lblMensaje.setText(mensaje);
        lblMensaje.setForeground(new Color(183, 28, 28)); // Rojo Oscuro

        lblEcuacion.setText(""); // Limpiar ecuación
    }

    public void mostrarAlerta(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error de Datos", JOptionPane.WARNING_MESSAGE);
    }
}