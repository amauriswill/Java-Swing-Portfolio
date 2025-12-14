package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class NominaController implements ActionListener {

    private final NominaView vista;
    private final NominaLogica modelo;

    public NominaController(NominaView vista, NominaLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnAgregar()) {
            agregarEmpleado();
        } else if (e.getSource() == vista.getBtnReiniciar()) {
            reiniciar();
        }
    }

    private void agregarEmpleado() {
        try {
            String nombre = vista.getNombre();
            String strSueldo = vista.getSueldo();

            if (nombre.isEmpty() || strSueldo.isEmpty()) {
                vista.mostrarError("Complete todos los campos.");
                return;
            }

            BigDecimal sueldo = new BigDecimal(strSueldo);

            if (sueldo.compareTo(BigDecimal.ZERO) <= 0) {
                vista.mostrarError("El sueldo debe ser mayor a 0.");
                return;
            }

            // 1. Agregar al Modelo
            Empleado nuevo = new Empleado(nombre, sueldo);
            modelo.agregarEmpleado(nuevo);

            // 2. Actualizar Tabla
            vista.agregarFila(nuevo.toArray());

            // 3. ACTUALIZACIÓN AUTOMÁTICA DEL GANADOR
            // Cada vez que agregamos, verificamos si hay un nuevo "rey"
            Empleado ganador = modelo.obtenerMejorPagado();
            if (ganador != null) {
                String txtSueldo = String.format("($ %.2f)", ganador.getSueldo());
                vista.actualizarGanador(ganador.getNombre(), txtSueldo);
            }

            vista.limpiarFormulario();

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese un sueldo válido (ej: 2500.50).");
        }
    }

    private void reiniciar() {
        modelo.limpiar();
        vista.limpiarTabla();
        vista.resetGanador();
        vista.limpiarFormulario();
    }
}