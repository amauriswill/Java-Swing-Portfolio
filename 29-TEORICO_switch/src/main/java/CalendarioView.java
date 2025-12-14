package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalendarioView extends JFrame {

    private JTextField txtDia;
    private JButton btnVerificar;
    private JLabel lblDiaTexto, lblTipoDia;
    private JPanel pnlResultado;

    public CalendarioView() {
        // LookAndFeel omitido

        setTitle("Calendario Inteligente Accesible");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA ---
        JPanel topPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        topPanel.setOpaque(false);

        JLabel lblInstruccion = new JLabel("Ingrese número del día (1-7):");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 16));
        lblInstruccion.setForeground(Color.BLACK);
        lblInstruccion.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(lblInstruccion);

        txtDia = new JTextField();
        txtDia.setFont(new Font("Arial", Font.PLAIN, 28));
        txtDia.setHorizontalAlignment(JTextField.CENTER);
        txtDia.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        topPanel.add(txtDia);

        // BOTÓN ACCESIBLE
        btnVerificar = new JButton("CONSULTAR DÍA");
        btnVerificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVerificar.setBackground(new Color(0, 34, 68)); // Azul Oscuro
        btnVerificar.setForeground(Color.WHITE);
        btnVerificar.setOpaque(true);
        btnVerificar.setBorderPainted(false);
        btnVerificar.setFocusPainted(true);
        btnVerificar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        topPanel.add(btnVerificar);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- RESULTADO ---
        pnlResultado = new JPanel(new GridLayout(2, 1));
        pnlResultado.setBackground(Color.WHITE);
        pnlResultado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        lblDiaTexto = new JLabel("---");
        lblDiaTexto.setFont(new Font("Arial", Font.BOLD, 36));
        lblDiaTexto.setHorizontalAlignment(SwingConstants.CENTER);
        lblDiaTexto.setForeground(Color.decode("#003366"));

        lblTipoDia = new JLabel("");
        lblTipoDia.setFont(new Font("Arial", Font.ITALIC, 20));
        lblTipoDia.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoDia.setForeground(Color.GRAY);

        pnlResultado.add(lblDiaTexto);
        pnlResultado.add(lblTipoDia);

        mainPanel.add(pnlResultado, BorderLayout.CENTER);

        this.getRootPane().setDefaultButton(btnVerificar);
    }

    public String getDiaInput() { return txtDia.getText(); }

    public void setBotonListener(ActionListener listener) {
        btnVerificar.addActionListener(listener);
    }

    public void mostrarResultado(String nombreDia, String tipo, Color fondo) {
        lblDiaTexto.setText(nombreDia);
        lblTipoDia.setText(tipo);
        pnlResultado.setBackground(fondo);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
    }
}