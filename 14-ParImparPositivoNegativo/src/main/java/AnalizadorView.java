package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AnalizadorView extends JFrame {

    private JTextField txtNumero;
    private JButton btnAnalizar;
    private JLabel lblParidad, lblSigno;
    private JPanel pnlResultados;

    public AnalizadorView() {
        // Desactivamos LookAndFeel
        // try { UIManager.setLookAndFeel(...); } catch...

        setTitle("Analizador Numérico Accesible");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8")); // Gris azulado suave
        setContentPane(mainPanel);

        // --- ENTRADA ---
        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        inputPanel.setOpaque(false);

        JLabel lblInstruccion = new JLabel("Ingrese un número entero:");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 16));
        lblInstruccion.setForeground(Color.BLACK);
        lblInstruccion.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(lblInstruccion);

        txtNumero = new JTextField();
        txtNumero.setFont(new Font("Arial", Font.PLAIN, 28));
        txtNumero.setHorizontalAlignment(JTextField.CENTER);
        txtNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        inputPanel.add(txtNumero);

        // BOTÓN ACCESIBLE (Azul Profundo)
        btnAnalizar = new JButton("ANALIZAR NÚMERO");
        btnAnalizar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAnalizar.setBackground(new Color(0, 34, 68)); // Azul Marino
        btnAnalizar.setForeground(Color.WHITE);
        btnAnalizar.setOpaque(true);
        btnAnalizar.setBorderPainted(false);
        btnAnalizar.setFocusPainted(true);
        btnAnalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inputPanel.add(btnAnalizar);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // --- RESULTADOS (Dos tarjetas visuales) ---
        pnlResultados = new JPanel(new GridLayout(2, 1, 15, 15));
        pnlResultados.setOpaque(false);

        // Tarjeta 1: Paridad
        lblParidad = crearEtiquetaResultado("Esperando...");
        pnlResultados.add(lblParidad);

        // Tarjeta 2: Signo
        lblSigno = crearEtiquetaResultado("...");
        pnlResultados.add(lblSigno);

        mainPanel.add(pnlResultados, BorderLayout.CENTER);

        // Soporte para tecla Enter
        this.getRootPane().setDefaultButton(btnAnalizar);
    }

    private JLabel crearEtiquetaResultado(String textoInicial) {
        JLabel lbl = new JLabel(textoInicial);
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setOpaque(true); // Para poder pintar el fondo
        lbl.setBackground(Color.WHITE);
        lbl.setForeground(Color.GRAY);
        lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return lbl;
    }

    // --- MÉTODOS PÚBLICOS ---
    public String getNumeroInput() { return txtNumero.getText(); }

    public void setBotonListener(ActionListener listener) {
        btnAnalizar.addActionListener(listener);
    }

    public void mostrarResultados(String textoParidad, Color bgParidad, Color fgParidad,
                                  String textoSigno, Color bgSigno, Color fgSigno) {
        // Actualizar tarjeta Paridad
        lblParidad.setText(textoParidad);
        lblParidad.setBackground(bgParidad);
        lblParidad.setForeground(fgParidad);

        // Actualizar tarjeta Signo
        lblSigno.setText(textoSigno);
        lblSigno.setBackground(bgSigno);
        lblSigno.setForeground(fgSigno);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
    }
}