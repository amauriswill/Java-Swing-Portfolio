package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class LlamadasController implements ActionListener {

    private final LlamadasView vista;
    private BigDecimal totalAcumulado = BigDecimal.ZERO;

    public LlamadasController(LlamadasView vista) {
        this.vista = vista;
        this.vista.setListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = vista.getMinutos();
            if (input.isEmpty()) {
                vista.mostrarError("Ingrese la duración.");
                return;
            }

            int minutos = Integer.parseInt(input);
            if (minutos <= 0) {
                vista.mostrarError("Los minutos deben ser mayor a 0.");
                return;
            }

            // 1. Crear Llamada (Lógica de Negocio)
            Llamada.Compania compania = vista.getCompaniaSeleccionada();
            Llamada nuevaLlamada = new Llamada(minutos, compania);

            // 2. Actualizar Tabla
            vista.agregarFila(nuevaLlamada.toArray());

            // 3. Actualizar Total Acumulado
            totalAcumulado = totalAcumulado.add(nuevaLlamada.getTotal());
            vista.actualizarTotal("$" + totalAcumulado.toString());

            vista.limpiarMinutos();

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese un número entero válido.");
        }
    }
}