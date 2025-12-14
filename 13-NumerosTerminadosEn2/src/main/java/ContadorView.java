package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ContadorView extends JFrame {

    private JTextField txtNumero;
    private JButton btnAgregar;
    private JLabel lblContador;
    private JTextArea txtHistorial; // Para ver qué hemos metido
    private JPanel pnlResultado;

    public ContadorView() {
        // Desactivamos LookAndFeel para colores reales
        // try { UIManager.setLookAndFeel(...); } catch... (Omitido)

        setTitle("Contador de Terminaciones Accesible");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA ---
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setOpaque(false);

        JLabel lblInstruccion = new JLabel("<html>Ingrese un número entero:<br/><small>(Negativo para finalizar)</small></html>");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 16));
        lblInstruccion.setForeground(Color.BLACK);
        topPanel.add(lblInstruccion, BorderLayout.NORTH);

        JPanel inputGroup = new JPanel(new BorderLayout(10, 0));
        inputGroup.setOpaque(false);

        txtNumero = new JTextField();
        txtNumero.setFont(new Font("Arial", Font.PLAIN, 24));
        txtNumero.setHorizontalAlignment(JTextField.CENTER);
        txtNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        inputGroup.add(txtNumero, BorderLayout.CENTER);

        // BOTÓN ACCESIBLE
        btnAgregar = new JButton("AGREGAR");
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.setBackground(new Color(0, 34, 68)); // Azul Oscuro
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setOpaque(true);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setFocusPainted(true);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Tamaño preferido para que no sea muy ancho
        btnAgregar.setPreferredSize(new Dimension(150, 50));

        inputGroup.add(btnAgregar, BorderLayout.EAST);
        topPanel.add(inputGroup, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- CENTRO (HISTORIAL) ---
        txtHistorial = new JTextArea();
        txtHistorial.setEditable(false);
        txtHistorial.setFont(new Font("Monospaced", Font.PLAIN, 16));
        txtHistorial.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(txtHistorial);
        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Historial de entradas"
        ));
        mainPanel.add(scroll, BorderLayout.CENTER);

        // --- RESULTADO (ABAJO) ---
        pnlResultado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlResultado.setBackground(Color.WHITE);
        pnlResultado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pnlResultado.setPreferredSize(new Dimension(0, 80));

        lblContador = new JLabel("Terminados en 2: 0");
        lblContador.setFont(new Font("Arial", Font.BOLD, 24));
        lblContador.setForeground(new Color(0, 34, 68)); // Azul oscuro

        pnlResultado.add(lblContador);
        mainPanel.add(pnlResultado, BorderLayout.SOUTH);

        // Soporte para tecla ENTER
        this.getRootPane().setDefaultButton(btnAgregar);
    }

    public String getNumeroInput() { return txtNumero.getText(); }

    public void limpiarInput() {
        txtNumero.setText("");
        txtNumero.requestFocus();
    }

    public void agregarAlHistorial(String texto) {
        txtHistorial.append(texto + "\n");
    }

    public void limpiarHistorial() {
        txtHistorial.setText("");
    }

    public void actualizarContador(int cantidad) {
        lblContador.setText("Terminados en 2: " + cantidad);
    }

    public void setBotonListener(ActionListener listener) {
        btnAgregar.addActionListener(listener);
    }

    public void mostrarMensajeFinal(int totalEncontrados) {
        // Mensaje de Alto Contraste usando HTML básico
        String msg = "<html><h2 style='color:#002244'>RESUMEN FINAL</h2>" +
                "<p style='font-size:14px'>Se ha detectado un número negativo.</p><br/>" +
                "<p style='font-size:18px'>Total de números terminados en 2: <b>" + totalEncontrados + "</b></p></html>";

        JOptionPane.showMessageDialog(this, msg, "Sesión Finalizada", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
    }
}