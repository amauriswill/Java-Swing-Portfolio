package org.example;

import java.math.BigDecimal;
import java.math.MathContext;

public class CircunferenciaLogica {

    // Usamos una precisión matemática estándar
    private static final MathContext MATH_CONTEXT = MathContext.DECIMAL64;
    // Definimos PI con alta precisión
    private static final BigDecimal PI = new BigDecimal(Math.PI, MATH_CONTEXT);
    private static final BigDecimal DOS = new BigDecimal("2");

    public BigDecimal calcularLongitud(BigDecimal radio) {
        // Fórmula: 2 * PI * radio
        return DOS.multiply(PI, MATH_CONTEXT).multiply(radio, MATH_CONTEXT);
    }

    public boolean esRadioValido(BigDecimal radio) {
        // El radio debe ser positivo
        return radio.compareTo(BigDecimal.ZERO) > 0;
    }
}