package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccesoController implements ActionListener {

    private final AccesoView vista;
    private final AccesoLogica modelo;

    public AccesoController(AccesoView vista, AccesoLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String strEdad = vista.getEdad();
            if (strEdad.isEmpty()) {
                vista.mostrarError("Por favor ingrese la edad.");
                return;
            }

            int edad = Integer.parseInt(strEdad);
            if (edad < 0) {
                vista.mostrarError("La edad no puede ser negativa.");
                return;
            }

            boolean tieneEntrada = vista.tieneEntrada();
            boolean esVip = vista.esVip();

            // Evaluar lógica
            AccesoLogica.ResultadoAcceso resultado = modelo.evaluarAcceso(edad, tieneEntrada, esVip);

            // Actualizar vista
            vista.mostrarResultado(resultado.permitido, resultado.mensaje, resultado.detalleTecnico);

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese una edad válida (número entero).");
        }
    }
}