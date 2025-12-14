package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WhileController implements ActionListener {

    private final WhileView vista;
    private final WhileLogica modelo;

    public WhileController(WhileView vista, WhileLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGenerar()) {
            ejecutarSecuencia();
        } else if (e.getSource() == vista.getBtnEvaluar()) {
            evaluarNumero();
        }
    }

    private void ejecutarSecuencia() {
        try {
            int limite = Integer.parseInt(vista.getLimite());
            if (limite <= 0) {
                vista.mostrarError("El límite debe ser mayor a 0.");
                return;
            }
            // Llamada al bucle WHILE del modelo
            List<Integer> secuencia = modelo.generarSecuencia(limite);
            vista.setSecuenciaTexto(secuencia);

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese un número entero válido.");
        }
    }

    private void evaluarNumero() {
        try {
            String txt = vista.getNumeroEvaluar();
            if (txt.isEmpty()) return;

            int numero = Integer.parseInt(txt);
            String resultado = modelo.evaluarNumero(numero);

            // Actualizar vista según resultado
            if (numero == 0) {
                vista.actualizarEstado("FIN DE SESIÓN (0)", Color.LIGHT_GRAY, Color.BLACK);
                vista.agregarHistorial("0 -> FIN");
                // En una app real, aquí podríamos deshabilitar el botón
            } else if (numero > 0) {
                vista.actualizarEstado("ES POSITIVO (+)", new Color(209, 231, 221), new Color(15, 81, 50)); // Verde
                vista.agregarHistorial(numero + " -> Positivo");
            } else {
                vista.actualizarEstado("ES NEGATIVO (-)", new Color(248, 215, 218), new Color(132, 32, 41)); // Rojo
                vista.agregarHistorial(numero + " -> Negativo");
            }

            vista.limpiarInputEvaluar();

        } catch (NumberFormatException ex) {
            vista.mostrarError("Solo números enteros.");
        }
    }
}