package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParImparController implements ActionListener {

    private final ParImparView vista;
    private final ParImparLogica modelo;

    public ParImparController(ParImparView vista, ParImparLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = vista.getNumeroTexto();

            if (input.isEmpty()) {
                vista.mostrarError("El campo está vacío. Por favor ingrese un número.");
                return;
            }

            int numero = Integer.parseInt(input);
            boolean esPar = modelo.esPar(numero);

            if (esPar) {
                // CASO PAR (Verde Accesible)
                // Fondo: Verde muy claro (#d1e7dd)
                // Texto: Verde muy oscuro (#0f5132) -> Ratio alto, legible
                vista.mostrarResultado(
                        "EL NÚMERO ES PAR",
                        Color.decode("#d1e7dd"),
                        Color.decode("#0f5132")
                );
            } else {
                // CASO IMPAR (Amarillo/Naranja Accesible)
                // Fondo: Amarillo muy claro (#fff3cd)
                // Texto: Marrón/Dorado oscuro (#664d03) -> Evitamos el naranja brillante que vibra
                vista.mostrarResultado(
                        "EL NÚMERO ES IMPAR",
                        Color.decode("#fff3cd"),
                        Color.decode("#664d03")
                );
            }

        } catch (NumberFormatException ex) {
            // CASO ERROR (Rojo Accesible)
            // Fondo: Rojo muy claro (#f8d7da)
            // Texto: Rojo oscuro (#842029)
            vista.mostrarError("Dato inválido. Ingrese solo números enteros.");
        }
    }
}