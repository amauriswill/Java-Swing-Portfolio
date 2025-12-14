package org.example;

import java.util.Arrays;

public class LoginAuth {

    // Credenciales "hardcoded" para el ejemplo.
    // En un sistema real, esto vendría de una base de datos.
    private static final String USUARIO_VALIDO = "amauriswill";
    private static final char[] PASS_VALIDO = {'1', '2', '3', '4', '5'};

    public boolean autenticar(String usuario, char[] password) {
        // Validamos que no sean nulos
        if (usuario == null || password == null) return false;

        // Comparamos usuario y contraseña (usando Arrays.equals para char[])
        boolean usuarioCorrecto = USUARIO_VALIDO.equals(usuario);
        boolean passCorrecta = Arrays.equals(PASS_VALIDO, password);

        // Limpiamos el array de password de la memoria por seguridad
        Arrays.fill(password, '0');

        return usuarioCorrecto && passCorrecta;
    }
}