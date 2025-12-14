package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PrestamoController implements ActionListener {

    private final PrestamoView vista;
    private final PrestamoLogica modelo;
    private final ExportadorService exportador;

    public PrestamoController(PrestamoView vista, PrestamoLogica modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.exportador = new ExportadorService();
        this.vista.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vista.getBtnCalcular()) {
            calcular();
        } else if (source == vista.getBtnLimpiar()) {
            vista.limpiarFormulario();
        } else if (source == vista.getBtnPDF()) {
            exportarArchivo("pdf");
        } else if (source == vista.getBtnCSV()) {
            exportarArchivo("csv");
        }
    }

    private void calcular() {
        try {
            String strMonto = vista.getMonto();
            String strTasa = vista.getTasa();
            String strMeses = vista.getMeses();

            if (strMonto.isEmpty() || strTasa.isEmpty() || strMeses.isEmpty()) {
                vista.mostrarError("Complete todos los campos.");
                return;
            }

            BigDecimal monto = new BigDecimal(strMonto);
            BigDecimal tasa = new BigDecimal(strTasa);
            int meses = Integer.parseInt(strMeses);

            if (monto.compareTo(BigDecimal.ZERO) <= 0 || meses <= 0) {
                vista.mostrarError("Monto y Plazo deben ser positivos.");
                return;
            }

            List<PrestamoLogica.CuotaDetalle> tabla = modelo.calcularAmortizacion(monto, tasa, meses);
            List<Object[]> filasVista = new ArrayList<>();
            for (PrestamoLogica.CuotaDetalle fila : tabla) {
                filasVista.add(fila.toArray());
            }

            vista.llenarTabla(filasVista);

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese valores numéricos válidos.");
        }
    }

    private void exportarArchivo(String tipo) {
        if (vista.getTabla().getRowCount() == 0) {
            vista.mostrarError("No hay datos para exportar. Calcule primero.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar " + tipo.toUpperCase());

        if (tipo.equals("pdf")) {
            fileChooser.setFileFilter(new FileNameExtensionFilter("PDF (*.pdf)", "pdf"));
        } else {
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV (*.csv)", "csv"));
        }

        if (fileChooser.showSaveDialog(vista) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            if (!archivo.getName().toLowerCase().endsWith("." + tipo)) {
                archivo = new File(archivo.getAbsolutePath() + "." + tipo);
            }

            try {
                if (tipo.equals("csv")) {
                    exportador.exportarCSV(vista.getTabla(), archivo);
                } else {
                    exportador.exportarPDF(vista.getTabla(), archivo, "Tabla de Amortización");
                }
                JOptionPane.showMessageDialog(vista, "Archivo guardado:\n" + archivo.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
                vista.mostrarError("Error al exportar: " + ex.getMessage());
            }
        }
    }
}