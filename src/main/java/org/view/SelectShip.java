package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;

public class SelectShip extends JFrame {
    private GameControllerV2 controlador;

    public SelectShip(GameControllerV2 controlador) {
        this.controlador = controlador;

        setTitle("Seleccionar Nave");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Etiqueta superior
        JLabel titleLabel = new JLabel("Seleccione la Nave que utilizará en su viaje", JLabel.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(20.0f));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel principal dividido en 4 partes
        JPanel shipsPanel = new JPanel(new GridLayout(2, 2));

        // Crear paneles para cada nave
        JPanel aegisPanel = createShipPanel("Aegis", "Muy resistente frente a los combates de los campos de batalla.\nvida: 300, velocidad: 50, poder de ataque inicial: 10", 1, this);
        JPanel swiftPanel = createShipPanel("Swift", "Perfecta para esquivar piratas enemigos con su velocidad inigualable.\nvida: 170, velocidad: 75, poder de ataque inicial: 270", 2, this);
        JPanel phantomPanel = createShipPanel("Phantom", "Posee tecnología de punta en camuflaje para evadir cinturones de asteroides.\nvida: 220, velocidad: 50, poder de ataque inicial: 165", 3, this);
        JPanel titanPanel = createShipPanel("Titan", "Gran cantidad de combustible pudiendo viajar mayores distancias.\nvida: 250, velocidad: 50, poder de ataque inicial: 150", 4, this);

        // Añadir los paneles al panel principal
        shipsPanel.add(aegisPanel);
        shipsPanel.add(swiftPanel);
        shipsPanel.add(phantomPanel);
        shipsPanel.add(titanPanel);

        mainPanel.add(shipsPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createShipPanel(String shipName, String description, int idNave, JFrame pantalla) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        // Cargar la imagen de fondo
        ImageIcon backgroundImage = controlador.cargarImagen(shipName.toLowerCase());
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());

        // Crear un panel transparente para los componentes
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(new Color(0, 0, 0, 200));  // Fondo gris con baja opacidad
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        JLabel descriptionLabel = new JLabel("<html>" + description.replace("\n", "<br>") + "</html>", JLabel.CENTER);
        descriptionLabel.setFont(descriptionLabel.getFont().deriveFont(14.0f));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionPanel.add(descriptionLabel, BorderLayout.CENTER);

        // Botón de selección
        JButton selectButton = new JButton("Seleccionar " + shipName);
        selectButton.setFont(selectButton.getFont().deriveFont(14.0f));
        selectButton.addActionListener(e -> controlador.seleccionarNave(idNave));
        selectButton.addActionListener(e -> pantalla.dispose());
        contentPanel.add(selectButton, BorderLayout.SOUTH);

        panel.add(contentPanel, BorderLayout.SOUTH);
        panel.add(backgroundLabel, BorderLayout.CENTER);
        panel.add(descriptionPanel, BorderLayout.NORTH);

        return panel;
    }
}
