package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class LlamadasView extends JFrame {

    private JTextField txtMinutos;
    private JRadioButton rbClaro, rbAltice, rbViva;
    private ButtonGroup grupoCompanias;
    private JButton btnRegistrar, btnLimpiar;
    private JTable tablaVentas;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotalDia;

    public LlamadasView() {
        // LookAndFeel omitido

        setTitle("Sistema de Llamadas Accesible");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA (IZQUIERDA) ---
        JPanel panelEntrada = new JPanel(new GridLayout(6, 1, 10, 10));
        panelEntrada.setOpaque(false);
        panelEntrada.setPreferredSize(new Dimension(200, 0));

        panelEntrada.add(crearLabel("Minutos:"));
        txtMinutos = new JTextField();
        txtMinutos.setFont(new Font("Arial", Font.PLAIN, 18));
        txtMinutos.setHorizontalAlignment(JTextField.CENTER);
        panelEntrada.add(txtMinutos);

        panelEntrada.add(crearLabel("Compañía:"));

        // Radio Buttons
        rbClaro = new JRadioButton("Claro ($10)");
        rbAltice = new JRadioButton("Altice ($7)");
        rbViva = new JRadioButton("Viva ($5)");

        estilizarRadio(rbClaro);
        estilizarRadio(rbAltice);
        estilizarRadio(rbViva);
        rbClaro.setSelected(true); // Default

        grupoCompanias = new ButtonGroup();
        grupoCompanias.add(rbClaro);
        grupoCompanias.add(rbAltice);
        grupoCompanias.add(rbViva);

        JPanel panelRadios = new JPanel(new GridLayout(3, 1));
        panelRadios.setOpaque(false);
        panelRadios.add(rbClaro);
        panelRadios.add(rbAltice);
        panelRadios.add(rbViva);
        panelEntrada.add(panelRadios);

        btnRegistrar = crearBoton("REGISTRAR", new Color(0, 34, 68));
        panelEntrada.add(btnRegistrar);

        mainPanel.add(panelEntrada, BorderLayout.WEST);

        // --- TABLA Y TOTAL (CENTRO) ---
        JPanel panelCentro = new JPanel(new BorderLayout(10, 10));
        panelCentro.setOpaque(false);

        String[] cols = {"COMPAÑÍA", "DURACIÓN", "COSTO"};
        modeloTabla = new DefaultTableModel(null, cols);
        tablaVentas = new JTable(modeloTabla);
        estilizarTabla(tablaVentas);

        panelCentro.add(new JScrollPane(tablaVentas), BorderLayout.CENTER);

        // Panel de Total
        JPanel panelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTotal.setBackground(Color.WHITE);
        panelTotal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        lblTotalDia = new JLabel("Total Ventas: $0.00");
        lblTotalDia.setFont(new Font("Arial", Font.BOLD, 20));
        lblTotalDia.setForeground(new Color(15, 81, 50)); // Verde Oscuro

        panelTotal.add(lblTotalDia);
        panelCentro.add(panelTotal, BorderLayout.SOUTH);

        mainPanel.add(panelCentro, BorderLayout.CENTER);

        this.getRootPane().setDefaultButton(btnRegistrar);
    }

    // --- Helpers de Diseño ---
    private JLabel crearLabel(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        return l;
    }

    private void estilizarRadio(JRadioButton rb) {
        rb.setFont(new Font("Arial", Font.PLAIN, 14));
        rb.setOpaque(false);
        rb.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JButton crearBoton(String texto, Color bg) {
        JButton btn = new JButton(texto);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void estilizarTabla(JTable t) {
        t.setFont(new Font("Arial", Font.PLAIN, 14));
        t.setRowHeight(25);
        t.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        t.getTableHeader().setBackground(Color.decode("#333333"));
        t.getTableHeader().setForeground(Color.WHITE);
    }

    // --- Métodos Públicos ---
    public String getMinutos() { return txtMinutos.getText(); }
    public void limpiarMinutos() { txtMinutos.setText(""); txtMinutos.requestFocus(); }

    public Llamada.Compania getCompaniaSeleccionada() {
        if (rbAltice.isSelected()) return Llamada.Compania.ALTICE;
        if (rbViva.isSelected()) return Llamada.Compania.VIVA;
        return Llamada.Compania.CLARO;
    }

    public void agregarFila(Object[] datos) { modeloTabla.addRow(datos); }

    public void actualizarTotal(String totalTexto) {
        lblTotalDia.setText("Total Ventas: " + totalTexto);
    }

    public void setListener(ActionListener l) {
        btnRegistrar.addActionListener(l);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}