package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class ConsumoController implements ActionListener {

    private final ConsumoView vista;
    private final ConsumoLogica modelo;
    private final NumberFormat formatoDinero;

    public ConsumoController(ConsumoView vista, ConsumoLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
        this.formatoDinero = NumberFormat.getCurrencyInstance(Locale.US);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = vista.getKiloInput();

            if (input.isEmpty()) {
                vista.mostrarError("Por favor ingrese el consumo.");
                return;
            }

            // Convertir a entero (los kW suelen medirse en enteros en facturas simples)
            int kw = Integer.parseInt(input);

            if (kw < 0) {
                vista.mostrarError("El consumo no puede ser negativo.");
                return;
            }

            // Calcular
            BigDecimal total = modelo.calcularPago(kw);

            // Formatear
            String textoTotal = formatoDinero.format(total);

            // Mostrar (Fondo Amarillo Claro, Texto Oscuro para dinero)
            vista.mostrarResultado(
                    "TOTAL: " + textoTotal,
                    new Color(255, 249, 230),
                    new Color(0, 0, 0)
            );

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese un valor numérico entero válido.");
        }
    }
}