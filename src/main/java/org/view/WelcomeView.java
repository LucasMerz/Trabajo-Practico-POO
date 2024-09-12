package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WelcomeView extends JFrame {
    private GameControllerV2 controlador;

    public WelcomeView(GameControllerV2 controlador) {
        this.controlador = controlador;

        JFrame pantalla= this;

        setTitle("GALAXIA PERDIDA");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 800));

        // Cargar la imagen de fondo
        ImageIcon backgroundImage = controlador.cargarImagen("fondoInicial");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);

        // Añadir la imagen de fondo al JLayeredPane en la capa más baja
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        // Crear un panel transparente para los componentes
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBounds(0, 0, 800, 600);

        // Añadir KeyListener para detectar la tecla Enter
        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();
        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    controlador.iniciarNombrePiloto();
                    pantalla.dispose();
                }
            }
        });

        // Añadir el panel principal al JLayeredPane en una capa superior
        layeredPane.add(mainPanel, Integer.valueOf(1));

        add(layeredPane);
    }
}
