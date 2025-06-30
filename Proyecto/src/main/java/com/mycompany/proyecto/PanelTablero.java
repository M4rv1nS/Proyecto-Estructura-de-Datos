package com.mycompany.proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelTablero extends JPanel {
    
    private final GameEngine motor;         // Motor del juego que gestiona la lógica
    private final int filas = 5;            // Número de filas del tablero
    private final int columnas = 9;         // Número de columnas del tablero
    private final int tamanoCelda = 80;     // Tamaño de cada celda en píxeles
    public static String plantaSeleccionada = null; // Planta seleccionada por el jugador

    // Constructor
    public PanelTablero(GameEngine motor) {
        
        this.motor = motor;
        setPreferredSize(new Dimension(columnas * tamanoCelda, filas * tamanoCelda));
        setBackground(Color.LIGHT_GRAY);

        // Listener para manejar clics del ratón
        addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
                // Solo permitir colocar plantas en la fase de preparación y si hay una planta seleccionada
                if (plantaSeleccionada != null) {
                    int columna = e.getX() / tamanoCelda;
                    int fila = e.getY() / tamanoCelda;
                    
                    // Verificar que el clic esté dentro de los límites del tablero
                    if (columna >= 0 && columna < columnas && fila >= 0 && fila < filas) {
                        
                        // Intentar colocar la planta
                        if (motor.comprarYColocarPlanta(fila, columna, plantaSeleccionada)) {
                            
                            plantaSeleccionada = null; // Resetear la selección
                            repaint(); // Actualizar la vista
                            
                        } else {
                            
                            JOptionPane.showMessageDialog(null, 
                                "No se puede colocar la planta aquí o no hay suficientes monedas");
                        }
                    }
                }
            }
        });
    }

    // Método para dibujar el tablero y sus elementos
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la grilla
        g.setColor(Color.GRAY);
        for (int i = 0; i <= filas; i++) {
            g.drawLine(0, i * tamanoCelda, columnas * tamanoCelda, i * tamanoCelda); // Líneas horizontales
        }
        for (int j = 0; j <= columnas; j++) {
            g.drawLine(j * tamanoCelda, 0, j * tamanoCelda, filas * tamanoCelda);   // Líneas verticales
        }

        // Dibujar las tropas (plantas y zombies)
        for (int i = 0; i < filas; i++) {
            
            for (int j = 0; j < columnas; j++) {
                
                Casilla c = motor.getTablero().obtenerCasilla(i, j);
                
                if (c.estaOcupada()) {
                    
                    if (c.esZombie) {
                        // Dibujar zombies como círculos rojos
                        g.setColor(Color.RED);
                        g.fillOval(j * tamanoCelda + 10, i * tamanoCelda + 10, 60, 60);
                        
                    } else {
                        // Dibujar plantas (azules para distancia, verdes para otras)
                        g.setColor(c.tropa instanceof PlantaDistancia ? Color.BLUE : Color.GREEN);
                        g.fillRect(j * tamanoCelda + 10, i * tamanoCelda + 10, 60, 60);
                    }
                }
            }
        }

        // Dibujar proyectiles
        ListaProyectil.NodoProyectil actual = motor.proyectiles.getCabeza();
        
        while (actual != null) {
            
            Proyectil p = actual.valor;
            g.setColor(Color.YELLOW);
            g.fillOval(p.getPosicion() * tamanoCelda + 30, p.getCarril() * tamanoCelda + 30, 20, 20);
            actual = actual.siguiente;
            
        }
    }

    // Método para actualizar la vista del tablero
    public void actualizar() {
        repaint();
    }
}