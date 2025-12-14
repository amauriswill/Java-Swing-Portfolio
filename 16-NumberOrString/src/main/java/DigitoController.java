package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitoController implements ActionListener {

    private final DigitoView vista;
    private final DigitoLogica modelo;

    public DigitoController(DigitoView vista, DigitoLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = vista.getTextoEntrada();
        DigitoLogica.TipoCaracter resultado = modelo.analizarEntrada(input);

        switch (resultado) {
            case DIGITO:
                // VERDE ACCESIBLE
                vista.mostrarResultado(
                        "ES UN DÍGITO NUMÉRICO",
                        "✓",
                        new Color(209, 231, 221), // Verde claro
                        new Color(15, 81, 50)     // Verde oscuro
                );
                break;

            case NO_DIGITO:
                // AMARILLO/NARANJA ACCESIBLE
                vista.mostrarResultado(
                        "NO ES UN NÚMERO",
                        "X",
                        new Color(255, 243, 205), // Amarillo claro
                        new Color(102, 77, 3)     // Marrón oscuro
                );
                break;

            case VACIO:
                vista.mostrarError("Por favor escriba un carácter.");
                resetVista();
                break;

            case MULTIPLE:
                vista.mostrarError("Por favor escriba SOLO UN carácter.");
                resetVista();
                break;
        }

        // Opcional: Limpiar input para probar otro rápido
        vista.limpiarEntrada();
    }

    private void resetVista() {
        vista.mostrarResultado("Esperando...", "?", Color.WHITE, Color.GRAY);
    }
}