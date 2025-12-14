package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentasController implements ActionListener {

    private final TotalPagarView vista;
    private final VentasLogica modelo;

    // Constructor: Recibe las partes y las conecta
    public VentasController(TotalPagarView vista, VentasLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;

        // "Oye Vista, avísame a MÍ cuando pulsen el botón"
        this.vista.setBotonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // 1. Obtener datos crudos de la VISTA
            String strCant = vista.getCantidad();
            String strPrecio = vista.getPrecio();

            // Validación básica de campos vacíos
            if (strCant.isEmpty() || strPrecio.isEmpty()) {
                vista.mostrarError("Por favor complete todos los campos.");
                vista.setTotal("$ 0.00", Color.GRAY);
                return;
            }

            // 2. Convertir datos
            int cantidad = Integer.parseInt(strCant);
            double precio = Double.parseDouble(strPrecio);

            // 3. Validar reglas de negocio con el MODELO
            if (!modelo.sonDatosValidos(cantidad, precio)) {
                vista.mostrarError("La cantidad y el precio deben ser mayores a 0.");
                vista.setTotal("Error", Color.RED);
                return;
            }

            // 4. Calcular usando el MODELO
            double total = modelo.calcularTotal(cantidad, precio);

            // 5. Actualizar la VISTA con el resultado
            String totalFormateado = String.format("$ %.2f", total);
            vista.setTotal(totalFormateado, new Color(25, 135, 84)); // Verde Éxito

        } catch (NumberFormatException ex) {
            // Manejo de errores de formato (si escriben letras)
            vista.mostrarError("Ingrese solo números válidos.");
            vista.setTotal("Error", Color.RED);
        }
    }
}