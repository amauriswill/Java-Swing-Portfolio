package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VotacionController implements ActionListener {

    private final VotacionView vista;
    private final VotacionLogica modelo;

    public VotacionController(VotacionView vista, VotacionLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String strActual = vista.getAnioActual();
            String strNac = vista.getAnioNacimiento();

            if (strActual.isEmpty() || strNac.isEmpty()) {
                vista.mostrarError("Complete ambos campos.");
                return;
            }

            int actual = Integer.parseInt(strActual);
            int nacimiento = Integer.parseInt(strNac);

            // Validación de Lógica
            if (!modelo.esFechaValida(actual, nacimiento)) {
                vista.mostrarError("Fecha inválida. El año de nacimiento no puede ser mayor al actual.");
                return;
            }

            // Cálculo
            int edad = modelo.calcularEdad(actual, nacimiento);
            boolean puede = modelo.puedeVotar(edad);

            // Actualizar Vista
            vista.mostrarResultado(edad, puede);

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese años válidos (ej: 1990, 2023).");
        }
    }
}