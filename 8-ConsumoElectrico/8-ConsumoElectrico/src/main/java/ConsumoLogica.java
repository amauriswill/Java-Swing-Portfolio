package org.example;

import java.math.BigDecimal;

public class ConsumoLogica {

    // Método puro: Calcula el total según el tramo
    public BigDecimal calcularPago(int kw) {
        BigDecimal consumo = new BigDecimal(kw);
        BigDecimal precioUnitario;

        // Lógica escalonada limpia (sin redundancias)
        if (kw <= 150) {
            precioUnitario = new BigDecimal("0.20");
        } else if (kw <= 300) {
            precioUnitario = new BigDecimal("1.88");
        } else if (kw <= 600) {
            precioUnitario = new BigDecimal("3.01");
        } else if (kw <= 1800) {
            precioUnitario = new BigDecimal("4.16");
        } else if (kw <= 3400) {
            // El video usa 187.6, lo mantenemos aunque parece alto
            precioUnitario = new BigDecimal("187.60");
        } else {
            precioUnitario = new BigDecimal("200.00");
        }

        return consumo.multiply(precioUnitario);
    }
}