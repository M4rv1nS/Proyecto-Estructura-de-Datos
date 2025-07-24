package com.mycompany.proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelControl extends JPanel {

    private GameEngine motor;
    private PanelTablero panelTablero;
    private JLabel lblMonedas, lblOleada, lblTurno;
    private JLabel lblContador;
    private Timer timerPreparacion;
    private int segundosPreparacion = 0;

    private JButton btnAccion, btnIniciarAtaque;
    private String fase = "PREPARACION";

    private JPanel panelSeleccionPlantas, panelColocacionPlantas, panelVistaZombies;
    private JButton btnPlantaDistanciaSelect, btnPlantaMeleeSelect;
    private Lista<String> plantasSeleccionadas = new Lista<>();

    public PanelControl(GameEngine motor, PanelTablero panelTablero) {
        this.motor = motor;
        this.panelTablero = panelTablero;
        setLayout(new FlowLayout());

        lblMonedas = new JLabel("Monedas: " + motor.getMonedas());
        lblOleada = new JLabel("Oleada: " + motor.getOleadaActual());
        lblTurno = new JLabel("Turno: " + motor.getTurnoActual());
        lblContador = new JLabel("Tiempo preparación: 0s");

        add(lblMonedas);
        add(lblOleada);
        add(lblTurno);
        add(lblContador);

        panelTablero.cargarZombiesPreview(motor.getZombiesEnEspera());

        panelSeleccionPlantas = new JPanel();
        panelSeleccionPlantas.setBorder(BorderFactory.createTitledBorder("Selecciona tus plantas"));

        btnPlantaDistanciaSelect = new JButton("Agregar Planta Distancia");
        btnPlantaMeleeSelect = new JButton("Agregar Planta Melee");

        btnPlantaDistanciaSelect.addActionListener(e -> seleccionarPlanta("distancia"));
        btnPlantaMeleeSelect.addActionListener(e -> seleccionarPlanta("melee"));

        panelSeleccionPlantas.add(btnPlantaDistanciaSelect);
        panelSeleccionPlantas.add(btnPlantaMeleeSelect);
        add(panelSeleccionPlantas);

        btnIniciarAtaque = new JButton("Iniciar Ataque");
        btnIniciarAtaque.addActionListener(e -> {
            if (plantasSeleccionadas.getTamano() == 0) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar al menos una planta.");
                return;
            }

            fase = "ATAQUE";
            timerPreparacion.stop();
            btnIniciarAtaque.setEnabled(false);
            btnAccion.setEnabled(true);

            motor.iniciarOleada();
            panelTablero.limpiarZombiesPreview();
            actualizarEstado();

            remove(panelSeleccionPlantas);
            panelSeleccionPlantas = null;

            if (panelVistaZombies != null) {
                remove(panelVistaZombies);
                panelVistaZombies = null;
            }

            mostrarBotonesDeColocacion();
            revalidate();
            repaint();
        });
        add(btnIniciarAtaque);

        btnAccion = new JButton("Siguiente Turno");
        btnAccion.setEnabled(false);
        btnAccion.addActionListener(e -> {
            motor.ejecutarTurno();

            if (motor.isVictoriaMostrada()) {
                btnAccion.setEnabled(false);
                return;
            }

            if (motor.getTurnoActual() < 30 &&
                motor.getZombiesEnEspera().estaVacia() &&
                !motor.enPreparacion() &&
                !motor.hayZombiesEnTablero()) {

                motor.setPreparacion(true);
                motor.generarZombiesOleada();
                panelTablero.cargarZombiesPreview(motor.getZombiesEnEspera());

                reiniciarFasePreparacion();
            }

            actualizarEstado();
            panelTablero.actualizar();
        });
        add(btnAccion);

        timerPreparacion = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                segundosPreparacion++;
                lblContador.setText("Tiempo preparación: " + segundosPreparacion + "s");
            }
        });
        timerPreparacion.start();
    }

    private void seleccionarPlanta(String tipo) {
        if (plantasSeleccionadas.contiene(tipo)) {
            JOptionPane.showMessageDialog(this, "Ya seleccionaste esta planta.");
            return;
        }

        if (plantasSeleccionadas.getTamano() >= 3) {
            JOptionPane.showMessageDialog(this, "Solo puedes seleccionar 3 plantas.");
            return;
        }

        plantasSeleccionadas.agregarFinal(tipo);
        JOptionPane.showMessageDialog(this, "Planta " + tipo + " agregada.");
    }

    private void mostrarBotonesDeColocacion() {
        if (panelColocacionPlantas != null) {
            remove(panelColocacionPlantas);
        }

        panelColocacionPlantas = new JPanel();
        panelColocacionPlantas.setBorder(BorderFactory.createTitledBorder("Plantas disponibles"));

        Lista<String>.Nodo<String> actual = plantasSeleccionadas.getCabeza();
        while (actual != null) {
            String tipo = actual.valor;
            JButton btn = new JButton("Planta " + tipo);
            btn.addActionListener(e -> {
                PanelTablero.plantaSeleccionada = tipo;
                JOptionPane.showMessageDialog(null, "Planta seleccionada: " + tipo);
                panelTablero.actualizar();
            });
            panelColocacionPlantas.add(btn);
            actual = actual.siguiente;
        }

        add(panelColocacionPlantas);
    }

    private void actualizarEstado() {
        lblMonedas.setText("Monedas: " + motor.getMonedas());
        lblOleada.setText("Oleada: " + motor.getOleadaActual());
        lblTurno.setText("Turno: " + motor.getTurnoActual());
    }

    private void reiniciarFasePreparacion() {
        fase = "PREPARACION";
        btnIniciarAtaque.setEnabled(true);
        btnAccion.setEnabled(false);

        plantasSeleccionadas = new Lista<>();

        panelSeleccionPlantas = new JPanel();
        panelSeleccionPlantas.setBorder(BorderFactory.createTitledBorder("Selecciona tus plantas"));

        btnPlantaDistanciaSelect = new JButton("Agregar Planta Distancia");
        btnPlantaMeleeSelect = new JButton("Agregar Planta Melee");

        btnPlantaDistanciaSelect.addActionListener(e -> seleccionarPlanta("distancia"));
        btnPlantaMeleeSelect.addActionListener(e -> seleccionarPlanta("melee"));

        panelSeleccionPlantas.add(btnPlantaDistanciaSelect);
        panelSeleccionPlantas.add(btnPlantaMeleeSelect);

        add(panelSeleccionPlantas);

        segundosPreparacion = 0;
        timerPreparacion.start();

        revalidate();
        repaint();
    }

    public String getFase() {
        return fase;
    }
}
