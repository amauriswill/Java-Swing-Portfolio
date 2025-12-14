package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CircunferenciaController implements ActionListener {

    private final CircunferenciaView vista;
    private final CircunferenciaLogica modelo;
    private final DecimalFormat formato;

    public CircunferenciaController(CircunferenciaView vista, CircunferenciaLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
        // Formato para mostrar hasta 4 decimales
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
            BigDecimal longitud = modelo.calcularLongitud(radio);

            // Formatear
            String resultadoTexto = "L = " + formato.format(longitud);

            // Mostrar (Azul Claro fondo, Azul Oscuro texto)
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