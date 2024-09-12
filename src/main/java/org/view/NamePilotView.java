package org.view;

import org.controller.GameControllerV2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NamePilotView extends JFrame {
    private GameControllerV2 controlador;

    public NamePilotView(GameControllerV2 controlador) {
        this.controlador = controlador;

        JFrame pantalla= this;

        setTitle("Nombre del Piloto");
        setSize(800, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // Cargar la imagen de fondo
        ImageIcon backgroundImage = controlador.cargarImagen("fondoNombrePiloto");
        Image image = backgroundImage.getImage();
        Image newimg = image.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(newimg);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);

        // Añadir la imagen de fondo al JLayeredPane en la capa más baja
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        // Crear un panel transparente para los componentes
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBounds(0, 0, 800, 600);

        // Panel para el nombre del piloto
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        namePanel.setOpaque(false);
        JLabel nameLabel = new JLabel("Ingrese el nombre del piloto: ");
        nameLabel.setFont(nameLabel.getFont().deriveFont(24.0f));
        nameLabel.setForeground(Color.WHITE);

        // Crear y agregar el campo de texto
        JTextField nameField = new JTextField(15);
        nameField.setFont(nameField.getFont().deriveFont(16.0f));

        // Añadir KeyListener para detectar la tecla Enter
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    controlador.mostrarPantallaSeleccionNave(nameField.getText());
                    pantalla.dispose();
                }
            }
        });

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        mainPanel.add(namePanel, BorderLayout.CENTER);

        // Panel para el botón de enviar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        buttonPanel.setOpaque(false);
        JButton submitButton = new JButton("Enviar");
        submitButton.setFont(submitButton.getFont().deriveFont(16.0f));
        submitButton.addActionListener(e -> controlador.mostrarPantallaSeleccionNave(nameField.getText()));
        buttonPanel.add(submitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Añadir el panel principal al JLayeredPane en una capa superior
        layeredPane.add(mainPanel, Integer.valueOf(1));

        add(layeredPane);
    }
}
