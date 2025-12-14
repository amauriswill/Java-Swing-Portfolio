package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DashboardView extends JFrame {

    private JDesktopPane escritorio; // Aquí vivirán las ventanas internas
    private JMenuItem menuClientes, menuEmpleados, menuSalir;

    public DashboardView(String usuario) {
        setTitle("Sistema de Gestión Empresarial - Usuario: " + usuario.toUpperCase());
        // Maximizar ventana por defecto
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. Crear el Escritorio Virtual (MDI Container)
        escritorio = new JDesktopPane();
        escritorio.setBackground(Color.decode("#cfd8dc")); // Color de fondo del escritorio
        setContentPane(escritorio);

        // 2. Crear Barra de Menú
        JMenuBar barraMenu = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        menuSalir = new JMenuItem("Cerrar Sesión");
        menuArchivo.add(menuSalir);

        JMenu menuGestion = new JMenu("Gestión");
        menuClientes = new JMenuItem("Gestión de Clientes");
        menuEmpleados = new JMenuItem("Gestión de Empleados");
        menuGestion.add(menuClientes);
        menuGestion.add(menuEmpleados);

        barraMenu.add(menuArchivo);
        barraMenu.add(menuGestion);
        setJMenuBar(barraMenu);
    }

    // Método para agregar ventanas hijas al escritorio
    public void abrirVentanaInterna(String titulo) {
        // Verificar si ya está abierta para no duplicar (Opcional profesional)
        for (JInternalFrame frame : escritorio.getAllFrames()) {
            if (frame.getTitle().equals(titulo)) {
                try { frame.setSelected(true); } catch (Exception e) {}
                return;
            }
        }

        // Crear nueva ventana interna
        JInternalFrame frame = new JInternalFrame(titulo, true, true, true, true);
        frame.setSize(400, 300);
        frame.setVisible(true);
        // La colocamos en cascada simple
        frame.setLocation(30 * escritorio.getAllFrames().length, 30 * escritorio.getAllFrames().length);

        // Agregar contenido de prueba
        JLabel label = new JLabel("Panel de " + titulo);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(label);

        escritorio.add(frame);
        try { frame.setSelected(true); } catch (Exception e) {}
    }

    public void setMenuListeners(ActionListener listener) {
        menuClientes.addActionListener(listener);
        menuEmpleados.addActionListener(listener);
        menuSalir.addActionListener(listener);
    }

    public JMenuItem getMenuClientes() { return menuClientes; }
    public JMenuItem getMenuEmpleados() { return menuEmpleados; }
    public JMenuItem getMenuSalir() { return menuSalir; }
}