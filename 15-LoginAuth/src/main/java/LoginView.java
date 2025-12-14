package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;
    private JProgressBar progressBar;

    public LoginView() {
        setTitle("Acceso al Sistema Accesible");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(Color.decode("#003366"));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        // --- FORMULARIO ---
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        formPanel.setOpaque(false);

        agregarCampo(formPanel, "Usuario:", txtUsuario = new JTextField());
        agregarCampo(formPanel, "Contraseña:", txtPassword = new JPasswordField());

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // --- PANEL INFERIOR (Botón y Barra) ---
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 15));
        bottomPanel.setOpaque(false);

        // Botón Accesible
        btnIngresar = new JButton("INGRESAR");
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 16));
        btnIngresar.setBackground(Color.decode("#003366"));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setOpaque(true);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setFocusPainted(true);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.setPreferredSize(new Dimension(0, 45)); // Altura del botón

        bottomPanel.add(btnIngresar, BorderLayout.NORTH);

        // Barra de Progreso (Inicialmente invisible)
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true); // Muestra el % en texto
        progressBar.setFont(new Font("Arial", Font.BOLD, 12));
        progressBar.setForeground(Color.decode("#003366")); // Color del texto/barra
        progressBar.setVisible(false); // Oculta al inicio
        progressBar.setPreferredSize(new Dimension(0, 25));

        bottomPanel.add(progressBar, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Tecla Enter activa el botón
        this.getRootPane().setDefaultButton(btnIngresar);
    }

    private void agregarCampo(JPanel panel, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panel.add(label);
        panel.add(field);
    }

    // --- Getters y Setters para el Controlador ---
    public String getUsuario() { return txtUsuario.getText(); }
    public char[] getPassword() { return txtPassword.getPassword(); }

    public void setBotonListener(ActionListener listener) {
        btnIngresar.addActionListener(listener);
    }

    public void setControlesHabilitados(boolean habilitado) {
        txtUsuario.setEnabled(habilitado);
        txtPassword.setEnabled(habilitado);
        btnIngresar.setEnabled(habilitado);
    }

    public void mostrarBarraProgreso(boolean mostrar) {
        progressBar.setVisible(mostrar);
        if (mostrar) progressBar.setValue(0);
    }

    public void actualizarProgreso(int valor) {
        progressBar.setValue(valor);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Acceso", JOptionPane.ERROR_MESSAGE);
    }
}