package org.example;

public class Contacto {
    private String cedula;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;

    public Contacto(String cedula, String nombre, String apellidos, String telefono, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Object[] toArray() {
        return new Object[]{cedula, nombre, apellidos, telefono, direccion};
    }

    // Getters necesarios para validaciones
    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }
}