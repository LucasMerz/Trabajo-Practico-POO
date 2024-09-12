package org.view;

import javax.swing.*;
import java.awt.*;

public class MonedasInsuficientesView extends JDialog {

    public MonedasInsuficientesView() {

        setTitle("Monedas insuficientes");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear y agregar el mensaje
        JLabel mensajeLabel = new JLabel("No tienes suficientes monedas para comprar eso", JLabel.CENTER);
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(mensajeLabel, BorderLayout.CENTER);

        // Crear un panel para el botón de aceptar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.setFont(new Font("Arial", Font.PLAIN, 14));
        aceptarButton.addActionListener(e -> this.dispose());

        buttonPanel.add(aceptarButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
