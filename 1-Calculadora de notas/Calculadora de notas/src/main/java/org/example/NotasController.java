package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotasController implements ActionListener {

    private final CalculoNotas vista;
    private final NotasLogica modelo;


    public NotasController(CalculoNotas vista, NotasLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;


        this.vista.setBotonListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            if (vista.getAsistencia().isEmpty() || vista.getEF().isEmpty()) {
                vista.mostrarError("Complete todos los campos.");
                return;
            }

            int asistencia = Integer.parseInt(vista.getAsistencia());
            double tp = Double.parseDouble(vista.getTP());
            double ep = Double.parseDouble(vista.getEP());
            double tf = Double.parseDouble(vista.getTF());
            double ef = Double.parseDouble(vista.getEF());


            double notaTotal = modelo.calcularSuma(asistencia, tp, ep, tf, ef);
            String literal = modelo.obtenerLiteral(notaTotal);


            if (!modelo.esNotaValida(notaTotal)) {
                vista.mostrarError("La nota excede el límite de 100.");
                vista.setNotaLiteral("", Color.BLACK);
                vista.setNotaNumerica("");
            } else {
                vista.setNotaNumerica(String.format("%.1f", notaTotal));


                Color color = Color.BLACK;
                if (literal.startsWith("A")) color = new Color(34, 139, 34);
                else if (literal.startsWith("B")) color = new Color(30, 144, 255);
                else if (literal.startsWith("C")) color = new Color(255, 140, 0);
                else color = Color.RED;

                vista.setNotaLiteral(literal, color);
            }

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese solo números válidos.");
        }
    }
}
