package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarioController implements ActionListener {

    private final CalendarioView vista;
    private final CalendarioLogica modelo;

    public CalendarioController(CalendarioView vista, CalendarioLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = vista.getDiaInput();
            if (input.isEmpty()) {
                vista.mostrarError("Por favor ingrese un número.");
                return;
            }

            int dia = Integer.parseInt(input);

            // 1. Obtener datos del modelo
            String nombre = modelo.obtenerNombreDia(dia);
            CalendarioLogica.TipoDia tipo = modelo.obtenerTipoDia(dia);

            // 2. Lógica visual (Colores según tipo)
            Color fondo;
            String textoTipo;

            switch (tipo) {
                case LABORAL:
                    fondo = new Color(209, 231, 221); // Verde Claro (Trabajo)
                    textoTipo = "DÍA LABORAL (A trabajar 💼)";
                    break;
                case FIN_DE_SEMANA:
                    fondo = new Color(255, 243, 205); // Amarillo/Naranja (Descanso)
                    textoTipo = "FIN DE SEMANA (Descanso ☀️)";
                    break;
                default:
                    fondo = new Color(248, 215, 218); // Rojo (Error)
                    textoTipo = "NÚMERO INVÁLIDO";
                    break;
            }

            // 3. Mostrar
            vista.mostrarResultado(nombre, textoTipo, fondo);

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese solo números enteros (1-7).");
        }
    }
}