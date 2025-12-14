package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CifrasView extends JFrame {

    private JTextField txtNumero;
    private JButton btnCalcular;
    private JLabel lblResultado;
    private JPanel pnlResultado;

    public CifrasView() {
        // --- IMPORTANTE: DESACTIVAMOS EL LOOK AND FEEL DE WINDOWS ---
        // Esto garantiza que nuestros colores de alto contraste se respeten
        // try { UIManager.setLookAndFeel(...); } catch... (Lo omitimos)

        setTitle("Contador de Cifras Accesible");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Principal
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8")); // Gris azulado muy claro (descanso visual)
        setContentPane(mainPanel);

        // --- ZONA DE ENTRADA (ARRIBA) ---
        JPanel topPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        topPanel.setOpaque(false);

        JLabel lblInstruccion = new JLabel("Ingrese un número entero:");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 18)); // Fuente grande y legible
        lblInstruccion.setForeground(Color.BLACK);
        lblInstruccion.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(lblInstruccion);

        txtNumero = new JTextField();
        txtNumero.setFont(new Font("Arial", Font.PLAIN, 28)); // Input gigante
        txtNumero.setHorizontalAlignment(JTextField.CENTER);
        // Borde grueso negro para que sea fácil de ubicar
        txtNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        topPanel.add(txtNumero);

        // BOTÓN DE ALTO CONTRASTE (Azul Profundo)
        btnCalcular = new JButton("CALCULAR CIFRAS");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 16));
        btnCalcular.setBackground(new Color(0, 34, 68)); // Azul Marino Oscuro (#002244)
        btnCalcular.setForeground(Color.WHITE);

        // Propiedades para forzar la legibilidad
        btnCalcular.setOpaque(true);
        btnCalcular.setBorderPainted(false);
        btnCalcular.setFocusPainted(true); // Dejamos el foco para accesibilidad por teclado
        btnCalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));

        topPanel.add(btnCalcular);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- ZONA DE RESULTADO (CENTRO) ---
        pnlResultado = new JPanel(new GridBagLayout());
        pnlResultado.setBackground(Color.WHITE);
        pnlResultado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde sólido

        lblResultado = new JLabel("¿Cuántas cifras tiene?");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 22));
        lblResultado.setForeground(Color.GRAY);

        pnlResultado.add(lblResultado);
        mainPanel.add(pnlResultado, BorderLayout.CENTER);
    }

    // --- MÉTODOS PÚBLICOS ---
    public String getNumeroInput() { return txtNumero.getText(); }

    public void setBotonListener(ActionListener listener) {
        btnCalcular.addActionListener(listener);
    }

    // Método flexible para mostrar resultados con colores accesibles
    public void mostrarResultado(String texto, Color fondo, Color textoColor) {
        lblResultado.setText(texto);
        lblResultado.setForeground(textoColor);
        pnlResultado.setBackground(fondo);
        pnlResultado.requestFocusInWindow(); // Ayuda a lectores de pantalla
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Dato", JOptionPane.ERROR_MESSAGE);
    }
}