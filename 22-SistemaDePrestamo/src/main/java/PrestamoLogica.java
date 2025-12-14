package org.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PrestamoLogica {

    public static class CuotaDetalle {
        public int numeroCuota;
        public BigDecimal cuotaFija;
        public BigDecimal interes;
        public BigDecimal abonoCapital;
        public BigDecimal saldoRestante;

        public Object[] toArray() {
            return new Object[]{numeroCuota, cuotaFija, interes, abonoCapital, saldoRestante};
        }
    }

    public List<CuotaDetalle> calcularAmortizacion(BigDecimal monto, BigDecimal tasaAnual, int meses) {
        List<CuotaDetalle> tabla = new ArrayList<>();

        BigDecimal tasaMensual = tasaAnual.divide(new BigDecimal("1200"), 10, RoundingMode.HALF_UP);
        BigDecimal unoMasI = BigDecimal.ONE.add(tasaMensual);
        BigDecimal potencia = unoMasI.pow(meses, MathContext.DECIMAL64);

        BigDecimal numerador = monto.multiply(tasaMensual).multiply(potencia);
        BigDecimal denominador = potencia.subtract(BigDecimal.ONE);

        BigDecimal cuotaFija = numerador.divide(denominador, 2, RoundingMode.HALF_UP);

        BigDecimal saldoActual = monto;

        for (int i = 1; i <= meses; i++) {
            CuotaDetalle fila = new CuotaDetalle();
            fila.numeroCuota = i;
            fila.cuotaFija = cuotaFija;
            fila.interes = saldoActual.multiply(tasaMensual).setScale(2, RoundingMode.HALF_UP);
            fila.abonoCapital = cuotaFija.subtract(fila.interes);

            saldoActual = saldoActual.subtract(fila.abonoCapital);

            if (i == meses && saldoActual.compareTo(BigDecimal.ZERO) != 0) {
                fila.abonoCapital = fila.abonoCapital.add(saldoActual);
                saldoActual = BigDecimal.ZERO;
            }

            fila.saldoRestante = saldoActual;
            tabla.add(fila);
        }
        return tabla;
    }
}