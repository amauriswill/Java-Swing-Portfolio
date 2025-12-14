package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class PrestamoView extends JFrame {

    private JTextField txtMonto, txtTasa, txtMeses;
    private JButton btnCalcular, btnLimpiar, btnPDF, btnCSV;
    private JTable tablaAmortizacion;
    private DefaultTableModel modeloTabla;

    public PrestamoView() {
        setTitle("Simulador de Crédito PRO");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA ---
        JPanel formPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        formPanel.setOpaque(false);

        agregarCampo(formPanel, "Monto ($):", txtMonto = new JTextField());
        agregarCampo(formPanel, "Tasa Anual (%):", txtTasa = new JTextField());
        agregarCampo(formPanel, "Plazo (Meses):", txtMeses = new JTextField());

        // Panel de Botones (Ahora con 4 botones)
        JPanel btnPanel = new JPanel(new GridLayout(4, 1, 0, 5));
        btnPanel.setOpaque(false);

        btnCalcular = crearBoton("CALCULAR", new Color(0, 34, 68));
        btnLimpiar = crearBoton("LIMPIAR", new Color(108, 117, 125));
        btnPDF = crearBoton("EXPORTAR PDF", new Color(220, 53, 69));
        btnCSV = crearBoton("EXPORTAR CSV", new Color(25, 135, 84));

        btnPanel.add(btnCalcular);
        btnPanel.add(btnLimpiar);
        btnPanel.add(btnPDF);
        btnPanel.add(btnCSV);

        formPanel.add(btnPanel);
        mainPanel.add(formPanel, BorderLayout.NORTH);

        // --- TABLA ---
        String[] columnas = {"Nº CUOTA", "PAGO MENSUAL", "INTERÉS", "ABONO CAPITAL", "SALDO RESTANTE"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        tablaAmortizacion = new JTable(modeloTabla);
        tablaAmortizacion.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaAmortizacion.setRowHeight(25);
        tablaAmortizacion.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaAmortizacion.getTableHeader().setBackground(Color.decode("#333333"));
        tablaAmortizacion.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tablaAmortizacion);
        mainPanel.add(scroll, BorderLayout.CENTER);

        this.getRootPane().setDefaultButton(btnCalcular);
    }

    private void agregarCampo(JPanel panel, String label, JTextField field) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel l = new JLabel(label);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        p.add(l, BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        panel.add(p);
    }

    private JButton crearBoton(String texto, Color bg) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // --- MÉTODOS PÚBLICOS ---
    public String getMonto() { return txtMonto.getText(); }
    public String getTasa() { return txtTasa.getText(); }
    public String getMeses() { return txtMeses.getText(); }
    public JTable getTabla() { return tablaAmortizacion; } // Necesario para exportar

    public void limpiarFormulario() {
        txtMonto.setText("");
        txtTasa.setText("");
        txtMeses.setText("");
        modeloTabla.setRowCount(0);
        txtMonto.requestFocus();
    }

    public void llenarTabla(java.util.List<Object[]> filas) {
        modeloTabla.setRowCount(0);
        for (Object[] fila : filas) {
            modeloTabla.addRow(fila);
        }
    }

    public void setListeners(ActionListener listener) {
        btnCalcular.addActionListener(listener);
        btnLimpiar.addActionListener(listener);
        btnPDF.addActionListener(listener);
        btnCSV.addActionListener(listener);
    }

    public JButton getBtnCalcular() { return btnCalcular; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JButton getBtnPDF() { return btnPDF; }
    public JButton getBtnCSV() { return btnCSV; }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}