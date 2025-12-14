package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AgendaLogica {

    private final List<Contacto> listaContactos;

    public AgendaLogica() {
        this.listaContactos = new ArrayList<>();
    }

    public boolean agregarContacto(Contacto nuevo) {
        // Validación: No permitir cédulas duplicadas
        if (buscarPorCedula(nuevo.getCedula()).isPresent()) {
            return false; // Ya existe
        }
        listaContactos.add(nuevo);
        return true;
    }

    public Optional<Contacto> buscarPorCedula(String cedula) {
        return listaContactos.stream()
                .filter(c -> c.getCedula().equalsIgnoreCase(cedula))
                .findFirst();
    }

    public List<Contacto> obtenerTodos() {
        return new ArrayList<>(listaContactos);
    }

    public void limpiarAgenda() {
        listaContactos.clear();
    }

    // Método para filtrar (Búsqueda inteligente)
    public List<Contacto> filtrarContactos(String texto) {
        if (texto == null || texto.isEmpty()) return obtenerTodos();

        String query = texto.toLowerCase();
        return listaContactos.stream()
                .filter(c -> c.getNombre().toLowerCase().contains(query) ||
                        c.getApellidos().toLowerCase().contains(query) ||
                        c.getCedula().contains(query))
                .collect(Collectors.toList());
    }
}