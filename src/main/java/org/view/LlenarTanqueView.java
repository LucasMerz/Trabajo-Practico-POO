package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;

public class LlenarTanqueView extends JFrame {
    public LlenarTanqueView(GameControllerV2 controlador, int costeRecarga) {
        setTitle("Tanque Lleno");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un JLayeredPane para la imagen de fondo
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // Cargar la imagen de fondo
        ImageIcon backgroundImage = controlador.cargarImagen("tanqueLleno");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        // Crear un panel transparente para el mensaje
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBounds(0, 0, 800, 600);

        JLabel messageLabel = new JLabel("¡Tanque lleno con éxito!", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        messageLabel.setForeground(Color.WHITE);
        contentPanel.add(messageLabel, BorderLayout.CENTER);

        // Añadir el panel de contenido al JLayeredPane
        layeredPane.add(contentPanel, Integer.valueOf(1));

        add(layeredPane);
    }
}
