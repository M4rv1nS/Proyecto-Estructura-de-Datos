package com.mycompany.proyecto;

import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class ReproductorMP3 {

    private Thread hiloMusica;
    private boolean enEjecucion = false;
    private Player player; // Guardamos la referencia al reproductor

    public void reproducir(String ruta) {
        detener(); // Por si hay música previa
        enEjecucion = true;
        hiloMusica = new Thread(() -> {
            try {
                while (enEjecucion) {
                    InputStream archivo = getClass().getResourceAsStream(ruta);
                    if (archivo == null) {
                        System.err.println("⚠ No se encontró el archivo MP3: " + ruta);
                        return;
                    }
                    BufferedInputStream buffer = new BufferedInputStream(archivo);
                    player = new Player(buffer);
                    player.play();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        hiloMusica.setDaemon(true);
        hiloMusica.start();
    }

    public void detener() {
        enEjecucion = false;
        try {
            if (player != null) {
                player.close(); // 🔹 Esto detiene el audio inmediatamente
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (hiloMusica != null) {
            hiloMusica.interrupt();
        }
    }
}
