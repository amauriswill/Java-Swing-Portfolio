package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class AgendaView extends JFrame {

    private JTextField txtCedula, txtNombre, txtApellido, txtTelefono, txtDireccion, txtBuscar;
    private JButton btnGuardar, btnLimpiar;
    private JTable tablaAgenda;
    private DefaultTableModel modeloTabla;

    public AgendaView() {
        setTitle("Agenda Telefónica Profesional");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- IZQUIERDA: FORMULARIO ---
        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        formPanel.setOpaque(false);
        formPanel.setPreferredSize(new Dimension(300, 0));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nuevo Contacto"));

        agregarCampo(formPanel, "Cédula/ID:", txtCedula = new JTextField());
        agregarCampo(formPanel, "Nombres:", txtNombre = new JTextField());
        agregarCampo(formPanel, "Apellidos:", txtApellido = new JTextField());
        agregarCampo(formPanel, "Teléfono:", txtTelefono = new JTextField());
        agregarCampo(formPanel, "Dirección:", txtDireccion = new JTextField());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setOpaque(false);
        btnGuardar = crearBoton("GUARDAR", new Color(0, 34, 68));
        btnLimpiar = crearBoton("LIMPIAR", new Color(108, 117, 125));

        btnPanel.add(btnGuardar);
        btnPanel.add(btnLimpiar);
        formPanel.add(btnPanel);

        mainPanel.add(formPanel, BorderLayout.WEST);

        // --- CENTRO: TABLA Y BUSCADOR ---
        JPanel centroPanel = new JPanel(new BorderLayout(10, 10));
        centroPanel.setOpaque(false);

        // Barra de búsqueda
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setOpaque(false);
        searchPanel.add(new JLabel("Buscar:"), BorderLayout.WEST);
        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
        searchPanel.add(txtBuscar, BorderLayout.CENTER);

        centroPanel.add(searchPanel, BorderLayout.NORTH);

        // Tabla
        String[] cols = {"CÉDULA", "NOMBRE", "APELLIDO", "TELÉFONO", "DIRECCIÓN"};
        modeloTabla = new DefaultTableModel(null, cols) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaAgenda = new JTable(modeloTabla);
        estilizarTabla(tablaAgenda);

        JScrollPane scroll = new JScrollPane(tablaAgenda);
        centroPanel.add(scroll, BorderLayout.CENTER);

        mainPanel.add(centroPanel, BorderLayout.CENTER);

        this.getRootPane().setDefaultButton(btnGuardar);
    }

    // --- Helpers ---
    private void agregarCampo(JPanel p, String lbl, JTextField txt) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        JLabel l = new JLabel(lbl);
        l.setFont(new Font("Arial", Font.BOLD, 12));
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        row.add(l, BorderLayout.NORTH);
        row.add(txt, BorderLayout.CENTER);
        p.add(row);
    }

    private JButton crearBoton(String t, Color c) {
        JButton b = new JButton(t);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    private void estilizarTabla(JTable t) {
        t.setFont(new Font("Arial", Font.PLAIN, 14));
        t.setRowHeight(25);
        t.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        t.getTableHeader().setBackground(Color.decode("#333333"));
        t.getTableHeader().setForeground(Color.WHITE);
    }

    // --- Getters ---
    public String getCedula() { return txtCedula.getText(); }
    public String getNombre() { return txtNombre.getText(); }
    public String getApellido() { return txtApellido.getText(); }
    public String getTelefono() { return txtTelefono.getText(); }
    public String getDireccion() { return txtDireccion.getText(); }
    public String getBusqueda() { return txtBuscar.getText(); }

    public void limpiarFormulario() {
        txtCedula.setText(""); txtNombre.setText(""); txtApellido.setText("");
        txtTelefono.setText(""); txtDireccion.setText("");
        txtCedula.requestFocus();
    }

    public void llenarTabla(java.util.List<Contacto> contactos) {
        modeloTabla.setRowCount(0);
        for (Contacto c : contactos) {
            modeloTabla.addRow(c.toArray());
        }
    }

    public void setListeners(ActionListener action, KeyListener key) {
        btnGuardar.addActionListener(action);
        btnLimpiar.addActionListener(action);
        txtBuscar.addKeyListener(key); // Escuchar tecleo para búsqueda en vivo
    }

    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}