package org.example;

public class AccesoLogica {

    public static class ResultadoAcceso {
        public final boolean permitido;
        public final String mensaje;
        public final String detalleTecnico; // Para fines educativos (muestra el if evaluado)

        public ResultadoAcceso(boolean permitido, String mensaje, String detalle) {
            this.permitido = permitido;
            this.mensaje = mensaje;
            this.detalleTecnico = detalle;
        }
    }

    public ResultadoAcceso evaluarAcceso(int edad, boolean tieneEntrada, boolean esVip) {
        // Lógica educativa: Construimos la explicación paso a paso
        String logica = String.format("if (%b && (%d >= 18 || %b))", tieneEntrada, edad, esVip);

        // 1. Primer filtro: ¿Tiene entrada?
        if (!tieneEntrada) {
            return new ResultadoAcceso(false, "ACCESO DENEGADO: No tiene entrada.", logica + " -> FALSO (Falta Entrada)");
        }

        // 2. Segundo filtro: ¿Cumple requisitos de edad o estatus?
        if (edad >= 18 || esVip) {
            String razon = esVip ? "Pase VIP" : "Mayor de edad";
            return new ResultadoAcceso(true, "BIENVENIDO (" + razon + ")", logica + " -> VERDADERO");
        } else {
            return new ResultadoAcceso(false, "ACCESO DENEGADO: Menor de edad y no es VIP.", logica + " -> FALSO");
        }
    }
}
