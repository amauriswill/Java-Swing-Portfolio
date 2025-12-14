package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class WhileView extends JFrame {

    // Componentes Pestaña 1 (Secuencia)
    private JTextField txtLimite;
    private JButton btnGenerar;
    private JTextArea txtResultadoSecuencia;

    // Componentes Pestaña 2 (Evaluador)
    private JTextField txtNumeroEvaluar;
    private JButton btnEvaluar;
    private JTextArea txtHistorial;
    private JLabel lblEstado;

    public WhileView() {
        setTitle("Laboratorio Bucle While");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Principal con Pestañas
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Arial", Font.BOLD, 14));

        // --- PESTAÑA 1: SECUENCIA ---
        JPanel pnlSecuencia = new JPanel(new BorderLayout(15, 15));
        pnlSecuencia.setBorder(new EmptyBorder(20, 20, 20, 20));
        pnlSecuencia.setBackground(Color.decode("#f0f4f8"));

        JPanel pnlInput1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlInput1.setOpaque(false);
        pnlInput1.add(crearLabel("Contar hasta:"));
        txtLimite = crearInput();
        txtLimite.setText("10"); // Valor por defecto
        pnlInput1.add(txtLimite);
        btnGenerar = crearBoton("GENERAR WHILE", new Color(0, 34, 68));
        pnlInput1.add(btnGenerar);

        txtResultadoSecuencia = new JTextArea();
        txtResultadoSecuencia.setFont(new Font("Monospaced", Font.PLAIN, 18));
        txtResultadoSecuencia.setEditable(false);

        pnlSecuencia.add(pnlInput1, BorderLayout.NORTH);
        pnlSecuencia.add(new JScrollPane(txtResultadoSecuencia), BorderLayout.CENTER);

        tabs.addTab("1. Secuencia", pnlSecuencia);

        // --- PESTAÑA 2: EVALUADOR ---
        JPanel pnlEvaluador = new JPanel(new BorderLayout(15, 15));
        pnlEvaluador.setBorder(new EmptyBorder(20, 20, 20, 20));
        pnlEvaluador.setBackground(Color.decode("#f0f4f8"));

        JPanel pnlInput2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlInput2.setOpaque(false);
        pnlInput2.add(crearLabel("Ingrese Número (0 para fin):"));
        txtNumeroEvaluar = crearInput();
        pnlInput2.add(txtNumeroEvaluar);
        btnEvaluar = crearBoton("EVALUAR", new Color(25, 135, 84)); // Verde
        pnlInput2.add(btnEvaluar);

        // Panel central con historial y estado
        JPanel pnlCentro = new JPanel(new BorderLayout(10, 10));
        pnlCentro.setOpaque(false);

        lblEstado = new JLabel("Esperando dato...");
        lblEstado.setFont(new Font("Arial", Font.BOLD, 22));
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstado.setOpaque(true);
        lblEstado.setBackground(Color.WHITE);
        lblEstado.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblEstado.setPreferredSize(new Dimension(0, 60));

        txtHistorial = new JTextArea("--- HISTORIAL DE SESIÓN ---\n");
        txtHistorial.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtHistorial.setEditable(false);

        pnlCentro.add(lblEstado, BorderLayout.NORTH);
        pnlCentro.add(new JScrollPane(txtHistorial), BorderLayout.CENTER);

        pnlEvaluador.add(pnlInput2, BorderLayout.NORTH);
        pnlEvaluador.add(pnlCentro, BorderLayout.CENTER);

        tabs.addTab("2. Evaluador (+/-)", pnlEvaluador);

        add(tabs);
    }

    // --- Helpers ---
    private JLabel crearLabel(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        return l;
    }

    private JTextField crearInput() {
        JTextField t = new JTextField(6);
        t.setFont(new Font("Arial", Font.PLAIN, 18));
        t.setHorizontalAlignment(JTextField.CENTER);
        return t;
    }

    private JButton crearBoton(String t, Color c) {
        JButton b = new JButton(t);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    // --- Métodos Públicos ---
    public String getLimite() { return txtLimite.getText(); }
    public String getNumeroEvaluar() { return txtNumeroEvaluar.getText(); }

    public void setSecuenciaTexto(List<Integer> numeros) {
        StringBuilder sb = new StringBuilder();
        for (Integer n : numeros) {
            sb.append("Número: ").append(n).append("\n");
        }
        txtResultadoSecuencia.setText(sb.toString());
    }

    public void actualizarEstado(String texto, Color fondo, Color letra) {
        lblEstado.setText(texto);
        lblEstado.setBackground(fondo);
        lblEstado.setForeground(letra);
    }

    public void agregarHistorial(String texto) {
        txtHistorial.append(texto + "\n");
    }

    public void limpiarInputEvaluar() {
        txtNumeroEvaluar.setText("");
        txtNumeroEvaluar.requestFocus();
    }

    public void setListeners(ActionListener l) {
        btnGenerar.addActionListener(l);
        btnEvaluar.addActionListener(l);
    }

    public JButton getBtnGenerar() { return btnGenerar; }
    public JButton getBtnEvaluar() { return btnEvaluar; }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}