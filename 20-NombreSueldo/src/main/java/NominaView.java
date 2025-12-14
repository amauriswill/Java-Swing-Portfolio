package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class NominaView extends JFrame {

    private JTextField txtNombre, txtSueldo;
    private JButton btnAgregar, btnReiniciar;
    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;
    private JLabel lblGanadorNombre, lblGanadorSueldo;
    private JPanel pnlGanador;

    public NominaView() {
        // LookAndFeel omitido

        setTitle("Sistema de Nómina Accesible");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- PANEL SUPERIOR (EL GANADOR) ---
        pnlGanador = new JPanel(new GridLayout(1, 2));
        pnlGanador.setBackground(Color.WHITE);
        pnlGanador.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pnlGanador.setPreferredSize(new Dimension(0, 80));

        JLabel lblTituloGanador = new JLabel("MEJOR PAGADO:", SwingConstants.CENTER);
        lblTituloGanador.setFont(new Font("Arial", Font.BOLD, 16));
        lblTituloGanador.setForeground(Color.GRAY);

        JPanel infoGanador = new JPanel(new FlowLayout(FlowLayout.CENTER));
        infoGanador.setOpaque(false);

        lblGanadorNombre = new JLabel("---");
        lblGanadorNombre.setFont(new Font("Arial", Font.BOLD, 20));
        lblGanadorNombre.setForeground(Color.BLACK);

        lblGanadorSueldo = new JLabel("($ 0.00)");
        lblGanadorSueldo.setFont(new Font("Arial", Font.BOLD, 20));
        lblGanadorSueldo.setForeground(new Color(15, 81, 50)); // Verde Oscuro

        infoGanador.add(lblGanadorNombre);
        infoGanador.add(lblGanadorSueldo);

        pnlGanador.add(lblTituloGanador);
        pnlGanador.add(infoGanador);

        mainPanel.add(pnlGanador, BorderLayout.NORTH);

        // --- IZQUIERDA (FORMULARIO) ---
        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        formPanel.setOpaque(false);
        formPanel.setPreferredSize(new Dimension(250, 0));

        agregarCampo(formPanel, "Nombre Empleado:", txtNombre = new JTextField());
        agregarCampo(formPanel, "Sueldo Mensual ($):", txtSueldo = new JTextField());

        // Espacio
        formPanel.add(Box.createVerticalStrut(10));

        btnAgregar = crearBoton("AGREGAR", new Color(0, 34, 68)); // Azul Oscuro
        formPanel.add(btnAgregar);

        btnReiniciar = crearBoton("REINICIAR", new Color(132, 32, 41)); // Rojo Oscuro
        formPanel.add(btnReiniciar);

        mainPanel.add(formPanel, BorderLayout.WEST);

        // --- CENTRO (TABLA) ---
        String[] columnas = {"NOMBRE", "SUELDO"};
        modeloTabla = new DefaultTableModel(null, columnas);
        tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setFont(new Font("Arial", Font.PLAIN, 16));
        tablaEmpleados.setRowHeight(30);
        tablaEmpleados.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaEmpleados.getTableHeader().setBackground(Color.decode("#333333"));
        tablaEmpleados.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tablaEmpleados);
        mainPanel.add(scroll, BorderLayout.CENTER);

        this.getRootPane().setDefaultButton(btnAgregar);
    }

    private void agregarCampo(JPanel panel, String texto, JTextField campo) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(label);
        panel.add(campo);
    }

    private JButton crearBoton(String texto, Color bg) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // --- MÉTODOS PÚBLICOS ---
    public String getNombre() { return txtNombre.getText(); }
    public String getSueldo() { return txtSueldo.getText(); }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtSueldo.setText("");
        txtNombre.requestFocus();
    }

    public void limpiarTabla() { modeloTabla.setRowCount(0); }

    public void agregarFila(Object[] datos) { modeloTabla.addRow(datos); }

    public void actualizarGanador(String nombre, String sueldo) {
        lblGanadorNombre.setText(nombre);
        lblGanadorSueldo.setText(sueldo);
        // Efecto visual: Fondo amarillo claro para destacar
        pnlGanador.setBackground(new Color(255, 243, 205));
    }

    public void resetGanador() {
        lblGanadorNombre.setText("---");
        lblGanadorSueldo.setText("($ 0.00)");
        pnlGanador.setBackground(Color.WHITE);
    }

    public void setListeners(ActionListener listener) {
        btnAgregar.addActionListener(listener);
        btnReiniciar.addActionListener(listener);
    }

    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnReiniciar() { return btnReiniciar; }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error de Datos", JOptionPane.ERROR_MESSAGE);
    }
}