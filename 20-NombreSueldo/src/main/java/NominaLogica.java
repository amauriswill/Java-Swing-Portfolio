package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NominaLogica {

    private final List<Empleado> empleados;

    public NominaLogica() {
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    public Empleado obtenerMejorPagado() {
        if (empleados.isEmpty()) return null;

        // Magia de Java moderno (Streams): Ordenamos por sueldo y tomamos el mayor
        return empleados.stream()
                .max(Comparator.comparing(Empleado::getSueldo))
                .orElse(null);
    }

    public void limpiar() {
        empleados.clear();
    }
}