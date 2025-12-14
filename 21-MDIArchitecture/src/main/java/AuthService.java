package org.example;

import java.util.Arrays;

public class AuthService {

    private static final String USUARIO_VALIDO = "amauriswill";
    private static final char[] PASS_VALIDO = {'1', '2', '3', '4', '5'};
    private int intentosFallidos = 0;
    private static final int MAX_INTENTOS = 3;

    public boolean validar(String usuario, char[] password) {
        if (estaBloqueado()) return false;

        boolean usuarioOk = USUARIO_VALIDO.equalsIgnoreCase(usuario);
        boolean passOk = Arrays.equals(PASS_VALIDO, password);
        Arrays.fill(password, '0'); // Limpieza de seguridad

        if (usuarioOk && passOk) {
            intentosFallidos = 0; // Resetear contador al entrar
            return true;
        } else {
            intentosFallidos++;
            return false;
        }
    }

    public boolean estaBloqueado() {
        return intentosFallidos >= MAX_INTENTOS;
    }

    public int getIntentosRestantes() {
        return MAX_INTENTOS - intentosFallidos;
    }
}