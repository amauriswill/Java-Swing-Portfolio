package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClasificadorView extends JFrame {

    private JTextField txtNumeroUnico;
    private JTextField txtNumA, txtNumB;
    private JButton btnClasificar, btnComparar;
    private JLabel lblResultadoSigno, lblResultadoMayor;
    private JPanel pnlSigno, pnlMayor;

    public ClasificadorView() {
        setTitle("Laboratorio de Decisiones Accesible");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 20, 20)); // Dos grandes secciones
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- SECCIÓN 1: IF-ELSE (Signo) ---
        JPanel panelSigno = crearPanelBase("1. Clasificador de Signo (IF-ELSE)");

        JPanel inputSigno = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputSigno.setOpaque(false);
        inputSigno.add(crearLabel("Número:"));
        txtNumeroUnico = crearInput();
        inputSigno.add(txtNumeroUnico);
        btnClasificar = crearBoton("CLASIFICAR", new Color(0, 34, 68));
        inputSigno.add(btnClasificar);

        panelSigno.add(inputSigno, BorderLayout.NORTH);

        pnlSigno = new JPanel(new GridBagLayout());
        pnlSigno.setBackground(Color.WHITE);
        pnlSigno.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        lblResultadoSigno = crearLabelResultado("Esperando...");
        pnlSigno.add(lblResultadoSigno);
        panelSigno.add(pnlSigno, BorderLayout.CENTER);

        mainPanel.add(panelSigno);

        // --- SECCIÓN 2: TERNARIO (Mayor) ---
        JPanel panelMayor = crearPanelBase("2. Comparador Ternario (? :)");

        JPanel inputMayor = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputMayor.setOpaque(false);
        inputMayor.add(crearLabel("A:"));
        txtNumA = crearInput();
        inputMayor.add(txtNumA);
        inputMayor.add(crearLabel("B:"));
        txtNumB = crearInput();
        inputMayor.add(txtNumB);
        btnComparar = crearBoton("COMPARAR", new Color(0, 34, 68));
        inputMayor.add(btnComparar);

        panelMayor.add(inputMayor, BorderLayout.NORTH);

        pnlMayor = new JPanel(new GridBagLayout());
        pnlMayor.setBackground(Color.WHITE);
        pnlMayor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        lblResultadoMayor = crearLabelResultado("Esperando...");
        pnlMayor.add(lblResultadoMayor);
        panelMayor.add(pnlMayor, BorderLayout.CENTER);

        mainPanel.add(panelMayor);
    }

    // --- Helpers ---
    private JPanel crearPanelBase(String titulo) {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), titulo));
        return p;
    }

    private JTextField crearInput() {
        JTextField t = new JTextField(6);
        t.setFont(new Font("Arial", Font.PLAIN, 18));
        t.setHorizontalAlignment(JTextField.CENTER);
        t.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return t;
    }

    private JLabel crearLabel(String t) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        return l;
    }

    private JLabel crearLabelResultado(String t) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Arial", Font.BOLD, 24));
        l.setForeground(Color.GRAY);
        return l;
    }

    private JButton crearBoton(String t, Color c) {
        JButton b = new JButton(t);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    // --- Métodos Públicos ---
    public String getNumUnico() { return txtNumeroUnico.getText(); }
    public String getNumA() { return txtNumA.getText(); }
    public String getNumB() { return txtNumB.getText(); }

    public void setListeners(ActionListener l) {
        btnClasificar.addActionListener(l);
        btnComparar.addActionListener(l);
    }

    public JButton getBtnClasificar() { return btnClasificar; }
    public JButton getBtnComparar() { return btnComparar; }

    public void mostrarResultadoSigno(String texto, Color fondo, Color textoColor) {
        lblResultadoSigno.setText(texto);
        lblResultadoSigno.setForeground(textoColor);
        pnlSigno.setBackground(fondo);
    }

    public void mostrarResultadoMayor(String texto) {
        lblResultadoMayor.setText(texto);
        lblResultadoMayor.setForeground(new Color(0, 34, 68));
        pnlMayor.setBackground(new Color(225, 245, 254)); // Azul claro
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}