package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;
    private JLabel lblMensaje;

    public LoginView() {
        setTitle("Acceso Seguro");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        JLabel lblTitulo = new JLabel("LOGIN SISTEMA MDI");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblTitulo);

        txtUsuario = new JTextField();
        txtUsuario.setBorder(BorderFactory.createTitledBorder("Usuario"));
        mainPanel.add(txtUsuario);

        txtPassword = new JPasswordField();
        txtPassword.setBorder(BorderFactory.createTitledBorder("Contraseña"));
        mainPanel.add(txtPassword);

        btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBackground(new Color(0, 34, 68));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btnIngresar);

        lblMensaje = new JLabel("");
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblMensaje);

        this.getRootPane().setDefaultButton(btnIngresar);
    }

    public String getUsuario() { return txtUsuario.getText(); }
    public char[] getPassword() { return txtPassword.getPassword(); }

    public void setLoginListener(ActionListener listener) {
        btnIngresar.addActionListener(listener);
    }

    public void mostrarError(String msg) {
        lblMensaje.setText(msg);
    }

    public void bloquearSistema() {
        txtUsuario.setEnabled(false);
        txtPassword.setEnabled(false);
        btnIngresar.setEnabled(false);
        btnIngresar.setBackground(Color.GRAY);
        lblMensaje.setText("SISTEMA BLOQUEADO (3 Intentos)");
    }
}