package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ComparadorView extends JFrame {

    private JTextField txtA, txtB;
    private JButton btnComparar;
    private JPanel pnlResultados;

    // Etiquetas para los resultados (Array para fácil acceso o individuales)
    private JLabel lblMayor, lblMenor, lblMayorIgual, lblMenorIgual, lblIgual, lblDistinto;

    public ComparadorView() {
        setTitle("Laboratorio de Operadores");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA (TOP) ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.setOpaque(false);

        topPanel.add(crearLabelInput("Valor A:"));
        txtA = crearInput();
        topPanel.add(txtA);

        topPanel.add(crearLabelInput("Valor B:"));
        txtB = crearInput();
        topPanel.add(txtB);

        btnComparar = new JButton("EVALUAR");
        btnComparar.setFont(new Font("Arial", Font.BOLD, 16));
        btnComparar.setBackground(new Color(0, 34, 68));
        btnComparar.setForeground(Color.WHITE);
        btnComparar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        topPanel.add(btnComparar);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- RESULTADOS (GRID CENTRAL) ---
        pnlResultados = new JPanel(new GridLayout(2, 3, 15, 15)); // 2 filas, 3 columnas
        pnlResultados.setOpaque(false);

        // Creamos las 6 tarjetas
        lblMayor = agregarTarjeta(pnlResultados, "A > B (Mayor que)");
        lblMenor = agregarTarjeta(pnlResultados, "A < B (Menor que)");
        lblMayorIgual = agregarTarjeta(pnlResultados, "A >= B (Mayor o Igual)");
        lblMenorIgual = agregarTarjeta(pnlResultados, "A <= B (Menor o Igual)");
        lblIgual = agregarTarjeta(pnlResultados, "A == B (Igual)");
        lblDistinto = agregarTarjeta(pnlResultados, "A != B (Distinto)");

        mainPanel.add(pnlResultados, BorderLayout.CENTER);

        this.getRootPane().setDefaultButton(btnComparar);
    }

    // --- Helpers de Diseño ---

    private JLabel agregarTarjeta(JPanel parent, String titulo) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel lblTitle = new JLabel(titulo, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setBorder(new EmptyBorder(10, 5, 5, 5));
        card.add(lblTitle, BorderLayout.NORTH);

        JLabel lblRes = new JLabel("---", SwingConstants.CENTER);
        lblRes.setFont(new Font("Arial", Font.BOLD, 24));
        lblRes.setForeground(Color.GRAY);
        card.add(lblRes, BorderLayout.CENTER);

        parent.add(card);
        return lblRes;
    }

    private JLabel crearLabelInput(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Arial", Font.BOLD, 16));
        return l;
    }

    private JTextField crearInput() {
        JTextField t = new JTextField(5);
        t.setFont(new Font("Arial", Font.PLAIN, 20));
        t.setHorizontalAlignment(JTextField.CENTER);
        t.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return t;
    }

    // --- Métodos Públicos ---
    public String getA() { return txtA.getText(); }
    public String getB() { return txtB.getText(); }

    public void setListener(ActionListener l) {
        btnComparar.addActionListener(l);
    }

    // Método genérico para actualizar una tarjeta
    public void actualizarTarjeta(JLabel lbl, boolean valor) {
        if (valor) {
            lbl.setText("VERDADERO");
            lbl.setForeground(new Color(15, 81, 50)); // Verde Oscuro
            lbl.getParent().setBackground(new Color(209, 231, 221)); // Fondo Verde Claro
        } else {
            lbl.setText("FALSO");
            lbl.setForeground(new Color(120, 20, 20)); // Rojo Oscuro
            lbl.getParent().setBackground(new Color(255, 220, 220)); // Fondo Rojo Claro
        }
    }

    // Getters de los labels para el controlador
    public JLabel getLblMayor() { return lblMayor; }
    public JLabel getLblMenor() { return lblMenor; }
    public JLabel getLblMayorIgual() { return lblMayorIgual; }
    public JLabel getLblMenorIgual() { return lblMenorIgual; }
    public JLabel getLblIgual() { return lblIgual; }
    public JLabel getLblDistinto() { return lblDistinto; }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}