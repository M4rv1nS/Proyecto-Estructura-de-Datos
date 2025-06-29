/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Snake
 */
import javax.swing.*;
import java.awt.*;

public class PanelControl extends JPanel {
    private GameEngine motor;
    private PanelTablero panelTablero;

    private JLabel lblMonedas;
    private JLabel lblOleada;
    private JLabel lblTurno;

    public PanelControl(GameEngine motor, PanelTablero panelTablero) {
        this.motor = motor;
        this.panelTablero = panelTablero;
        setLayout(new FlowLayout());

        // etiquetas de estado
        lblMonedas = new JLabel("Monedas: " + motor.getMonedas());
        lblOleada = new JLabel("Oleada: " + motor.getOleadaActual());
        lblTurno = new JLabel("Turno: " + motor.getTurnoActual());

        add(lblMonedas);
        add(lblOleada);
        add(lblTurno);

        // boton para seleccionar planta de distancia
        JButton btnDistancia = new JButton("Planta Distancia (300)");
        btnDistancia.addActionListener(e -> {
            PanelTablero.plantaSeleccionada = "distancia"; // se elige este tipo
        });

        // boton para seleccionar planta melee
        JButton btnMelee = new JButton("Planta Melee (200)");
        btnMelee.addActionListener(e -> {
            PanelTablero.plantaSeleccionada = "melee"; // se elige este tipo
        });

        // boton para ejecutar turno
        JButton btnTurno = new JButton("Siguiente Turno");
        btnTurno.addActionListener(e -> {
            motor.ejecutarTurno();
            actualizarEstado();
            panelTablero.actualizar();
        });

        add(btnDistancia);
        add(btnMelee);
        add(btnTurno);
    }

    // actualiza etiquetas
    private void actualizarEstado() {
        lblMonedas.setText("Monedas: " + motor.getMonedas());
        lblOleada.setText("Oleada: " + motor.getOleadaActual());
        lblTurno.setText("Turno: " + motor.getTurnoActual());
    }
}
