package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class DigitoView extends JFrame {

    private JTextField txtEntrada;
    private JButton btnAnalizar;
    private JLabel lblResultado, lblIcono;
    private JPanel pnlResultado;

    public DigitoView() {
        // LookAndFeel omitido

        setTitle("Analizador de Caracteres Accesible");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA ---
        JPanel topPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        topPanel.setOpaque(false);

        JLabel lblInstruccion = new JLabel("Ingrese UN solo carácter:");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 16));
        lblInstruccion.setForeground(Color.BLACK);
        lblInstruccion.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(lblInstruccion);

        txtEntrada = new JTextField();
        txtEntrada.setFont(new Font("Arial", Font.PLAIN, 32)); // Input gigante
        txtEntrada.setHorizontalAlignment(JTextField.CENTER);
        txtEntrada.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        // Limitamos visualmente el ancho para sugerir que es poco texto
        topPanel.add(txtEntrada);

        // BOTÓN ACCESIBLE
        btnAnalizar = new JButton("ANALIZAR");
        btnAnalizar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAnalizar.setBackground(new Color(0, 34, 68)); // Azul Oscuro
        btnAnalizar.setForeground(Color.WHITE);
        btnAnalizar.setOpaque(true);
        btnAnalizar.setBorderPainted(false);
        btnAnalizar.setFocusPainted(true);
        btnAnalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        topPanel.add(btnAnalizar);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- RESULTADO ---
        pnlResultado = new JPanel(new BorderLayout());
        pnlResultado.setBackground(Color.WHITE);
        pnlResultado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        lblIcono = new JLabel("?");
        lblIcono.setFont(new Font("Arial", Font.BOLD, 60));
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcono.setForeground(Color.GRAY);

        lblResultado = new JLabel("Esperando...");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 20));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setForeground(Color.GRAY);
        lblResultado.setBorder(new EmptyBorder(0, 0, 20, 0));

        pnlResultado.add(lblIcono, BorderLayout.CENTER);
        pnlResultado.add(lblResultado, BorderLayout.SOUTH);

        mainPanel.add(pnlResultado, BorderLayout.CENTER);

        this.getRootPane().setDefaultButton(btnAnalizar);
    }

    public String getTextoEntrada() { return txtEntrada.getText().trim(); }

    public void limpiarEntrada() { txtEntrada.setText(""); txtEntrada.requestFocus(); }

    public void setBotonListener(ActionListener listener) {
        btnAnalizar.addActionListener(listener);
    }

    public void mostrarResultado(String texto, String simbolo, Color fondo, Color textoColor) {
        lblResultado.setText(texto);
        lblIcono.setText(simbolo);

        lblResultado.setForeground(textoColor);
        lblIcono.setForeground(textoColor);
        pnlResultado.setBackground(fondo);

        // Accesibilidad
        pnlResultado.requestFocusInWindow();
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
    }
}