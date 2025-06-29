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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelTablero extends JPanel {
    private GameEngine motor;
    private final int filas = 5;
    private final int columnas = 9;
    private final int tamanoCelda = 80;

    public static String plantaSeleccionada = null; // planta seleccionada globalmente

    public PanelTablero(GameEngine motor) {
        this.motor = motor;
        setPreferredSize(new Dimension(columnas * tamanoCelda, filas * tamanoCelda));
        setBackground(Color.LIGHT_GRAY);

        // agregar escucha de clic
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columna = e.getX() / tamanoCelda;
                int fila = e.getY() / tamanoCelda;

                // si hay una planta seleccionada, intentar colocarla
                if (plantaSeleccionada != null) {
                    Tropa planta = null;
                    int costo = 0;

                    if (plantaSeleccionada.equals("distancia")) {
                        planta = new PlantaDistancia();
                        costo = 300;
                    } else if (plantaSeleccionada.equals("melee")) {
                        planta = new PlantaMelee();
                        costo = 200;
                    }

                    if (planta != null) {
                        boolean colocada = motor.comprarYColocarPlanta(fila, columna, planta, costo);
                        if (colocada) {
                            plantaSeleccionada = null; // limpiar seleccion
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "No se puede colocar la planta");
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // dibujar la grilla
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                g.setColor(Color.GRAY);
                g.drawRect(j * tamanoCelda, i * tamanoCelda, tamanoCelda, tamanoCelda);
            }
        }

        // recorrer el tablero y dibujar tropas
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla c = motor.getTablero().obtenerCasilla(i, j);
                if (c.estaOcupada()) {
                    if (c.esZombie) {
                        g.setColor(Color.RED);
                    } else {
                        g.setColor(Color.GREEN);
                    }
                    g.fillOval(j * tamanoCelda + 10, i * tamanoCelda + 10, 60, 60);
                }
            }
        }
    }

    public void actualizar() {
        repaint();
    }
}
