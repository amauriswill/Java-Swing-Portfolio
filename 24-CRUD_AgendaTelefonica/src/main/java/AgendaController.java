package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AgendaController implements ActionListener, KeyListener {

    private final AgendaView vista;
    private final AgendaLogica modelo;

    public AgendaController(AgendaView vista, AgendaLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setListeners(this, this); // Pasamos ambos listeners
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) {
            guardarContacto();
        } else if (e.getSource() == vista.getBtnLimpiar()) {
            vista.limpiarFormulario();
        }
    }

    private void guardarContacto() {
        String ced = vista.getCedula();
        String nom = vista.getNombre();
        String ape = vista.getApellido();
        String tel = vista.getTelefono();
        String dir = vista.getDireccion();

        if (ced.isEmpty() || nom.isEmpty() || tel.isEmpty()) {
            vista.mostrarError("Cédula, Nombre y Teléfono son obligatorios.");
            return;
        }

        Contacto nuevo = new Contacto(ced, nom, ape, tel, dir);

        if (modelo.agregarContacto(nuevo)) {
            vista.mostrarMensaje("Contacto guardado exitosamente.");
            vista.limpiarFormulario();
            actualizarTabla(); // Refresco automático
        } else {
            vista.mostrarError("Ya existe un contacto con la cédula: " + ced);
        }
    }

    private void actualizarTabla() {
        // Si hay texto en el buscador, filtramos. Si no, mostramos todos.
        String filtro = vista.getBusqueda();
        vista.llenarTabla(modelo.filtrarContactos(filtro));
    }

    // --- KeyListener para Búsqueda en Vivo ---
    @Override
    public void keyReleased(KeyEvent e) {
        actualizarTabla(); // Cada vez que suelta una tecla, filtra
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}
}