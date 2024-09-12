package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;

public class NaveReparadaView extends JFrame {
    private GameControllerV2 controlador;

    public NaveReparadaView(GameControllerV2 controlador) {
        this.controlador = controlador;

        setTitle("Nave Reparada");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiado a DISPOSE_ON_CLOSE
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear y agregar el texto descriptivo
        JLabel infoLabel = new JLabel("Tu nave ha sido reparada con éxito.", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(infoLabel, BorderLayout.CENTER);

        // Crear un panel para el botón de aceptar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.setFont(new Font("Arial", Font.PLAIN, 18));
        aceptarButton.addActionListener(e -> this.dispose()); // Cerrar solo esta ventana
        buttonPanel.add(aceptarButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
