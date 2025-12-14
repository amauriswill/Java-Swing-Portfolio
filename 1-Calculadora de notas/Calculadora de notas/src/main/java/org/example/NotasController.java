package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotasController implements ActionListener {

    private final CalculoNotas vista;
    private final NotasLogica modelo;

    // El controlador recibe la Vista y el Modelo para conectarlos
    public NotasController(CalculoNotas vista, NotasLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;

        // Le decimos a la vista: "Cuando pulsen el botón, avísame a MÍ"
        this.vista.setBotonListener(this);
    }

    // Aquí está el COMPORTAMIENTO
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // 1. Pedir datos a la VISTA
            if (vista.getAsistencia().isEmpty() || vista.getEF().isEmpty()) {
                vista.mostrarError("Complete todos los campos.");
                return;
            }

            int asistencia = Integer.parseInt(vista.getAsistencia());
            double tp = Double.parseDouble(vista.getTP());
            double ep = Double.parseDouble(vista.getEP());
            double tf = Double.parseDouble(vista.getTF());
            double ef = Double.parseDouble(vista.getEF());

            // 2. Procesar datos con el MODELO
            double notaTotal = modelo.calcularSuma(asistencia, tp, ep, tf, ef);
            String literal = modelo.obtenerLiteral(notaTotal);

            // 3. Actualizar la VISTA
            if (!modelo.esNotaValida(notaTotal)) {
                vista.mostrarError("La nota excede el límite de 100.");
                vista.setNotaLiteral("", Color.BLACK);
                vista.setNotaNumerica("");
            } else {
                vista.setNotaNumerica(String.format("%.1f", notaTotal));

                // Determinamos color aquí (o podría ser lógica de vista, depende de cuán estricto seas)
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