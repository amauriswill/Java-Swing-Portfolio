package org.example;

public class DigitoLogica {

    // Enum para los posibles resultados del análisis
    public enum TipoCaracter {
        DIGITO, NO_DIGITO, VACIO, MULTIPLE
    }

    public TipoCaracter analizarEntrada(String texto) {
        if (texto == null || texto.isEmpty()) {
            return TipoCaracter.VACIO;
        }
        if (texto.length() > 1) {
            return TipoCaracter.MULTIPLE; // Error: solo queremos analizar un carácter
        }

        char caracter = texto.charAt(0);

        if (Character.isDigit(caracter)) {
            return TipoCaracter.DIGITO;
        } else {
            return TipoCaracter.NO_DIGITO;
        }
    }
}