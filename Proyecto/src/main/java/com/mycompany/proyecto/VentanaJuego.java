package com.mycompany.proyecto;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {
    private GameEngine motor;
    private PanelTablero panelTablero;
    private PanelControl panelControl;

    public VentanaJuego() {
        
        // titulo y configuracion basica
        setTitle("Plants-Fide");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // inicializar el motor del juego
        motor = new GameEngine();

        // inicializar los paneles de tablero y control
        panelTablero = new PanelTablero(motor);
        panelControl = new PanelControl(motor, panelTablero);

        // agregar paneles a la ventana principal
        add(panelTablero, BorderLayout.CENTER);  // tablero al centro
        add(panelControl, BorderLayout.SOUTH);   // controles abajo

        setVisible(true);  // mostrar la ventana
    }

    // metodo main que lanza el juego
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaJuego());
    }
}
