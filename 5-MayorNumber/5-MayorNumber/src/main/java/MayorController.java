package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class MayorController implements ActionListener {

    private final MayorView vista;
    private final MayorLogica modelo;

    public MayorController(MayorView vista, MayorLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // 1. Validar vacíos
            if (vista.getNum1().isEmpty() || vista.getNum2().isEmpty() || vista.getNum3().isEmpty()) {
                vista.mostrarError("Por favor complete los 3 campos.");
                return;
            }

            // 2. Convertir a BigInteger (Soporte para números gigantes)
            BigInteger n1 = new BigInteger(vista.getNum1());
            BigInteger n2 = new BigInteger(vista.getNum2());
            BigInteger n3 = new BigInteger(vista.getNum3());

            // 3. Lógica
            BigInteger mayor = modelo.encontrarMayor(n1, n2, n3);

            // 4. Mostrar Resultado
            // Fondo Verde Claro, Texto Verde Oscuro (Alto Contraste)
            vista.mostrarResultado(
                    "EL MAYOR ES: " + mayor.toString(),
                    new Color(209, 231, 221),
                    new Color(15, 81, 50)
            );

        } catch (NumberFormatException ex) {
            // Error
            vista.mostrarError("Ingrese solo números enteros válidos.");
            vista.mostrarResultado(
                    "ERROR EN DATOS",
                    new Color(248, 215, 218),
                    new Color(132, 32, 41)
            );
        }
    }
}