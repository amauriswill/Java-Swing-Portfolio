package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class PrecioVentaController implements ActionListener {

    private final PrecioVentaView vista;
    private final PrecioVentaLogica modelo;
    private final NumberFormat formatoMoneda;

    public PrecioVentaController(PrecioVentaView vista, PrecioVentaLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBotonListener(this);

        // Configuramos formato de dinero (Ej: $1,250.00)
        this.formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String strCosto = vista.getCosto();
            String strPorc = vista.getPorcentaje();

            if (strCosto.isEmpty() || strPorc.isEmpty()) {
                vista.mostrarError("Por favor ingrese Costo y Porcentaje.");
                return;
            }

            // Convertimos texto a BigDecimal (Mejor que double para dinero)
            BigDecimal costo = new BigDecimal(strCosto);
            BigDecimal porcentaje = new BigDecimal(strPorc);

            // Validaciones de negocio
            if (costo.compareTo(BigDecimal.ZERO) <= 0 || porcentaje.compareTo(BigDecimal.ZERO) < 0) {
                vista.mostrarError("El costo debe ser mayor a 0 y el porcentaje positivo.");
                return;
            }

            // Cálculos
            BigDecimal ganancia = modelo.calcularGanancia(costo, porcentaje);
            BigDecimal precioFinal = modelo.calcularPrecioFinal(costo, ganancia);

            // Formatear salida (Ponemos bonito el dinero)
            String txtGanancia = formatoMoneda.format(ganancia);
            String txtPrecio = formatoMoneda.format(precioFinal);

            // Mostrar en vista
            vista.mostrarResultados(txtGanancia, txtPrecio);

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese valores numéricos válidos (use punto para decimales).");
        }
    }
}