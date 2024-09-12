package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;

public class ComprarInfoValiosaView extends JFrame {
    private GameControllerV2 controlador;

    public ComprarInfoValiosaView(GameControllerV2 controlador) {
        this.controlador = controlador;

        setTitle("Comprar Información Valiosa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JLayeredPane mainPanel = new JLayeredPane();
        mainPanel.setPreferredSize(new Dimension(800, 600));

        // Cargar la imagen de fondo
        ImageIcon backgroundImage = controlador.cargarImagen("infoTesoro");
        ImageIcon resizedBackgroundImage = controlador.resizeIcon(backgroundImage, 800, 600);
        JLabel backgroundLabel = new JLabel(resizedBackgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);

        // Crear un panel transparente para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 500, 800, 50); // Ajusta la posición del botón

        JButton comprarButton = new JButton("Comprar Información");
        comprarButton.setFont(new Font("Arial", Font.PLAIN, 18));
        comprarButton.addActionListener(e -> {
            controlador.comprarInformacionValiosa();
        });

        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.setFont(new Font("Arial", Font.PLAIN, 18));
        cerrarButton.addActionListener(e -> this.dispose());

        buttonPanel.add(comprarButton);
        buttonPanel.add(cerrarButton);

        // Añadir la imagen de fondo y el botón al JLayeredPane
        mainPanel.add(backgroundLabel, Integer.valueOf(0));
        mainPanel.add(buttonPanel, Integer.valueOf(1));

        add(mainPanel);
    }
}
