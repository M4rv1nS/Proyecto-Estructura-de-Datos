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
import java.awt.event.*;

public class PanelControl extends JPanel {
    private GameEngine motor;
    private PanelTablero panelTablero;

    private JLabel lblMonedas, lblTurno, lblOleada;
    private JButton btnPlanta1, btnPlanta2, btnTurno;

    public PanelControl(GameEngine motor, PanelTablero panelTablero) {
        this.motor = motor;
        this.panelTablero = panelTablero;

        setLayout(new FlowLayout());

        lblMonedas = new JLabel("Monedas: " + motor.getMonedas());
        lblTurno = new JLabel("Turno: " + motor.getTurnoActual());
        lblOleada = new JLabel("Oleada: " + motor.getOleadaActual());

        btnPlanta1 = new JButton("Comprar Planta Distancia (¢250)");
        btnPlanta2 = new JButton("Comprar Planta Melee (¢400)");
        btnTurno = new JButton("Siguiente Turno");

        add(lblMonedas);
        add(lblTurno);
        add(lblOleada);
        add(btnPlanta1);
        add(btnPlanta2);
        add(btnTurno);

        btnPlanta1.addActionListener((ActionEvent e) -> {
            boolean colocada = motor.comprarYColocarPlanta(0, new PlantaDistancia(), 250);
            if (colocada) actualizarHUD();
        });

        btnPlanta2.addActionListener((ActionEvent e) -> {
            boolean colocada = motor.comprarYColocarPlanta(1, new PlantaMelee(), 400);
            if (colocada) actualizarHUD();
        });

        btnTurno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                motor.ejecutarTurno();
                actualizarHUD();
                panelTablero.actualizar();
            }
        });
    }

    private void actualizarHUD() {
        lblMonedas.setText("Monedas: " + motor.getMonedas());
        lblTurno.setText("Turno: " + motor.getTurnoActual());
        lblOleada.setText("Oleada: " + motor.getOleadaActual());
    }
}

