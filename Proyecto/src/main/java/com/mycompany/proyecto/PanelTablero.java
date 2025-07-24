package com.mycompany.proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PanelTablero extends JPanel {

    private final GameEngine motor;
    private final int filas = 5;
    private final int columnas = 9;
    private final int tamanoCelda = 80;
    public static String plantaSeleccionada = null;

    private Image imgZombie;
    private Image imgLanzaGuisantes;
    private Image imgMelee;

    private Lista<NodoZombie> previewZombies = new Lista<>();

    public PanelTablero(GameEngine motor) {
        this.motor = motor;
        setPreferredSize(new Dimension(columnas * tamanoCelda, filas * tamanoCelda));
        setBackground(Color.LIGHT_GRAY);
        cargarImagenes();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (plantaSeleccionada != null && !motor.estaDerrotado()) {
                    int columna = e.getX() / tamanoCelda;
                    int fila = e.getY() / tamanoCelda;

                    if (columna >= 0 && columna < columnas && fila >= 0 && fila < filas) {
                        if (motor.comprarYColocarPlanta(fila, columna, plantaSeleccionada)) {
                            plantaSeleccionada = null;
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "No se puede colocar la planta aquí o no hay suficientes monedas");
                        }
                    }
                }
            }
        });
    }

    private void cargarImagenes() {
        imgZombie = cargarImagen("/com/mycompany/proyecto/img/zombiealien.webp");
        imgLanzaGuisantes = cargarImagen("/com/mycompany/proyecto/img/lanzaguisantes.webp");
        imgMelee = cargarImagen("/com/mycompany/proyecto/img/melee.webp");
    }

    private Image cargarImagen(String ruta) {
        java.net.URL location = getClass().getResource(ruta);
        if (location == null) {
            System.err.println("⚠ No se pudo cargar la imagen: " + ruta);
            return new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB); // imagen vacía
        }
        return new ImageIcon(location).getImage();
    }

    public void cargarZombiesPreview(ColaZombies cola) {
        previewZombies = new Lista<>();

        int fila = 0;
        int columna = columnas;

        NodoZombie actual = cola.frente;
        while (actual != null && fila < filas) {
            AlienZombie zombieCopia = new AlienZombie();
            zombieCopia.setPosicion(fila, columna);

            NodoZombie copia = new NodoZombie(zombieCopia);
            copia.fila = fila;
            copia.columna = columna;

            previewZombies.agregarFinal(copia);

            fila++;
            actual = actual.siguiente;
        }

        repaint();
    }

    public void limpiarZombiesPreview() {
        previewZombies = new Lista<>();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Grilla
        g.setColor(Color.GRAY);
        for (int i = 0; i <= filas; i++) {
            g.drawLine(0, i * tamanoCelda, columnas * tamanoCelda, i * tamanoCelda);
        }
        for (int j = 0; j <= columnas; j++) {
            g.drawLine(j * tamanoCelda, 0, j * tamanoCelda, filas * tamanoCelda);
        }

        // Plantas y Zombies reales en el tablero
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla c = motor.getTablero().obtenerCasilla(i, j);
                if (c.estaOcupada()) {
                    int x = j * tamanoCelda + 10;
                    int y = i * tamanoCelda + 10;

                    if (c.esZombie) {
                        g.drawImage(imgZombie, x, y, 60, 60, this);
                    } else {
                        if (c.tropa instanceof PlantaDistancia) {
                            g.drawImage(imgLanzaGuisantes, x, y, 60, 60, this);
                        } else {
                            g.drawImage(imgMelee, x, y, 60, 60, this);
                        }
                    }
                }
            }
        }

        // Proyectiles
        ListaProyectil.NodoProyectil actualProyectil = motor.proyectiles.getCabeza();
        while (actualProyectil != null) {
            Proyectil p = actualProyectil.valor;
            g.setColor(Color.YELLOW);
            g.fillOval(p.getPosicion() * tamanoCelda + 30, p.getCarril() * tamanoCelda + 30, 20, 20);
            actualProyectil = actualProyectil.siguiente;
        }

        // Zombies de preview en preparación
        if (motor.enPreparacion()) {
            Lista<NodoZombie>.Nodo<NodoZombie> actual = previewZombies.getCabeza();
            while (actual != null) {
                int x = actual.valor.columna * tamanoCelda + 10;
                int y = actual.valor.fila * tamanoCelda + 10;
                g.drawImage(imgZombie, x, y, 60, 60, this);
                actual = actual.siguiente;
            }
        }
    }

    public void actualizar() {
        repaint();
    }
}
