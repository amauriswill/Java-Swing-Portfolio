package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AccesoView extends JFrame {

    private JTextField txtEdad;
    private JCheckBox chkEntrada, chkVip;
    private JButton btnEvaluar;
    private JLabel lblEstado, lblDetalle;
    private JPanel pnlSemaforo;

    public AccesoView() {
        setTitle("Control de Acceso Inteligente");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.decode("#f0f4f8"));
        setContentPane(mainPanel);

        // --- ENTRADA DE DATOS ---
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Datos del Visitante"));

        // Edad
        JPanel pnlEdad = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlEdad.setOpaque(false);
        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setFont(new Font("Arial", Font.BOLD, 16));
        txtEdad = new JTextField(5);
        txtEdad.setFont(new Font("Arial", Font.PLAIN, 18));
        txtEdad.setHorizontalAlignment(JTextField.CENTER);
        pnlEdad.add(lblEdad);
        pnlEdad.add(txtEdad);
        formPanel.add(pnlEdad);

        // Checkboxes
        chkEntrada = crearCheckbox("Tiene Entrada / Ticket");
        chkVip = crearCheckbox("Es Miembro VIP");

        formPanel.add(chkEntrada);
        formPanel.add(chkVip);

        // Botón
        btnEvaluar = new JButton("VERIFICAR ACCESO");
        btnEvaluar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEvaluar.setBackground(new Color(0, 34, 68));
        btnEvaluar.setForeground(Color.WHITE);
        btnEvaluar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(btnEvaluar);

        mainPanel.add(formPanel, BorderLayout.NORTH);

        // --- SEMÁFORO (RESULTADO) ---
        pnlSemaforo = new JPanel(new BorderLayout(10, 10));
        pnlSemaforo.setBackground(Color.LIGHT_GRAY);
        pnlSemaforo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        lblEstado = new JLabel("ESPERANDO...", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 28));
        lblEstado.setForeground(Color.DARK_GRAY);
        lblEstado.setOpaque(false);

        lblDetalle = new JLabel("Lógica: ---", SwingConstants.CENTER);
        lblDetalle.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lblDetalle.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnlSemaforo.add(lblEstado, BorderLayout.CENTER);
        pnlSemaforo.add(lblDetalle, BorderLayout.SOUTH);

        mainPanel.add(pnlSemaforo, BorderLayout.CENTER);

        this.getRootPane().setDefaultButton(btnEvaluar);
    }

    private JCheckBox crearCheckbox(String texto) {
        JCheckBox chk = new JCheckBox(texto);
        chk.setFont(new Font("Arial", Font.BOLD, 16));
        chk.setOpaque(false);
        chk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return chk;
    }

    // --- Métodos Públicos ---
    public String getEdad() { return txtEdad.getText(); }
    public boolean tieneEntrada() { return chkEntrada.isSelected(); }
    public boolean esVip() { return chkVip.isSelected(); }

    public void setBotonListener(ActionListener l) {
        btnEvaluar.addActionListener(l);
    }

    public void mostrarResultado(boolean permitido, String mensaje, String logica) {
        lblEstado.setText(mensaje);
        lblDetalle.setText("<html><center>" + logica + "</center></html>");

        if (permitido) {
            pnlSemaforo.setBackground(new Color(40, 167, 69)); // Verde
            lblEstado.setForeground(Color.WHITE);
        } else {
            pnlSemaforo.setBackground(new Color(220, 53, 69)); // Rojo
            lblEstado.setForeground(Color.WHITE);
        }
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error de Datos", JOptionPane.ERROR_MESSAGE);
    }
}