package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PrecioVentaView extends JFrame {

    private JTextField txtCosto, txtPorcentaje;
    private JLabel lblGanancia, lblPrecioFinal;
    private JButton btnCalcular;
    private JPanel pnlResultados;

    public PrecioVentaView() {
        // Look & Feel omitido para garantizar colores personalizados

        setTitle("Calculadora de Precios Retail");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ZONA DE ENTRADA ---
        JPanel inputPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        inputPanel.setOpaque(false);

        agregarCampo(inputPanel, "Costo del Producto ($):", txtCosto = new JTextField());
        agregarCampo(inputPanel, "Margen de Ganancia (%):", txtPorcentaje = new JTextField());

        // Espacio
        inputPanel.add(Box.createVerticalStrut(10));

        // BOTÓN ACCESIBLE (Azul Corporativo Oscuro)
        btnCalcular = new JButton("CALCULAR PRECIO DE VENTA");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 16));
        btnCalcular.setBackground(new Color(0, 34, 68));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setOpaque(true);
        btnCalcular.setBorderPainted(false);
        btnCalcular.setFocusPainted(true);
        btnCalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inputPanel.add(btnCalcular);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // --- ZONA DE RESULTADOS ---
        pnlResultados = new JPanel(new GridLayout(2, 1, 10, 10));
        pnlResultados.setBackground(Color.WHITE);
        pnlResultados.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                new EmptyBorder(15, 15, 15, 15)
        ));

        // Sub-paneles para organizar etiquetas
        lblGanancia = crearEtiquetaResultado("Ganancia Neta:", "$ 0.00");
        lblPrecioFinal = crearEtiquetaResultado("PRECIO DE VENTA:", "$ 0.00");

        pnlResultados.add(lblGanancia);
        pnlResultados.add(lblPrecioFinal);

        mainPanel.add(pnlResultados, BorderLayout.CENTER);
    }

    // Helper para inputs
    private void agregarCampo(JPanel panel, String texto, JTextField campo) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.decode("#101828")); // Negro suave
        panel.add(label);

        campo.setFont(new Font("Arial", Font.PLAIN, 18));
        campo.setHorizontalAlignment(JTextField.RIGHT); // Números alineados a la derecha
        campo.setBorder(BorderFactory.createLineBorder(Color.decode("#667085"), 1));
        panel.add(campo);
    }

    // Helper para crear bloques de resultado
    private JLabel crearEtiquetaResultado(String titulo, String valorInicial) {
        JLabel lbl = new JLabel("<html>" + titulo + "<br/><span style='font-size:18px; color:#000'>" + valorInicial + "</span></html>");
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setForeground(Color.GRAY);
        return lbl;
    }

    // --- MÉTODOS PÚBLICOS ---
    public String getCosto() { return txtCosto.getText(); }
    public String getPorcentaje() { return txtPorcentaje.getText(); }

    public void setBotonListener(ActionListener listener) {
        btnCalcular.addActionListener(listener);
    }

    public void mostrarResultados(String ganancia, String precioFinal) {
        // Actualizamos usando HTML básico para dar formato dentro del JLabel
        lblGanancia.setText("<html>Ganancia Neta:<br/><span style='font-size:20px; color:#027a48'>" + ganancia + "</span></html>");
        lblPrecioFinal.setText("<html>PRECIO DE VENTA:<br/><span style='font-size:26px; color:#002244'>" + precioFinal + "</span></html>");
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error Financiero", JOptionPane.ERROR_MESSAGE);
    }
}