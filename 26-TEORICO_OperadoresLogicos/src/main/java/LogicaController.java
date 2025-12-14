package org.example;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LogicaController implements ItemListener {

    private final LogicaView vista;
    private final LogicaBooleana modelo;

    public LogicaController(LogicaView vista, LogicaBooleana modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setItemListener(this);
        // Calcular estado inicial
        recalcular();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Actualizar el texto del checkbox que cambió
        if (e.getSource() == vista.getChkA()) {
            vista.actualizarInputVisual(vista.getChkA());
        } else {
            vista.actualizarInputVisual(vista.getChkB());
        }
        recalcular();
    }

    private void recalcular() {
        boolean a = vista.isASelected();
        boolean b = vista.isBSelected();

        LogicaBooleana.ResultadoLogico res = modelo.calcular(a, b);

        vista.actualizarTarjeta(vista.getLblNotA(), res.notA);
        vista.actualizarTarjeta(vista.getLblNotB(), res.notB);
        vista.actualizarTarjeta(vista.getLblAnd(), res.and);
        vista.actualizarTarjeta(vista.getLblOr(), res.or);
        vista.actualizarTarjeta(vista.getLblXor(), res.xor);
    }
}