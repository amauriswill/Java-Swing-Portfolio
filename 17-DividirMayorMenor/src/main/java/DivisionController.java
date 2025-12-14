package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class DivisionController implements ActionListener {

    private final DivisionView vista;
    private final DivisionLogica modelo;

    public DivisionController(DivisionView vista, DivisionLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String s1 = vista.getNum1();
            String s2 = vista.getNum2();

            if (s1.isEmpty() || s2.isEmpty()) {
                vista.mostrarAlerta("Por favor complete ambos campos.");
                return;
            }

            BigDecimal n1 = new BigDecimal(s1);
            BigDecimal n2 = new BigDecimal(s2);

            // Procesar lógica
            DivisionLogica.ResultadoDivision res = modelo.procesarNumeros(n1, n2);

            switch (res.estado) {
                case ERROR_IGUALES:
                    vista.mostrarError("LOS NÚMEROS SON IGUALES");
                    break;
                case ERROR_CERO:
                    vista.mostrarError("NO SE PUEDE DIVIDIR POR 0");
                    break;
                case EXITO:
                    String ecuacion = res.mayor + " ÷ " + res.menor + " = " + res.resultado;
                    vista.mostrarExito("RESULTADO:", ecuacion);
                    break;
            }

        } catch (NumberFormatException ex) {
            vista.mostrarAlerta("Ingrese solo números válidos.");
        }
    }
}