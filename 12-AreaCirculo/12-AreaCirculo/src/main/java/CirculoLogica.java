package org.example;

import java.math.BigDecimal;
import java.math.MathContext;

public class CirculoLogica {

    // Definimos PI con precisión de 64 bits (suficiente para ingeniería)
    private static final MathContext MATH_CONTEXT = MathContext.DECIMAL64;
    private static final BigDecimal PI = new BigDecimal(Math.PI, MATH_CONTEXT);

    // Método puro: Calcula el Área (A = pi * r^2)
    public BigDecimal calcularArea(BigDecimal radio) {
        // Elevamos el radio al cuadrado (pow 2) y multiplicamos por PI
        BigDecimal radioCuadrado = radio.pow(2, MATH_CONTEXT);
        return PI.multiply(radioCuadrado, MATH_CONTEXT);
    }

    // Validación
    public boolean esRadioValido(BigDecimal radio) {
        return radio.compareTo(BigDecimal.ZERO) > 0;
    }
}