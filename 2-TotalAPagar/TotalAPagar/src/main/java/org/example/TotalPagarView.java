package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class TotalPagarView extends JFrame {

    // Componentes visuales
    private JTextField txtProducto, txtCantidad, txtPrecio;
    private JTextField txtTotalPagar;
    private JButton btnCalcular;

    public TotalPagarView() {
        // --- CONFIGURACIÓN VISUAL (LOOK & FEEL) ---
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        setTitle("Caja Registradora MVC");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        mainPanel.setBackground(Color.decode("#f8f9fa")); // Fondo claro moderno
        setContentPane(mainPanel);

        // --- ZONA DE FORMULARIO ---
        JPanel formPanel = new JPanel(new GridLayout(0, 1, 8, 8));
        formPanel.setOpaque(false);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Campo Descripción
        formPanel.add(crearLabel("DESCRIPCIÓN DEL PRODUCTO:", labelFont));
        txtProducto = crearInput(inputFont);
        formPanel.add(txtProducto);

        // Sub-panel para poner Cantidad y Precio en la misma línea
        JPanel rowPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        rowPanel.setOpaque(false);

        // Columna Cantidad
        JPanel pnlCant = new JPanel(new BorderLayout());
        pnlCant.setOpaque(false);
        pnlCant.add(crearLabel("CANTIDAD:", labelFont), BorderLayout.NORTH);
        txtCantidad = crearInput(inputFont);
        pnlCant.add(txtCantidad, BorderLayout.CENTER);

        // Columna Precio
        JPanel pnlPrecio = new JPanel(new BorderLayout());
        pnlPrecio.setOpaque(false);
        pnlPrecio.add(crearLabel("PRECIO UNITARIO ($):", labelFont), BorderLayout.NORTH);
        txtPrecio = crearInput(inputFont);
        pnlPrecio.add(txtPrecio, BorderLayout.CENTER);

        rowPanel.add(pnlCant);
        rowPanel.add(pnlPrecio);
        formPanel.add(rowPanel);

        // Espaciador visual
        formPanel.add(Box.createVerticalStrut(15));

        // Botón Estilizado
        btnCalcular = new JButton("CALCULAR TOTAL");
        btnCalcular.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnCalcular.setBackground(new Color(13, 110, 253)); // Azul Bootstrap
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(btnCalcular);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // --- ZONA DE RESULTADO (Inferior) ---
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JLabel lblTotal = new JLabel("TOTAL A PAGAR:");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(lblTotal, BorderLayout.NORTH);

        txtTotalPagar = new JTextField("$ 0.00");
        txtTotalPagar.setFont(new Font("Segoe UI", Font.BOLD, 26));
        txtTotalPagar.setHorizontalAlignment(JTextField.CENTER);
        txtTotalPagar.setEditable(false);
        txtTotalPagar.setBackground(Color.WHITE);
        txtTotalPagar.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        bottomPanel.add(txtTotalPagar, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    // --- MÉTODOS PARA QUE EL CONTROLADOR INTERACTÚE ---

    // Getters: Para que el controlador pueda leer lo que escribió el usuario
    public String getCantidad() { return txtCantidad.getText(); }
    public String getPrecio() { return txtPrecio.getText(); }

    // Setters: Para que el controlador pueda cambiar cosas en la pantalla
    public void setTotal(String textoTotal, Color color) {
        txtTotalPagar.setText(textoTotal);
        txtTotalPagar.setForeground(color);
    }

    public void setBotonListener(ActionListener listener) {
        btnCalcular.addActionListener(listener);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error de Datos", JOptionPane.ERROR_MESSAGE);
    }

    // --- Helpers de Diseño (Para no repetir código) ---
    private JLabel crearLabel(String texto, Font fuente) {
        JLabel l = new JLabel(texto);
        l.setFont(fuente);
        l.setForeground(Color.DARK_GRAY);
        return l;
    }
    private JTextField crearInput(Font fuente) {
        JTextField t = new JTextField();
        t.setFont(fuente);
        t.setMargin(new Insets(6, 8, 6, 8)); // Padding interno
        return t;
    }
}