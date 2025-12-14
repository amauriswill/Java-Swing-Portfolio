package org.example;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ExportadorService {

    public void exportarCSV(JTable tabla, File archivo) throws IOException {
        TableModel modelo = tabla.getModel();
        FileWriter csv = new FileWriter(archivo);

        for (int i = 0; i < modelo.getColumnCount(); i++) {
            csv.write(modelo.getColumnName(i) + (i == modelo.getColumnCount() - 1 ? "" : ","));
        }
        csv.write("\n");

        for (int i = 0; i < modelo.getRowCount(); i++) {
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                Object data = modelo.getValueAt(i, j);
                String texto = data != null ? data.toString().replace(",", "") : "";
                csv.write(texto + (j == modelo.getColumnCount() - 1 ? "" : ","));
            }
            csv.write("\n");
        }
        csv.close();
    }

    public void exportarPDF(JTable tabla, File archivo, String titulo) throws Exception {
        Document documento = new Document();
        PdfWriter.getInstance(documento, new FileOutputStream(archivo));

        documento.open();

        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.NORMAL);
        Paragraph para = new Paragraph(titulo, fuenteTitulo);
        para.setAlignment(Paragraph.ALIGN_CENTER);
        para.setSpacingAfter(20);
        documento.add(para);

        PdfPTable tablaPDF = new PdfPTable(tabla.getColumnCount());

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tablaPDF.addCell(tabla.getColumnName(i));
        }

        for (int i = 0; i < tabla.getRowCount(); i++) {
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                Object val = tabla.getValueAt(i, j);
                tablaPDF.addCell(val != null ? val.toString() : "");
            }
        }

        documento.add(tablaPDF);
        documento.close();
    }
}