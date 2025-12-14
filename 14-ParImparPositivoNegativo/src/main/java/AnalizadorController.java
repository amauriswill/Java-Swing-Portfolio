package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnalizadorController implements ActionListener {

    private final AnalizadorView vista;
    private final AnalizadorLogica modelo;

    public AnalizadorController(AnalizadorView vista, AnalizadorLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = vista.getNumeroInput();
            if (input.isEmpty()) {
                vista.mostrarError("Por favor ingrese un número.");
                return;
            }

            int numero = Integer.parseInt(input);

            // 1. Analizar Paridad
            boolean esPar = modelo.esPar(numero);
            String txtParidad = esPar ? "ES PAR" : "ES IMPAR";

            // Colores Paridad (Azul suave para Par, Naranja suave para Impar)
            Color bgPar = esPar ? new Color(225, 245, 254) : new Color(255, 243, 205);
            Color fgPar = esPar ? new Color(1, 87, 155) : new Color(102, 77, 3);

            // 2. Analizar Signo
            String signo = modelo.determinarSigno(numero);

            // Colores Signo (Verde para Positivo, Rojo para Negativo, Gris para Neutro)
            Color bgSigno, fgSigno;
            switch (signo) {
                case "POSITIVO":
                    bgSigno = new Color(209, 231, 221); // Verde claro
                    fgSigno = new Color(15, 81, 50);    // Verde oscuro
                    break;
                case "NEGATIVO":
                    bgSigno = new Color(248, 215, 218); // Rojo claro
                    fgSigno = new Color(132, 32, 41);   // Rojo oscuro
                    break;
                default: // NEUTRO
                    bgSigno = new Color(226, 227, 229); // Gris claro
                    fgSigno = new Color(65, 70, 75);    // Gris oscuro
                    break;
            }

            // Actualizar Vista de una sola vez
            vista.mostrarResultados(txtParidad, bgPar, fgPar, signo, bgSigno, fgSigno);

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese solo números enteros válidos.");
        }
    }
}