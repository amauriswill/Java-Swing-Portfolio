package org.example;

public class CifrasLogica {

    // Recibimos el String directo para no gastar memoria convirtiendo a número
    public int contarCifrasOptimizado(String textoNumero) {
        if (textoNumero == null || textoNumero.isEmpty()) return 0;

        int longitudTotal = textoNumero.length();
        int inicio = 0;

        // 1. Si tiene signo negativo, empezamos a contar desde el siguiente caracter
        if (textoNumero.charAt(0) == '-') {
            inicio = 1;
        }

        // 2. Optimizacion: Saltar ceros a la izquierda (Ej: "000123" -> cuenta desde el '1')
        // Mientras no lleguemos al final y el caracter sea '0', avanzamos
        while (inicio < longitudTotal && textoNumero.charAt(inicio) == '0') {
            inicio++;
        }

        // 3. Calculamos cifras reales
        int cifras = longitudTotal - inicio;

        // Caso especial: Si el número era "0", "000" o "-0", la resta dará 0 o negativo,
        // pero matemáticamente el número 0 tiene 1 cifra.
        if (cifras <= 0) {
            return 1;
        }

        return cifras;
    }

    // Validación ligera: recorre el string para asegurar que solo son números
    // Esto es mucho más rápido que intentar parsear y capturar una excepción
    public boolean esNumerico(String texto) {
        if (texto == null || texto.isEmpty()) return false;

        int i = 0;
        if (texto.charAt(0) == '-') {
            if (texto.length() == 1) return false; // Solo un "-" no es número
            i = 1;
        }

        for (; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c < '0' || c > '9') {
                return false; // Encontró algo que no es dígito
            }
        }
        return true;
    }
}