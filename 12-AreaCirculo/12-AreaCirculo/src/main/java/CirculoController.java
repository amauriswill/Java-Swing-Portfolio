package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CirculoController implements ActionListener {

    private final CirculoView vista;
    private final CirculoLogica modelo;
    private final DecimalFormat formato;

    public CirculoController(CirculoView vista, CirculoLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
        // Formato: Hasta 4 decimales
        this.formato = new DecimalFormat("#,##0.0000");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = vista.getRadioInput();

            if (input.isEmpty()) {
                vista.mostrarError("Por favor ingrese el radio.");
                return;
            }

            BigDecimal radio = new BigDecimal(input);

            if (!modelo.esRadioValido(radio)) {
                vista.mostrarError("El radio debe ser mayor a 0.");
                return;
            }

            // Cálculo
            BigDecimal area = modelo.calcularArea(radio);

            // Formatear
            String resultadoTexto = "A = " + formato.format(area);

            // Mostrar (Fondo Azul Claro, Texto Oscuro para contraste)
            vista.mostrarResultado(
                    resultadoTexto,
                    new Color(225, 245, 254),
                    new Color(1, 87, 155)
            );

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese un valor numérico válido.");
        }
    }
}