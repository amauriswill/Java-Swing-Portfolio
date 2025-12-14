package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CirculoView extends JFrame {

    private JTextField txtRadio;
    private JLabel lblResultado;
    private JPanel pnlResultado;
    private JButton btnCalcular;

    public CirculoView() {
        // Desactivamos LookAndFeel para asegurar colores exactos
        // try { UIManager.setLookAndFeel(...); } catch... (Omitido)

        setTitle("Calculadora de Área Accesible");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA ---
        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        inputPanel.setOpaque(false);

        JLabel lblInstruccion = new JLabel("Ingrese el Radio del Círculo:");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 16));
        lblInstruccion.setForeground(Color.BLACK);
        inputPanel.add(lblInstruccion);

        txtRadio = new JTextField();
        txtRadio.setFont(new Font("Arial", Font.PLAIN, 24));
        txtRadio.setHorizontalAlignment(JTextField.CENTER);
        txtRadio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        inputPanel.add(txtRadio);

        // BOTÓN ACCESIBLE (Azul Profundo)
        btnCalcular = new JButton("CALCULAR ÁREA");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 16));
        btnCalcular.setBackground(new Color(0, 34, 68)); // Azul Oscuro
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setOpaque(true);
        btnCalcular.setBorderPainted(false);
        btnCalcular.setFocusPainted(true);
        btnCalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inputPanel.add(btnCalcular);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // --- RESULTADO ---
        pnlResultado = new JPanel(new GridBagLayout());
        pnlResultado.setBackground(Color.WHITE);
        pnlResultado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        lblResultado = new JLabel("Esperando datos...");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 20));
        lblResultado.setForeground(Color.GRAY);

        pnlResultado.add(lblResultado);
        mainPanel.add(pnlResultado, BorderLayout.CENTER);
    }

    public String getRadioInput() { return txtRadio.getText(); }

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
        JOptionPane.showMessageDialog(this, mensaje, "Error Matemático", JOptionPane.ERROR_MESSAGE);
    }
}