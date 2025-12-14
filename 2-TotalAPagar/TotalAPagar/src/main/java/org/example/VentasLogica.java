package org.example;

public class VentasLogica {

    // Método puro: Realiza la multiplicación
    public double calcularTotal(int cantidad, double precio) {
        return cantidad * precio;
    }

    // Regla de Negocio: Validar que no sean números negativos ni cero
    public boolean sonDatosValidos(int cantidad, double precio) {
        return cantidad > 0 && precio > 0;
    }
}