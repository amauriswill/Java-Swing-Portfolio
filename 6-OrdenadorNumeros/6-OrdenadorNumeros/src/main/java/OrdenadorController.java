package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class OrdenadorController implements ActionListener {

    private final OrdenadorView vista;
    private final OrdenadorLogica modelo;

    public OrdenadorController(OrdenadorView vista, OrdenadorLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // 1. Validar
            if (vista.getNum1().isEmpty() || vista.getNum2().isEmpty()) {
                vista.mostrarError("Por favor complete ambos campos.");
                return;
            }

            // 2. Convertir (Soporte infinito con BigInteger)
            BigInteger n1 = new BigInteger(vista.getNum1());
            BigInteger n2 = new BigInteger(vista.getNum2());

            // 3. Lógica
            String resultado = modelo.ordenarDescendente(n1, n2);

            // 4. Mostrar
            // Fondo Azul Hielo, Texto Azul Oscuro (Alto Contraste)
            vista.mostrarResultado(
                    resultado,
                    new Color(225, 245, 254), // Azul muy muy claro
                    new Color(1, 87, 155)     // Azul oscuro
            );

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese solo números enteros válidos.");
            vista.mostrarResultado(
                    "DATOS INVÁLIDOS",
                    new Color(248, 215, 218),
                    new Color(132, 32, 41)
            );
        }
    }
}