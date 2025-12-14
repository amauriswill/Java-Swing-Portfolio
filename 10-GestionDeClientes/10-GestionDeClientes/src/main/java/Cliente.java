package org.example;

public class Cliente {
    private String codigo;
    private String nombre;
    private String apellidos;
    private String estadoCivil;
    private String sexo;

    public Cliente(String codigo, String nombre, String apellidos, String estadoCivil, String sexo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
    }

    // Convertimos el objeto a un arreglo para la tabla fácilmente
    public Object[] toArray() {
        return new Object[]{codigo, nombre, apellidos, estadoCivil, sexo};
    }

    // Getters básicos
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    // ... otros getters si fueran necesarios para lógica futura
}