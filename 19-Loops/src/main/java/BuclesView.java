package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class BuclesView extends JFrame {

    private JTextField txtNumero;
    private JButton btnWhile, btnDoWhile, btnFor, btnLimpiar;
    private JList<Integer> listWhile, listDoWhile, listFor;
    private DefaultListModel<Integer> modelWhile, modelDoWhile, modelFor;

    public BuclesView() {
        // LookAndFeel omitido

        setTitle("Laboratorio de Bucles Accesible");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- PANEL SUPERIOR (Input) ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.setOpaque(false);

        JLabel lblInstruccion = new JLabel("Número Inicial (1-100):");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(lblInstruccion);

        txtNumero = new JTextField(5);
        txtNumero.setFont(new Font("Arial", Font.PLAIN, 24));
        txtNumero.setHorizontalAlignment(JTextField.CENTER);
        txtNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        topPanel.add(txtNumero);

        btnLimpiar = crearBoton("LIMPIAR TODO", new Color(108, 117, 125));
        topPanel.add(btnLimpiar);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- PANEL CENTRAL (3 Columnas) ---
        JPanel gridPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        gridPanel.setOpaque(false);

        // Columna 1: WHILE
        modelWhile = new DefaultListModel<>();
        listWhile = crearLista(modelWhile);
        btnWhile = crearBoton("Ejecutar WHILE", new Color(0, 34, 68));
        gridPanel.add(crearPanelColumna("Bucle WHILE", btnWhile, listWhile));

        // Columna 2: DO-WHILE
        modelDoWhile = new DefaultListModel<>();
        listDoWhile = crearLista(modelDoWhile);
        btnDoWhile = crearBoton("Ejecutar DO-WHILE", new Color(0, 34, 68));
        gridPanel.add(crearPanelColumna("Bucle DO-WHILE", btnDoWhile, listDoWhile));

        // Columna 3: FOR
        modelFor = new DefaultListModel<>();
        listFor = crearLista(modelFor);
        btnFor = crearBoton("Ejecutar FOR", new Color(0, 34, 68));
        gridPanel.add(crearPanelColumna("Bucle FOR", btnFor, listFor));

        mainPanel.add(gridPanel, BorderLayout.CENTER);
    }

    // --- Componentes Reutilizables ---

    private JPanel crearPanelColumna(String titulo, JButton boton, JList<Integer> lista) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTitulo, BorderLayout.NORTH);

        panel.add(boton, BorderLayout.SOUTH);

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JList<Integer> crearLista(DefaultListModel<Integer> model) {
        JList<Integer> list = new JList<>(model);
        list.setFont(new Font("Arial", Font.PLAIN, 16));
        list.setFixedCellHeight(30);
        return list;
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

    // --- Métodos Públicos ---
    public String getInputNumero() { return txtNumero.getText(); }
    public void limpiarInput() { txtNumero.setText(""); txtNumero.requestFocus(); }

    public void actualizarListaWhile(List<Integer> datos) { llenarModelo(modelWhile, datos); }
    public void actualizarListaDoWhile(List<Integer> datos) { llenarModelo(modelDoWhile, datos); }
    public void actualizarListaFor(List<Integer> datos) { llenarModelo(modelFor, datos); }

    public void limpiarTodasLasListas() {
        modelWhile.clear();
        modelDoWhile.clear();
        modelFor.clear();
    }

    private void llenarModelo(DefaultListModel<Integer> modelo, List<Integer> datos) {
        modelo.clear();
        for (Integer n : datos) {
            modelo.addElement(n);
        }
    }

    public void setListeners(ActionListener listener) {
        btnWhile.addActionListener(listener);
        btnDoWhile.addActionListener(listener);
        btnFor.addActionListener(listener);
        btnLimpiar.addActionListener(listener);
    }

    // Getters para identificar botones
    public JButton getBtnWhile() { return btnWhile; }
    public JButton getBtnDoWhile() { return btnDoWhile; }
    public JButton getBtnFor() { return btnFor; }
    public JButton getBtnLimpiar() { return btnLimpiar; }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}