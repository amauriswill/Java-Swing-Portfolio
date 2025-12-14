package org.example;

public class VotacionLogica {

    // Método puro: Calcula la edad
    public int calcularEdad(int anioActual, int anioNacimiento) {
        return anioActual - anioNacimiento;
    }

    // Regla de Negocio: ¿Puede votar?
    public boolean puedeVotar(int edad) {
        return edad >= 18;
    }

    // Validación: Nadie puede nacer en el futuro
    public boolean esFechaValida(int anioActual, int anioNacimiento) {
        return anioNacimiento <= anioActual && anioNacimiento > 1900;
    }
}