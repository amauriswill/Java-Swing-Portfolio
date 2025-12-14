package org.example;

public class CalendarioLogica {

    public enum TipoDia {
        LABORAL, FIN_DE_SEMANA, ERROR
    }

    // Versión Clásica (Compatible con Java 8)
    public String obtenerNombreDia(int numero) {
        switch (numero) {
            case 1: return "Lunes";
            case 2: return "Martes";
            case 3: return "Miércoles";
            case 4: return "Jueves";
            case 5: return "Viernes";
            case 6: return "Sábado";
            case 7: return "Domingo";
            default: return "Desconocido";
        }
    }

    public TipoDia obtenerTipoDia(int numero) {
        switch (numero) {
            // Agrupamiento clásico (Falling through)
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return TipoDia.LABORAL;

            case 6:
            case 7:
                return TipoDia.FIN_DE_SEMANA;

            default:
                return TipoDia.ERROR;
        }
    }
}