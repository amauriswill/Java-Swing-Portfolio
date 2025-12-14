package org.example;

public class NotasLogica {
    public double calcularSuma(int asistencia, double tp, double ep, double tf, double ef) {
        return asistencia + tp + ep + tf + ef;
    }

    public String obtenerLiteral(double notaTotal) {
        if (notaTotal > 100) return "ERROR";
        else if (notaTotal >= 90) return "A (Excelente)";
        else if (notaTotal >= 80) return "B (Bueno)";
        else if (notaTotal >= 70) return "C (Suficiente)";
        else return "F (Reprobado)";
    }

    public boolean esNotaValida(double notaTotal) {
        return notaTotal <= 100 && notaTotal >= 0;
    }
}