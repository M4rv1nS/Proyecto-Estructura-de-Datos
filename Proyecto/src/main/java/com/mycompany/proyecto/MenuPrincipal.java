package com.mycompany.proyecto;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private ReproductorMP3 musica;

    public MenuPrincipal() {
        setTitle("Plants-Fide - Menú Principal");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        musica = new ReproductorMP3();
        musica.reproducir("/com/mycompany/proyecto/audio/cancionMenu.mp3");

        JPanel panelFondo = new JPanel() {
            private Image imagen = new ImageIcon(
                    getClass().getResource("/com/mycompany/proyecto/img/menu.png")
            ).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(new GridBagLayout());

        JButton btnIniciar = crearBoton("Iniciar Juego");
        JButton btnInstrucciones = crearBoton("Instrucciones");
        JButton btnSalir = crearBoton("Salir");

        btnIniciar.addActionListener(e -> {
            musica.detener(); // Detener música del menú
            dispose();
            new VentanaJuego(); // Aquí la música del juego se iniciará dentro de VentanaJuego
        });

        btnInstrucciones.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "Defiende tu jardín de los alien-zombies.\n"
                + "Compra plantas, colócalas en el tablero y resiste las oleadas.",
                "Instrucciones",
                JOptionPane.INFORMATION_MESSAGE
        ));

        btnSalir.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFondo.add(btnIniciar, gbc);
        gbc.gridy = 1;
        panelFondo.add(btnInstrucciones, gbc);
        gbc.gridy = 2;
        panelFondo.add(btnSalir, gbc);

        add(panelFondo);
        setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 20));
        boton.setPreferredSize(new Dimension(220, 50));
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(0, 0, 0, 150));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return boton;
    }
}
