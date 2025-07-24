package com.mycompany.proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelTablero extends JPanel {

    private final GameEngine motor;
    private final int filas = 5;
    private final int columnas = 9;
    private final int tamanoCeldaAncho = 103;
    private final int tamanoCeldaAlto = 131;
    public static String plantaSeleccionada = null;

    private Image imgZombie;
    private Image imgLanzaGuisantes;
    private Image imgMelee;
    private Image imgFondo;

    private Lista<NodoZombie> previewZombies = new Lista<>();

    public PanelTablero(GameEngine motor) {
        this.motor = motor;
        setPreferredSize(new Dimension(columnas * tamanoCeldaAncho, filas * tamanoCeldaAlto));
        cargarImagenes();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (plantaSeleccionada != null && !motor.estaDerrotado()) {
                    int columna = e.getX() / tamanoCeldaAncho;
                    int fila = e.getY() / tamanoCeldaAlto;

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

    private Image cargarImagen(String ruta) {
        java.net.URL location = getClass().getClassLoader().getResource("com/mycompany/proyecto/img/" + ruta);
        if (location == null) {
            System.err.println("⚠ Imagen no encontrada: " + ruta);
            return null;
        }
        return new ImageIcon(location).getImage();
    }

    private void cargarImagenes() {
        imgZombie = cargarImagen("zombiealien.png");
        imgLanzaGuisantes = cargarImagen("lanzaguisantes.png");
        imgMelee = cargarImagen("melee.png");
        imgFondo = cargarImagen("fondo.png");
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

        // Fondo (no escalar)
        if (imgFondo != null) {
            g.drawImage(imgFondo, 0, 0, columnas * tamanoCeldaAncho, filas * tamanoCeldaAlto, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, columnas * tamanoCeldaAncho, filas * tamanoCeldaAlto);
        }

        // Plantas y Zombies
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla c = motor.getTablero().obtenerCasilla(i, j);
                if (c.estaOcupada() && c.tropa != null) {
                    int x = j * tamanoCeldaAncho;
                    int y = i * tamanoCeldaAlto;

                    if (c.esZombie && imgZombie != null) {
                        int anchoZombie = 120;
                        int altoZombie = 150;
                        int offsetX = x + (tamanoCeldaAncho - anchoZombie) / 2;
                        int offsetY = y + (tamanoCeldaAlto - altoZombie);
                        g.drawImage(imgZombie, offsetX, offsetY, anchoZombie, altoZombie, null);
                    } else if (!c.esZombie) {
                        int anchoPlanta = 70;
                        int altoPlanta = 80;
                        int offsetX = x + (tamanoCeldaAncho - anchoPlanta) / 2;
                        int offsetY = y + (tamanoCeldaAlto - altoPlanta) / 2;

                        if (c.tropa instanceof PlantaDistancia && imgLanzaGuisantes != null) {
                            g.drawImage(imgLanzaGuisantes, offsetX, offsetY, anchoPlanta, altoPlanta, null);
                        } else if (c.tropa instanceof PlantaMelee && imgMelee != null) {
                            g.drawImage(imgMelee, offsetX, offsetY, anchoPlanta, altoPlanta, null);
                        } else {
                            g.setColor(Color.GREEN);
                            g.fillOval(offsetX, offsetY, anchoPlanta, altoPlanta);
                        }
                    }
                }
            }
        }

        // Proyectiles
        ListaProyectil.NodoProyectil actualProyectil = motor.proyectiles.getCabeza();
        while (actualProyectil != null) {
            Proyectil p = actualProyectil.valor;
            int x = p.getPosicion() * tamanoCeldaAncho + tamanoCeldaAncho / 2 - 10;
            int y = p.getCarril() * tamanoCeldaAlto + tamanoCeldaAlto / 2 - 10;
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, 20, 20);
            actualProyectil = actualProyectil.siguiente;
        }

        // Zombies en preparación
        if (motor.enPreparacion()) {
            Lista<NodoZombie>.Nodo<NodoZombie> actual = previewZombies.getCabeza();
            while (actual != null) {
                int x = actual.valor.columna * tamanoCeldaAncho;
                int y = actual.valor.fila * tamanoCeldaAlto;
                if (imgZombie != null) {
                    int anchoZombie = 120;
                    int altoZombie = 150;
                    int offsetX = x + (tamanoCeldaAncho - anchoZombie) / 2;
                    int offsetY = y + (tamanoCeldaAlto - altoZombie);
                    g.drawImage(imgZombie, offsetX, offsetY, anchoZombie, altoZombie, null);
                }
                actual = actual.siguiente;
            }
        }

        // Mostrar mensaje de victoria si aplica
        if (motor.isVictoriaMostrada()) {
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.setColor(Color.YELLOW);
            String mensaje = "¡VICTORIA!";
            int x = (getWidth() - g.getFontMetrics().stringWidth(mensaje)) / 2;
            int y = getHeight() / 2;
            g.drawString(mensaje, x, y);
        }
    }

    public void actualizar() {
        repaint();
    }
}
