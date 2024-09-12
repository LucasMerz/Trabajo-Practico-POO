package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;

public class GameOverView extends JFrame {

    public GameOverView(GameControllerV2 controlador) {

        setTitle("Game Over");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear y agregar el texto de Game Over
        JLabel gameOverLabel = new JLabel("Game Over", JLabel.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 36));
        mainPanel.add(gameOverLabel, BorderLayout.CENTER);

        // Crear un panel para los botones de Volver al inicio y Aceptar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton retryButton = new JButton("Volver al inicio");
        retryButton.setFont(new Font("Arial", Font.PLAIN, 18));
        retryButton.addActionListener(e -> {
            controlador.iniciar();
            this.dispose();
        });

        JButton acceptButton = new JButton("Aceptar");
        acceptButton.setFont(new Font("Arial", Font.PLAIN, 18));
        acceptButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(retryButton);
        buttonPanel.add(acceptButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
