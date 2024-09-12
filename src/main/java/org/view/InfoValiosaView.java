package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;

public class InfoValiosaView extends JFrame {
    private GameControllerV2 controlador;

    public InfoValiosaView(GameControllerV2 controlador) {
        this.controlador = controlador;

        setTitle("Información Valiosa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear y agregar el texto descriptivo
        JLabel infoLabel = new JLabel("Aquí está la información valiosa que compraste.", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(infoLabel, BorderLayout.CENTER);

        add(mainPanel);
    }
}
