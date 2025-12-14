package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.Year;

public class VotacionView extends JFrame {

    private JTextField txtAnioActual, txtAnioNacimiento;
    private JButton btnVerificar;
    private JLabel lblEdad, lblMensaje;
    private JPanel pnlResultado;

    public VotacionView() {
        // Look & Feel omitido

        setTitle("Sistema Electoral Accesible");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA ---
        JPanel inputPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        inputPanel.setOpaque(false);

        // Año Actual (Pre-llenado automáticamente)
        agregarCampo(inputPanel, "Año Actual:", txtAnioActual = new JTextField());
        // Truco Pro: Ponemos el año del sistema automáticamente
        txtAnioActual.setText(String.valueOf(Year.now().getValue()));

        agregarCampo(inputPanel, "Año de Nacimiento:", txtAnioNacimiento = new JTextField());

        // Espacio
        inputPanel.add(Box.createVerticalStrut(10));

        // BOTÓN ACCESIBLE
        btnVerificar = new JButton("VERIFICAR ELEGIBILIDAD");
        btnVerificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVerificar.setBackground(new Color(0, 34, 68)); // Azul Oscuro
        btnVerificar.setForeground(Color.WHITE);
        btnVerificar.setOpaque(true);
        btnVerificar.setBorderPainted(false);
        btnVerificar.setFocusPainted(true);
        btnVerificar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inputPanel.add(btnVerificar);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // --- RESULTADO ---
        pnlResultado = new JPanel(new GridLayout(2, 1));
        pnlResultado.setBackground(Color.WHITE);
        pnlResultado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        lblEdad = new JLabel("- años");
        lblEdad.setFont(new Font("Arial", Font.BOLD, 24));
        lblEdad.setHorizontalAlignment(SwingConstants.CENTER);

        lblMensaje = new JLabel("Esperando datos...");
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 18));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setForeground(Color.GRAY);

        pnlResultado.add(lblEdad);
        pnlResultado.add(lblMensaje);
        mainPanel.add(pnlResultado, BorderLayout.CENTER);
    }

    private void agregarCampo(JPanel panel, String texto, JTextField campo) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
        panel.add(label);

        campo.setFont(new Font("Arial", Font.PLAIN, 18));
        campo.setHorizontalAlignment(JTextField.CENTER);
        campo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(campo);
    }

    public String getAnioActual() { return txtAnioActual.getText(); }
    public String getAnioNacimiento() { return txtAnioNacimiento.getText(); }

    public void setBotonListener(ActionListener listener) {
        btnVerificar.addActionListener(listener);
    }

    public void mostrarResultado(int edad, boolean puedeVotar) {
        lblEdad.setText(edad + " AÑOS");

        if (puedeVotar) {
            // VERDE ACCESIBLE
            pnlResultado.setBackground(new Color(209, 231, 221)); // Fondo Verde Claro
            lblMensaje.setText("¡HABILITADO PARA VOTAR!");
            lblMensaje.setForeground(new Color(15, 81, 50)); // Verde Oscuro
        } else {
            // ROJO/NARANJA ACCESIBLE
            pnlResultado.setBackground(new Color(255, 243, 205)); // Fondo Amarillo/Naranja Claro
            lblMensaje.setText("NO TIENE EDAD PARA VOTAR");
            lblMensaje.setForeground(new Color(102, 77, 3)); // Marrón Oscuro
        }
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Validación", JOptionPane.ERROR_MESSAGE);
    }
}