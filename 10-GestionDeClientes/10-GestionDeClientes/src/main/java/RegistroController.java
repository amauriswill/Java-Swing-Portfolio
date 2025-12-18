package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroController implements ActionListener {

    private final RegistroView vista;

    public RegistroController(RegistroView vista) {
        this.vista = vista;
        this.vista.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();

        if (fuente == vista.getBtnNuevo()) {
            limpiar();
        }
        else if (fuente == vista.getBtnAgregar()) {
            agregar();
        }
        else if (fuente == vista.getBtnEliminar()) {
            eliminar();
        }
    }

    private void limpiar() {
        vista.limpiarFormulario();
    }

    private void agregar() {
      
        String codigo = vista.getCodigo();
        String nombre = vista.getNombre();
        String apellidos = vista.getApellidos();
        String estado = vista.getEstadoCivil();
        String sexo = vista.getSexo();

    
        if (codigo.isEmpty() || nombre.isEmpty() || apellidos.isEmpty()) {
            vista.mostrarError("Por favor complete los campos de texto.");
            return;
        }
        if (estado.equals("Seleccionar") || sexo.equals("Seleccionar")) {
            vista.mostrarError("Por favor seleccione Estado Civil y Sexo.");
            return;
        }

        
        Cliente cliente = new Cliente(codigo, nombre, apellidos, estado, sexo);


        vista.agregarFila(cliente.toArray());


        vista.limpiarFormulario();
    }

    private void eliminar() {
        int fila = vista.getFilaSeleccionada();
        if (fila >= 0) {
            vista.eliminarFila(fila);
        } else {
            vista.mostrarError("Seleccione una fila para eliminar.");
        }
    }
}
