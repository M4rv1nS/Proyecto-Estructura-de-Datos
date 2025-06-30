package com.mycompany.proyecto;

import javax.swing.*;
import java.awt.*;

public class PanelControl extends JPanel {

    private GameEngine motor;
    private PanelTablero panelTablero;
    private JLabel lblMonedas, lblOleada, lblTurno;
    private JLabel[] lblZombies;

    public PanelControl(GameEngine motor, PanelTablero panelTablero) {
        this.motor = motor;
        this.panelTablero = panelTablero;
        setLayout(new FlowLayout());

        lblMonedas = new JLabel("Monedas: " + motor.getMonedas());
        lblOleada = new JLabel("Oleada: " + motor.getOleadaActual());
        lblTurno = new JLabel("Turno: " + motor.getTurnoActual());
        add(lblMonedas);
        add(lblOleada);
        add(lblTurno);

        lblZombies = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            lblZombies[i] = new JLabel("?");
            add(lblZombies[i]);
        }
        actualizarZombies();

        JButton btnDistancia = new JButton("Planta Distancia (250)");
        btnDistancia.addActionListener(e -> PanelTablero.plantaSeleccionada = "distancia");
        JButton btnMelee = new JButton("Planta Melee (400)");
        btnMelee.addActionListener(e -> PanelTablero.plantaSeleccionada = "melee");
        JButton btnAccion = new JButton("Siguiente Turno");
        btnAccion.addActionListener(e -> {
            if (motor.enPreparacion()) {
                motor.iniciarOleada();
                btnAccion.setText("Siguiente Turno");
            } else {
                motor.ejecutarTurno();
            }
            actualizarEstado();
            panelTablero.actualizar();
        });

        add(btnDistancia);
        add(btnMelee);
        add(btnAccion);
    }

    private void actualizarEstado() {
        lblMonedas.setText("Monedas: " + motor.getMonedas());
        lblOleada.setText("Oleada: " + motor.getOleadaActual());
        lblTurno.setText("Turno: " + motor.getTurnoActual());
        actualizarZombies();
    }

    private void actualizarZombies() {
        NodoZombie actual = motor.getZombiesEnEspera().frente;
        for (int i = 0; i < 3; i++) {
            if (actual != null) {
                lblZombies[i].setText("Z (Vida: " + actual.valor.getVida() + ")");
                actual = actual.siguiente;
            } else {
                lblZombies[i].setText("?");
            }
        }
    }
}
