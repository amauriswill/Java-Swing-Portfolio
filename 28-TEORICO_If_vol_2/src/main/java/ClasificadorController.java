package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClasificadorController implements ActionListener {

    private final ClasificadorView vista;
    private final ClasificadorLogica modelo;

    public ClasificadorController(ClasificadorView vista, ClasificadorLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnClasificar()) {
            clasificar();
        } else if (e.getSource() == vista.getBtnComparar()) {
            comparar();
        }
    }

    private void clasificar() {
        try {
            String input = vista.getNumUnico();
            if (input.isEmpty()) return;
            int n = Integer.parseInt(input);

            ClasificadorLogica.Signo signo = modelo.clasificarSigno(n);

            switch (signo) {
                case POSITIVO:
                    vista.mostrarResultadoSigno("POSITIVO (+)", new Color(209, 231, 221), new Color(15, 81, 50));
                    break;
                case NEGATIVO:
                    vista.mostrarResultadoSigno("NEGATIVO (-)", new Color(248, 215, 218), new Color(132, 32, 41));
                    break;
                case CERO:
                    vista.mostrarResultadoSigno("ES CERO (0)", new Color(255, 243, 205), new Color(102, 77, 3));
                    break;
            }
        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese un número entero.");
        }
    }

    private void comparar() {
        try {
            String sa = vista.getNumA();
            String sb = vista.getNumB();
            if (sa.isEmpty() || sb.isEmpty()) return;

            int a = Integer.parseInt(sa);
            int b = Integer.parseInt(sb);

            int mayor = modelo.obtenerMayor(a, b);

            vista.mostrarResultadoMayor("EL MAYOR ES: " + mayor);

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese números enteros.");
        }
    }
}