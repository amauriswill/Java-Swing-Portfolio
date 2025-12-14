package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class NotasView extends JFrame {

    private JTextField txtNota;
    private JButton btnAgregar, btnCalcular, btnReiniciar;
    private JTable tablaNotas;
    private DefaultTableModel modeloTabla;
    private JLabel lblPromedio, lblInfo;

    public NotasView() {
        // LookAndFeel omitido

        setTitle("Gestor de Calificaciones Accesible");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA DE DATOS (ARRIBA) ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        topPanel.setOpaque(false);

        JLabel lblInstruccion = new JLabel("Ingrese Nota (0-100):");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 16));
        lblInstruccion.setForeground(Color.BLACK);
        topPanel.add(lblInstruccion);

        txtNota = new JTextField(5);
        txtNota.setFont(new Font("Arial", Font.PLAIN, 20));
        txtNota.setHorizontalAlignment(JTextField.CENTER);
        txtNota.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        topPanel.add(txtNota);

        btnAgregar = crearBoton("AGREGAR ALUMNO", new Color(0, 34, 68)); // Azul Oscuro
        topPanel.add(btnAgregar);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- TABLA DE DATOS (CENTRO) ---
        // Columnas: # Alumno, Nota, Estado (Vacío hasta calcular)
        String[] columnas = {"ALUMNO", "NOTA", "ESTADO"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        tablaNotas = new JTable(modeloTabla);
        tablaNotas.setFont(new Font("Arial", Font.PLAIN, 16));
        tablaNotas.setRowHeight(30);
        tablaNotas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaNotas.getTableHeader().setBackground(Color.decode("#333333"));
        tablaNotas.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tablaNotas);
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainPanel.add(scroll, BorderLayout.CENTER);

        // --- PANEL DE ACCIONES Y RESULTADO (ABAJO) ---
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setOpaque(false);

        // Botones de acción
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlBotones.setOpaque(false);

        btnCalcular = crearBoton("CALCULAR PROMEDIO", new Color(15, 81, 50)); // Verde Oscuro
        btnReiniciar = crearBoton("REINICIAR TODO", new Color(132, 32, 41)); // Rojo Oscuro

        pnlBotones.add(btnCalcular);
        pnlBotones.add(btnReiniciar);
        bottomPanel.add(pnlBotones, BorderLayout.NORTH);

        // Tarjeta de Resultado
        JPanel pnlResultado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlResultado.setBackground(Color.WHITE);
        pnlResultado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        lblPromedio = new JLabel("Promedio del Grupo: -");
        lblPromedio.setFont(new Font("Arial", Font.BOLD, 22));
        lblPromedio.setForeground(Color.decode("#003366"));

        pnlResultado.add(lblPromedio);
        bottomPanel.add(pnlResultado, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Enter para agregar rápido
        this.getRootPane().setDefaultButton(btnAgregar);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // --- MÉTODOS PÚBLICOS ---
    public String getNotaInput() { return txtNota.getText(); }
    public void limpiarInput() { txtNota.setText(""); txtNota.requestFocus(); }

    public void agregarFila(int numeroAlumno, double nota) {
        modeloTabla.addRow(new Object[]{"Alumno " + numeroAlumno, nota, "Pendiente..."});
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
        lblPromedio.setText("Promedio del Grupo: -");
    }

    public void actualizarResultadoFila(int fila, boolean superaPromedio) {
        String estado = superaPromedio ? "SUPERIOR A MEDIA" : "Normal / Bajo";
        modeloTabla.setValueAt(estado, fila, 2); // Actualiza columna 2 (Estado)
    }

    public void mostrarPromedio(double promedio) {
        lblPromedio.setText(String.format("Promedio del Grupo: %.2f", promedio));
    }

    public void setListeners(ActionListener listener) {
        btnAgregar.addActionListener(listener);
        btnCalcular.addActionListener(listener);
        btnReiniciar.addActionListener(listener);
    }

    // Getters para identificar botones
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnCalcular() { return btnCalcular; }
    public JButton getBtnReiniciar() { return btnReiniciar; }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Datos", JOptionPane.ERROR_MESSAGE);
    }
}