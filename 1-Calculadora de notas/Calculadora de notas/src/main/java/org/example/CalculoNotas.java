package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculoNotas extends JFrame {

    // Componentes (Privados, nadie los toca directamente)
    private JTextField txtAlumno, txtAsistencia, txtTrabajoPractico, txtExamenParcial, txtTrabajoFinal, txtExamenFinal;
    private JTextField txtNotaNumerica, txtNotaLiteral;
    private JButton btnCalcular;

    public CalculoNotas() {
        // --- SOLO ESTÉTICA Y DISEÑO ---
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        setTitle("Sistema MVC");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.decode("#f0f2f5"));
        setContentPane(mainPanel);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        formPanel.setOpaque(false);
        Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);

        formPanel.add(crearLabel("Nombre Alumno:", mainFont));
        txtAlumno = crearInput(mainFont);
        formPanel.add(txtAlumno);

        formPanel.add(crearLabel("Asistencia (10):", mainFont));
        txtAsistencia = crearInput(mainFont);
        formPanel.add(txtAsistencia);

        formPanel.add(crearLabel("Trabajo Práctico (20):", mainFont));
        txtTrabajoPractico = crearInput(mainFont);
        formPanel.add(txtTrabajoPractico);

        formPanel.add(crearLabel("Examen Parcial (20):", mainFont));
        txtExamenParcial = crearInput(mainFont);
        formPanel.add(txtExamenParcial);

        formPanel.add(crearLabel("Trabajo Final (20):", mainFont));
        txtTrabajoFinal = crearInput(mainFont);
        formPanel.add(txtTrabajoFinal);

        formPanel.add(crearLabel("Examen Final (30):", mainFont));
        txtExamenFinal = crearInput(mainFont);
        formPanel.add(txtExamenFinal);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Panel Inferior
        JPanel bottomPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        bottomPanel.setOpaque(false);
        bottomPanel.add(new JSeparator());

        btnCalcular = new JButton(" CALCULAR NOTA FINAL ");
        btnCalcular.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCalcular.setBackground(new Color(0, 102, 204));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // NOTA: AQUI YA NO HAY LOGICA, SOLO DISEÑO
        bottomPanel.add(btnCalcular);

        JPanel resultsPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        resultsPanel.setOpaque(false);

        resultsPanel.add(crearLabel("Nota Numérica:", mainFont));
        txtNotaNumerica = crearInput(mainFont);
        txtNotaNumerica.setEditable(false);
        txtNotaNumerica.setHorizontalAlignment(JTextField.CENTER);
        resultsPanel.add(txtNotaNumerica);

        resultsPanel.add(crearLabel("Calificación:", mainFont));
        txtNotaLiteral = crearInput(mainFont);
        txtNotaLiteral.setEditable(false);
        txtNotaLiteral.setHorizontalAlignment(JTextField.CENTER);
        txtNotaLiteral.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultsPanel.add(txtNotaLiteral);

        bottomPanel.add(resultsPanel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    // --- MÉTODOS PÚBLICOS PARA EL CONTROLADOR ---
    // El controlador usará esto para "escuchar" el botón
    public void setBotonListener(ActionListener listener) {
        btnCalcular.addActionListener(listener);
    }

    // Getters para sacar datos
    public String getAsistencia() { return txtAsistencia.getText(); }
    public String getTP() { return txtTrabajoPractico.getText(); }
    public String getEP() { return txtExamenParcial.getText(); }
    public String getTF() { return txtTrabajoFinal.getText(); }
    public String getEF() { return txtExamenFinal.getText(); }

    // Setters para mostrar resultados
    public void setNotaNumerica(String valor) { txtNotaNumerica.setText(valor); }
    public void setNotaLiteral(String valor, Color color) {
        txtNotaLiteral.setText(valor);
        txtNotaLiteral.setForeground(color);
    }

    // Métodos visuales helpers
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private JLabel crearLabel(String texto, Font fuente) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        return label;
    }
    private JTextField crearInput(Font fuente) {
        JTextField input = new JTextField();
        input.setFont(fuente);
        input.setMargin(new Insets(5, 10, 5, 10));
        return input;
    }
}