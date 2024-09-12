package org.view;

import javax.swing.*;
import java.awt.*;

public class CombustibleInsuficienteView extends JFrame {

    public CombustibleInsuficienteView() {
        setTitle("Combustible Insuficiente");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear y agregar el texto de Combustible Insuficiente
        JLabel mensajeLabel = new JLabel("Combustible insuficiente", JLabel.CENTER);
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(mensajeLabel, BorderLayout.CENTER);

        // Crear un panel para el botón de Aceptar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.setFont(new Font("Arial", Font.PLAIN, 14));
        aceptarButton.addActionListener(e -> this.dispose());

        buttonPanel.add(aceptarButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}