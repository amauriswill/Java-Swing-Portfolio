package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class AppController implements ActionListener {

    private LoginView loginView;
    private DashboardView dashboardView;
    private final AuthService authService;

    public AppController(LoginView loginView, AuthService authService) {
        this.loginView = loginView;
        this.authService = authService;
        this.loginView.setLoginListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // LÓGICA DEL LOGIN
        if (loginView != null && e.getSource() instanceof javax.swing.JButton) {
            String user = loginView.getUsuario();
            char[] pass = loginView.getPassword();

            if (authService.validar(user, pass)) {
                abrirDashboard(user);
            } else {
                if (authService.estaBloqueado()) {
                    loginView.bloquearSistema();
                    JOptionPane.showMessageDialog(loginView, "Sistema Bloqueado por seguridad.");
                } else {
                    loginView.mostrarError("Credenciales inválidas. Intentos: " + authService.getIntentosRestantes());
                }
            }
        }

        // LÓGICA DEL DASHBOARD (MENÚS)
        else if (dashboardView != null) {
            if (e.getSource() == dashboardView.getMenuSalir()) {
                System.exit(0);
            }
            else if (e.getSource() == dashboardView.getMenuClientes()) {
                dashboardView.abrirVentanaInterna("Clientes");
            }
            else if (e.getSource() == dashboardView.getMenuEmpleados()) {
                dashboardView.abrirVentanaInterna("Empleados");
            }
        }
    }

    private void abrirDashboard(String usuario) {
        loginView.dispose(); // Cerrar Login
        loginView = null;    // Liberar memoria

        dashboardView = new DashboardView(usuario);
        dashboardView.setMenuListeners(this); // El controlador ahora escucha al dashboard
        dashboardView.setVisible(true);
    }
}