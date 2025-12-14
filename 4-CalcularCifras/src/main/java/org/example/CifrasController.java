package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CifrasController implements ActionListener {

    private final CifrasView vista;
    private final CifrasLogica modelo;

    public CifrasController(CifrasView vista, CifrasLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. Obtener texto crudo (Operación barata)
        String input = vista.getNumeroInput().trim();

        if (input.isEmpty()) {
            vista.mostrarError("Por favor ingrese un número.");
            return;
        }

        // 2. Validación eficiente (Sin Try-Catch costosos)
        if (!modelo.esNumerico(input)) {
            vista.mostrarError("Dato inválido. Ingrese solo números enteros.");
            vista.mostrarResultado(
                    "ERROR",
                    new Color(255, 235, 238),
                    new Color(183, 28, 28)
            );
            return;
        }

        // 3. Lógica optimizada (Conteo de caracteres)
        // No importa si el número tiene 1 millón de cifras, esto volará.
        int cantidad = modelo.contarCifrasOptimizado(input);

        // 4. Mensaje
        String mensaje = "TIENE " + cantidad + " CIFRAS";
        if (cantidad == 1) mensaje = "TIENE 1 CIFRA";

        vista.mostrarResultado(
                mensaje,
                new Color(255, 249, 230),
                new Color(0, 0, 0)
        );
    }
}