package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegistroView extends JFrame {

    private JTextField txtCodigo, txtNombre, txtApellidos;
    private JComboBox<String> cboEstadoCivil, cboSexo;
    private JTable tablaDatos;
    private DefaultTableModel modeloTabla;
    private JButton btnNuevo, btnAgregar, btnEliminar;

    public RegistroView() {
 

        setTitle("Gestión de Clientes Accesible");
        setSize(800, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- PANEL DE FORMULARIO (IZQUIERDA) ---
        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        formPanel.setOpaque(false);
        formPanel.setPreferredSize(new Dimension(300, 0)); 

        // Inputs
        agregarCampo(formPanel, "Código Cliente:", txtCodigo = new JTextField());
        agregarCampo(formPanel, "Nombres:", txtNombre = new JTextField());
        agregarCampo(formPanel, "Apellidos:", txtApellidos = new JTextField());

        // Combos
        cboEstadoCivil = new JComboBox<>(new String[]{"Seleccionar", "Casado", "Soltero", "Viudo", "Unión Libre"});
        agregarCombo(formPanel, "Estado Civil:", cboEstadoCivil);

        cboSexo = new JComboBox<>(new String[]{"Seleccionar", "Masculino", "Femenino"});
        agregarCombo(formPanel, "Sexo:", cboSexo);

        // Panel de Botones (Dentro del formulario)
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.setOpaque(false);

        btnNuevo = crearBoton("NUEVO", new Color(108, 117, 125)); 
        btnAgregar = crearBoton("AGREGAR", new Color(0, 34, 68)); 
        btnEliminar = crearBoton("ELIMINAR", new Color(132, 32, 41));

        btnPanel.add(btnNuevo);
        btnPanel.add(btnAgregar);
        btnPanel.add(btnEliminar);
        formPanel.add(btnPanel);

        mainPanel.add(formPanel, BorderLayout.WEST);

        // --- PANEL DE TABLA (CENTRO) ---
        String[] columnas = {"CÓDIGO", "NOMBRES", "APELLIDOS", "ESTADO", "SEXO"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaDatos = new JTable(modeloTabla);
        estilizarTabla(tablaDatos); 

        JScrollPane scrollPane = new JScrollPane(tablaDatos);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

    // --- MÉTODOS DE DISEÑO ---

    private void agregarCampo(JPanel panel, String labelText, JTextField field) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel l = new JLabel(labelText);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        p.add(l, BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        panel.add(p);
    }

    private void agregarCombo(JPanel panel, String labelText, JComboBox<String> combo) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel l = new JLabel(labelText);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        combo.setFont(new Font("Arial", Font.PLAIN, 16));
        p.add(l, BorderLayout.NORTH);
        p.add(combo, BorderLayout.CENTER);
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

    private void estilizarTabla(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 16)); 
        table.setRowHeight(30); 
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(33, 37, 41)); 
        table.getTableHeader().setForeground(Color.WHITE); 
        table.setSelectionBackground(new Color(255, 243, 205)); 
        table.setSelectionForeground(Color.BLACK);
    }

    // --- MÉTODOS PÚBLICOS PARA EL CONTROLADOR ---

    public String getCodigo() { return txtCodigo.getText(); }
    public String getNombre() { return txtNombre.getText(); }
    public String getApellidos() { return txtApellidos.getText(); }
    public String getEstadoCivil() { return (String) cboEstadoCivil.getSelectedItem(); }
    public String getSexo() { return (String) cboSexo.getSelectedItem(); }

    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        cboEstadoCivil.setSelectedIndex(0);
        cboSexo.setSelectedIndex(0);
        txtCodigo.requestFocus();
    }

    public void agregarFila(Object[] datos) {
        modeloTabla.addRow(datos);
    }

    public void eliminarFila(int filaIndex) {
        modeloTabla.removeRow(filaIndex);
    }

    public int getFilaSeleccionada() {
        return tablaDatos.getSelectedRow();
    }

    public void setListeners(ActionListener listener) {
        btnNuevo.addActionListener(listener);
        btnAgregar.addActionListener(listener);
        btnEliminar.addActionListener(listener);
    }


    public JButton getBtnNuevo() { return btnNuevo; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnEliminar() { return btnEliminar; }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
