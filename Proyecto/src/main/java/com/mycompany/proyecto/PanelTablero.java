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

public class PanelTablero extends JPanel {
    private GameEngine motor;
    private final int filas = 5;
    private final int columnas = 9;
    private final int tamanoCelda = 80;

    public PanelTablero(GameEngine motor) {
        this.motor = motor;
        setPreferredSize(new Dimension(columnas * tamanoCelda, filas * tamanoCelda));
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la grilla
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                g.setColor(Color.GRAY);
                g.drawRect(j * tamanoCelda, i * tamanoCelda, tamanoCelda, tamanoCelda);
            }
        }

        // Aquí podrías recorrer carriles y dibujar tropas (planta o zombie)
        // Lo haremos en una fase posterior
    }

    public void actualizar() {
        repaint();
    }
}

