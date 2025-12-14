package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ParImparView extends JFrame {

    private JTextField txtNumero;
    private JLabel lblResultado;
    private JPanel pnlResultado;
    private JButton btnVerificar;

    public ParImparView() {
        // -------------------------------------------------------------------------
        // PASO CRÍTICO 1: COMENTAMOS ESTA LÍNEA
        // Al quitar esto, usamos el estilo "Metal" (Java por defecto) que SÍ RESPETA LOS COLORES
        // try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        // -------------------------------------------------------------------------

        setTitle("Verificador Accesible");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Principal
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f5f7fa"));
        setContentPane(mainPanel);

        // --- ZONA SUPERIOR ---
        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        inputPanel.setOpaque(false);

        JLabel lblInstruccion = new JLabel("Ingrese un número entero:");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 16)); // Cambio a Arial para máxima legibilidad
        lblInstruccion.setForeground(Color.BLACK);
        lblInstruccion.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(lblInstruccion);

        txtNumero = new JTextField();
        txtNumero.setFont(new Font("Arial", Font.PLAIN, 24));
        txtNumero.setHorizontalAlignment(JTextField.CENTER);
        txtNumero.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        inputPanel.add(txtNumero);

        // -------------------------------------------------------------------------
        // PASO CRÍTICO 2: EL BOTÓN DE ALTO CONTRASTE
        // -------------------------------------------------------------------------
        btnVerificar = new JButton("VERIFICAR NÚMERO");
        btnVerificar.setFont(new Font("Arial", Font.BOLD, 16));

        // COLOR: Azul Marino muy oscuro (#002244)
        btnVerificar.setBackground(new Color(0, 34, 68));
        btnVerificar.setForeground(Color.WHITE);

        // TRUCO PARA FORZAR EL COLOR:
        btnVerificar.setOpaque(true);           // Obliga a pintar el fondo
        btnVerificar.setBorderPainted(false);   // Quita el borde 3D que a veces estorba
        btnVerificar.setFocusPainted(false);    // Quita el recuadro de foco
        btnVerificar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inputPanel.add(btnVerificar);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // --- ZONA CENTRAL ---
        pnlResultado = new JPanel(new GridBagLayout());
        pnlResultado.setBackground(Color.WHITE);
        pnlResultado.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        lblResultado = new JLabel("Esperando dato...");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 20));
        lblResultado.setForeground(Color.GRAY);

        pnlResultado.add(lblResultado);
        mainPanel.add(pnlResultado, BorderLayout.CENTER);
    }

    // --- GETTERS Y SETTERS ---
    public String getNumeroTexto() { return txtNumero.getText(); }

    public void setBotonListener(ActionListener listener) {
        btnVerificar.addActionListener(listener);
    }

    public void mostrarResultado(String texto, Color fondo, Color textoColor) {
        lblResultado.setText(texto);
        lblResultado.setForeground(textoColor);
        pnlResultado.setBackground(fondo);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
    }
}