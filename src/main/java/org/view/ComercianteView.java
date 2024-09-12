package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;

public class ComercianteView extends JFrame {
    private GameControllerV2 controlador;

    public ComercianteView(GameControllerV2 controlador) {
        this.controlador = controlador;

        setTitle("Planeta Neutral");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear y agregar el texto descriptivo
        JLabel descripcionLabel = new JLabel("Llegaste al planeta y encontraste un comerciante local", JLabel.CENTER);
        descripcionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(descripcionLabel, BorderLayout.NORTH);

        // Crear un JLayeredPane para el topButtonPanel con la imagen de fondo
        JLayeredPane topButtonPanel = new JLayeredPane();
        topButtonPanel.setPreferredSize(new Dimension(250, 100));

        // Cargar la imagen de fondo y redimensionarla
        ImageIcon backgroundImage = controlador.cargarImagen("comerciante");
        ImageIcon resizedBackgroundImage = controlador.resizeIcon(backgroundImage, 250, 500);
        JLabel backgroundLabel = new JLabel(resizedBackgroundImage);
        backgroundLabel.setBounds(0, 0, 250, 500);

        // Crear un panel transparente para los botones "Mapa Estelar" e "Inventario"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 0, 250, 100);
        JButton mapaEstelarButton = new JButton("Mapa Estelar");
        JButton inventarioButton = new JButton("Inventario");

        mapaEstelarButton.addActionListener(e -> controlador.mostrarPantallaMapaEstelar());
        inventarioButton.addActionListener(e -> controlador.mostrarPantallaInventario());

        buttonPanel.add(mapaEstelarButton);
        buttonPanel.add(inventarioButton);

        // Añadir la imagen de fondo y los botones al JLayeredPane
        topButtonPanel.add(backgroundLabel, Integer.valueOf(0));
        topButtonPanel.add(buttonPanel, Integer.valueOf(1));

        // Crear un panel para los botones centrales
        JPanel centerButtonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        JButton comprarButton = createImageButton("Comprar", "comprar", controlador);
        JButton venderButton = createImageButton("Vender", "vender", controlador);
        JButton recargarCombustibleButton = createImageButton("Recargar combustible", "combustible", controlador);

        comprarButton.addActionListener(e -> controlador.mostrarPantallaTienda());
        // venderButton.addActionListener(e -> controlador.mostrarPantallaVenta());
        // recargarCombustibleButton.addActionListener(e -> controlador.recargarCombustible());

        // Botón para recargar combustible

        recargarCombustibleButton.addActionListener(e -> {
            controlador.reabastecerCombustible();
        });
        buttonPanel.add(recargarCombustibleButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        buttonPanel.add(recargarCombustibleButton);

        centerButtonPanel.add(comprarButton);
        centerButtonPanel.add(venderButton);
        centerButtonPanel.add(recargarCombustibleButton);
        centerButtonPanel.add(topButtonPanel);

        mainPanel.add(centerButtonPanel, BorderLayout.CENTER);

        // Crear un panel para el botón de abandonar
        JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton abandonarButton = new JButton("Abandonar planeta");
        abandonarButton.addActionListener(e -> this.dispose());
        abandonarButton.addActionListener(e -> controlador.mostrarPantallaSeleccionPlaneta(controlador.sistemaActual()));

        bottomButtonPanel.add(abandonarButton);

        mainPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JButton createImageButton(String text, String imageName, GameControllerV2 controlador) {
        JButton button = new JButton(text);
        ImageIcon imageIcon = controlador.cargarImagen(imageName);
        ImageIcon resizedIcon = controlador.resizeIcon(imageIcon, 250, 500);
        button.setIcon(resizedIcon);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        return button;
    }
}
