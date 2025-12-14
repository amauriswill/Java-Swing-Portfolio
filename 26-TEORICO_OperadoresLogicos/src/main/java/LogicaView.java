package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemListener;

public class LogicaView extends JFrame {

    private JCheckBox chkA, chkB;
    private JPanel pnlResultados;
    private JLabel lblNotA, lblNotB, lblAnd, lblOr, lblXor;

    public LogicaView() {
        setTitle("Laboratorio Lógico Accesible");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA (Checkboxes) ---
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Entradas"));

        chkA = crearCheckbox("Entrada A (Falso)");
        chkB = crearCheckbox("Entrada B (Falso)");

        inputPanel.add(chkA);
        inputPanel.add(chkB);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // --- RESULTADOS (Grid) ---
        pnlResultados = new JPanel(new GridLayout(2, 3, 15, 15));
        pnlResultados.setOpaque(false);

        lblNotA = agregarTarjeta(pnlResultados, "NOT A (!A)");
        lblNotB = agregarTarjeta(pnlResultados, "NOT B (!B)");
        lblAnd = agregarTarjeta(pnlResultados, "AND (A && B)");
        lblOr = agregarTarjeta(pnlResultados, "OR (A || B)");
        lblXor = agregarTarjeta(pnlResultados, "XOR (A ^ B)"); // Bonus

        mainPanel.add(pnlResultados, BorderLayout.CENTER);
    }

    private JCheckBox crearCheckbox(String texto) {
        JCheckBox chk = new JCheckBox(texto);
        chk.setFont(new Font("Arial", Font.BOLD, 24));
        chk.setOpaque(false);
        chk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        chk.setForeground(Color.RED); // Empieza en falso (Rojo)
        return chk;
    }

    private JLabel agregarTarjeta(JPanel parent, String titulo) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel lblTitle = new JLabel(titulo, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBorder(new EmptyBorder(10, 5, 5, 5));
        card.add(lblTitle, BorderLayout.NORTH);

        JLabel lblRes = new JLabel("---", SwingConstants.CENTER);
        lblRes.setFont(new Font("Arial", Font.BOLD, 28));
        lblRes.setForeground(Color.GRAY);
        card.add(lblRes, BorderLayout.CENTER);

        parent.add(card);
        return lblRes;
    }

    // --- Métodos Públicos ---
    public boolean isASelected() { return chkA.isSelected(); }
    public boolean isBSelected() { return chkB.isSelected(); }

    public void setItemListener(ItemListener listener) {
        chkA.addItemListener(listener);
        chkB.addItemListener(listener);
    }

    public void actualizarInputVisual(JCheckBox chk) {
        if (chk.isSelected()) {
            chk.setText(chk == chkA ? "Entrada A (VERDADERO)" : "Entrada B (VERDADERO)");
            chk.setForeground(new Color(15, 81, 50)); // Verde
        } else {
            chk.setText(chk == chkA ? "Entrada A (FALSO)" : "Entrada B (FALSO)");
            chk.setForeground(new Color(183, 28, 28)); // Rojo
        }
    }

    public void actualizarTarjeta(JLabel lbl, boolean valor) {
        if (valor) {
            lbl.setText("VERDADERO");
            lbl.setForeground(new Color(15, 81, 50));
            lbl.getParent().setBackground(new Color(209, 231, 221));
        } else {
            lbl.setText("FALSO");
            lbl.setForeground(new Color(183, 28, 28));
            lbl.getParent().setBackground(new Color(255, 235, 238));
        }
    }

    // Getters
    public JCheckBox getChkA() { return chkA; }
    public JCheckBox getChkB() { return chkB; }
    public JLabel getLblNotA() { return lblNotA; }
    public JLabel getLblNotB() { return lblNotB; }
    public JLabel getLblAnd() { return lblAnd; }
    public JLabel getLblOr() { return lblOr; }
    public JLabel getLblXor() { return lblXor; }
}