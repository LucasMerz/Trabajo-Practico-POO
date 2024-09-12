package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;

public class EnfrentarEnemigoWinView extends JFrame {

    public EnfrentarEnemigoWinView(GameControllerV2 controlador) {
        setTitle("Victoria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JLayeredPane mainPanel = new JLayeredPane();
        mainPanel.setPreferredSize(new Dimension(800, 600));

        // Cargar la imagen de fondo
        ImageIcon backgroundImage = controlador.cargarImagen("PantallaWin");
        ImageIcon resizedBackgroundImage = controlador.resizeIcon(backgroundImage, 800, 600);
        JLabel backgroundLabel = new JLabel(resizedBackgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);

        // Crear un panel transparente para el texto
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        textPanel.setBounds(0, 250, 800, 100);

        JLabel winLabel = new JLabel("¡Has ganado la pelea!", JLabel.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winLabel.setForeground(Color.WHITE);
        textPanel.add(winLabel, BorderLayout.CENTER);

        // Crear un panel para el botón de continuar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 500, 800, 50);

        JButton continueButton = new JButton("Yay!");
        continueButton.setFont(new Font("Arial", Font.PLAIN, 18));
        continueButton.addActionListener(e -> {
            this.dispose();
        });

        buttonPanel.add(continueButton);

        // Añadir la imagen de fondo, el texto y el botón al JLayeredPane
        mainPanel.add(backgroundLabel, Integer.valueOf(0));
        mainPanel.add(textPanel, Integer.valueOf(1));
        mainPanel.add(buttonPanel, Integer.valueOf(2));

        add(mainPanel);
    }
}
