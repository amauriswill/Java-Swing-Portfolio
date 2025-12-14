package org.example;

import javax.swing.Timer; // Importante: Usar el Timer de Swing
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private final LoginView loginView;
    private final LoginAuth authModel;
    private Timer progressTimer;
    private int progresoActual = 0;

    public LoginController(LoginView view, LoginAuth model) {
        this.loginView = view;
        this.authModel = model;
        this.loginView.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario = loginView.getUsuario();
        char[] pass = loginView.getPassword();

        // 1. Validación básica de campos vacíos
        if (usuario.isEmpty() || pass.length == 0) {
            loginView.mostrarError("Por favor ingrese usuario y contraseña.");
            return;
        }

        // 2. Autenticación con el Modelo
        if (authModel.autenticar(usuario, pass)) {
            iniciarCargaExitos(usuario);
        } else {
            loginView.mostrarError("Credenciales incorrectas.");
        }
    }

    // Método para manejar la animación de carga si el login es correcto
    private void iniciarCargaExitos(String usuarioConfirmado) {
        // Bloquear controles para que no den clic de nuevo
        loginView.setControlesHabilitados(false);
        // Mostrar la barra
        loginView.mostrarBarraProgreso(true);
        progresoActual = 0;

        // Configurar el TIMER: Se ejecutará cada 30ms
        progressTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                progresoActual++;
                loginView.actualizarProgreso(progresoActual);

                if (progresoActual >= 100) {
                    // FIN DE LA CARGA
                    progressTimer.stop();
                    abrirSistemaPrincipal(usuarioConfirmado);
                }
            }
        });

        // Iniciar el timer
        progressTimer.start();
    }

    private void abrirSistemaPrincipal(String usuario) {
        // 1. Cerrar (liberar recursos) la ventana de login
        loginView.dispose();

        // 2. Crear y mostrar la nueva ventana del sistema
        SistemaView sistemaView = new SistemaView(usuario);
        sistemaView.setVisible(true);
    }
}