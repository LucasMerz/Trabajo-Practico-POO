package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;

public class NoHayTesoroView extends JFrame {

    public NoHayTesoroView(GameControllerV2 controlador, int idSistema) {

        setTitle("Victoria Sin Tesoro");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear y agregar el texto de victoria sin tesoro
        JLabel victoryLabel = new JLabel("¡Has ganado la pelea, pero no hay tesoro!", JLabel.CENTER);
        victoryLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(victoryLabel, BorderLayout.CENTER);

        // Crear un panel para el botón de continuar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton continueButton = new JButton("Continuar");
        continueButton.setFont(new Font("Arial", Font.PLAIN, 18));
        continueButton.addActionListener(e -> {
            controlador.mostrarPantallaSeleccionPlaneta(idSistema);
            this.dispose();
        });
        continueButton.addActionListener(e -> this.dispose());

        buttonPanel.add(continueButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
