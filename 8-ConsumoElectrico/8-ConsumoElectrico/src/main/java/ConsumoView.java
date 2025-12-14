package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ConsumoView extends JFrame {

    private JTextField txtKilo;
    private JLabel lblTotal;
    private JPanel pnlResultado;
    private JButton btnCalcular;

    public ConsumoView() {
        // Desactivar LookAndFeel para colores reales
        // try { UIManager.setLookAndFeel(...); } catch... (Omitido)

        setTitle("Calculadora Eléctrica");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        inputPanel.setOpaque(false);

        JLabel lblInstruccion = new JLabel("Consumo del Mes (kW):");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 16));
        lblInstruccion.setForeground(Color.BLACK);
        inputPanel.add(lblInstruccion);

        txtKilo = new JTextField();
        txtKilo.setFont(new Font("Arial", Font.PLAIN, 24));
        txtKilo.setHorizontalAlignment(JTextField.CENTER);
        txtKilo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        inputPanel.add(txtKilo);

        // Espaciador
        inputPanel.add(Box.createVerticalStrut(10));

        // BOTÓN ACCESIBLE
        btnCalcular = new JButton("CALCULAR PAGO");
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

        lblTotal = new JLabel("Esperando lectura...");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 22));
        lblTotal.setForeground(Color.GRAY);

        pnlResultado.add(lblTotal);
        mainPanel.add(pnlResultado, BorderLayout.CENTER);
    }

    public String getKiloInput() { return txtKilo.getText(); }

    public void setBotonListener(ActionListener listener) {
        btnCalcular.addActionListener(listener);
    }

    public void mostrarResultado(String texto, Color fondo, Color textoColor) {
        lblTotal.setText(texto);
        lblTotal.setForeground(textoColor);
        pnlResultado.setBackground(fondo);
        pnlResultado.requestFocusInWindow();
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Lectura", JOptionPane.ERROR_MESSAGE);
    }
}