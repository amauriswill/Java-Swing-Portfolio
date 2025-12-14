package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrecioVentaLogica {

    // Método puro: Calcula la ganancia monetaria basada en un porcentaje
    // Fórmula: Costo * (Porcentaje / 100)
    public BigDecimal calcularGanancia(BigDecimal costo, BigDecimal porcentaje) {
        // Dividimos el porcentaje entre 100
        BigDecimal factor = porcentaje.divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
        // Multiplicamos por el costo
        return costo.multiply(factor);
    }

    // Método puro: Suma el costo más la ganancia para dar el precio final
    public BigDecimal calcularPrecioFinal(BigDecimal costo, BigDecimal ganancia) {
        return costo.add(ganancia);
    }
}