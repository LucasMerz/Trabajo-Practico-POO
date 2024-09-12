package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;

public class TiendaAliadaView extends JFrame {
    private GameControllerV2 controlador;

    public TiendaAliadaView(GameControllerV2 controlador) {
        this.controlador = controlador;

        setTitle("Tienda Aliada");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear y agregar el texto descriptivo
        JLabel descripcionLabel = new JLabel("Bienvenido a la tienda aliada", JLabel.CENTER);
        descripcionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(descripcionLabel, BorderLayout.NORTH);

        // Crear un panel para los botones principales
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        // Botón para comprar información valiosa
        JButton infoValiosaButton = new JButton("Comprar Información Valiosa");
        setImageAsButtonBackground(infoValiosaButton, "infoTesoro", 250, 500);
        infoValiosaButton.addActionListener(e -> controlador.mostrarComprarInfoValiosaView());
        buttonPanel.add(infoValiosaButton);

        // Botón para reparar la nave
        JButton repararNaveButton = new JButton("Reparar Nave");
        setImageAsButtonBackground(repararNaveButton, "naveTaller", 250, 500);
        repararNaveButton.addActionListener(e -> controlador.repararNave());
        buttonPanel.add(repararNaveButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Crear un panel para los botones "Mapa Estelar" e "Inventario"
        JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton mapaEstelarButton = new JButton("Mapa Estelar");
        JButton inventarioButton = new JButton("Inventario");

        mapaEstelarButton.addActionListener(e -> controlador.mostrarPantallaMapaEstelar());
        inventarioButton.addActionListener(e -> controlador.mostrarPantallaInventario());

        topButtonPanel.add(mapaEstelarButton);
        topButtonPanel.add(inventarioButton);

        mainPanel.add(topButtonPanel, BorderLayout.NORTH);

        // Crear un panel para el botón "Abandonar planeta"
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton abandonarButton = new JButton("Abandonar planeta");
        abandonarButton.setFont(new Font("Arial", Font.PLAIN, 18));
        abandonarButton.addActionListener(e -> {
            this.dispose();
            controlador.mostrarPantallaSeleccionPlaneta(controlador.sistemaActual());
        });

        bottomPanel.add(abandonarButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void setImageAsButtonBackground(JButton button, String imagePath, int width, int height) {
        ImageIcon icon = controlador.cargarImagen(imagePath);
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        button.setIcon(icon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
    }
}
