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

public class VentanaJuego extends JFrame {
    private GameEngine motor;
    private PanelTablero panelTablero;
    private PanelControl panelControl;

    public VentanaJuego() {
        setTitle("Plants-Fide");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        motor = new GameEngine();

        panelTablero = new PanelTablero(motor);
        panelControl = new PanelControl(motor, panelTablero);

        add(panelTablero, BorderLayout.CENTER);
        add(panelControl, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaJuego();
    }
}
