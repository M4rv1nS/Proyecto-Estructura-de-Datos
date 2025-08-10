package com.mycompany.proyecto;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

    private GameEngine motor;
    private PanelTablero panelTablero;
    private PanelControl panelControl;
    private ReproductorMP3 musicaJuego;

    public VentanaJuego() {
        setTitle("Plants-Fide");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        musicaJuego = new ReproductorMP3();
        musicaJuego.reproducir("/com/mycompany/proyecto/audio/cancionJuego.mp3");

        motor = new GameEngine();
        panelTablero = new PanelTablero(motor);
        panelControl = new PanelControl(motor, panelTablero);

        add(panelTablero, BorderLayout.CENTER);
        add(panelControl, BorderLayout.SOUTH);
        panelTablero.repaint();

        setVisible(true);
    }
}
