package org.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DivisionLogica {

    public enum ResultadoEstado {
        EXITO, ERROR_CERO, ERROR_IGUALES
    }

    public static class ResultadoDivision {
        public final ResultadoEstado estado;
        public final BigDecimal resultado;
        public final BigDecimal mayor;
        public final BigDecimal menor;

        public ResultadoDivision(ResultadoEstado estado, BigDecimal resultado, BigDecimal mayor, BigDecimal menor) {
            this.estado = estado;
            this.resultado = resultado;
            this.mayor = mayor;
            this.menor = menor;
        }
    }

    public ResultadoDivision procesarNumeros(BigDecimal n1, BigDecimal n2) {
        // 1. Validar si son iguales
        if (n1.compareTo(n2) == 0) {
            return new ResultadoDivision(ResultadoEstado.ERROR_IGUALES, null, n1, n2);
        }

        // 2. Determinar Mayor y Menor
        BigDecimal mayor = n1.max(n2);
        BigDecimal menor = n1.min(n2);

        // 3. Validar división por cero (El menor es el divisor)
        if (menor.compareTo(BigDecimal.ZERO) == 0) {
            return new ResultadoDivision(ResultadoEstado.ERROR_CERO, null, mayor, menor);
        }

        // 4. Calcular División (Con 2 decimales de precisión)
        BigDecimal division = mayor.divide(menor, 2, RoundingMode.HALF_UP);

        return new ResultadoDivision(ResultadoEstado.EXITO, division, mayor, menor);
    }
}