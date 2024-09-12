package org.view;

import org.controller.GameControllerV2;
import org.model.Enemigo;
import org.model.objectViews.PlanetaView;

import javax.swing.*;
import java.awt.*;

public class VisualizarEnemigoView extends JFrame {
    private GameControllerV2 controlador;

    public VisualizarEnemigoView(GameControllerV2 controlador, int idSistema) {
        this.controlador = controlador;

        setTitle("Visualizar Enemigo");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // Cargar la imagen de fondo
        ImageIcon backgroundImage = controlador.cargarImagen("fondoEnemigo");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 850, 600);

        // Añadir la imagen de fondo al JLayeredPane en la capa más baja
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        // Crear un panel transparente para los componentes
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBounds(0, 0, 800, 600);

        // Panel para los botones superiores
        JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        topButtonPanel.setOpaque(false);

        JButton mapaEstelarButton = new JButton("Mapa estelar");
        mapaEstelarButton.setFont(mapaEstelarButton.getFont().deriveFont(14.0f));
        mapaEstelarButton.addActionListener(e -> controlador.mostrarPantallaMapaEstelar());
        topButtonPanel.add(mapaEstelarButton);

        JButton inventarioButton = new JButton("Inventario");
        inventarioButton.setFont(inventarioButton.getFont().deriveFont(14.0f));
        inventarioButton.addActionListener(e -> controlador.mostrarPantallaInventario());
        topButtonPanel.add(inventarioButton);

        mainPanel.add(topButtonPanel);

        // Etiqueta superior
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        titlePanel.setBackground(new Color(0, 0, 0, 150));  // Fondo gris con baja opacidad
        JLabel titleLabel = new JLabel("<html>Has avistado un enemigo.<br>Todavía estás a tiempo de dar media vuelta y huir del planeta</html>", JLabel.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18.0f));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel);

        // Obtener los datos del enemigo
        Enemigo enemigo = new Enemigo(); // Inicialización de ejemplo
        String enemigoInfo = "<html>Vida: " + enemigo.getVida() + "<br>Poder de ataque: " + enemigo.getPoderDeAtaque() + "</html>";

        // Etiqueta central con la información del enemigo
        JPanel enemigoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        enemigoPanel.setBackground(new Color(0, 0, 0, 150));  // Fondo gris con baja opacidad
        JLabel enemigoLabel = new JLabel(enemigoInfo, JLabel.CENTER);
        enemigoLabel.setFont(enemigoLabel.getFont().deriveFont(16.0f));
        enemigoLabel.setForeground(Color.WHITE);
        enemigoPanel.add(enemigoLabel);
        mainPanel.add(enemigoPanel);

        // Panel para los botones inferiores
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setOpaque(false);

        JButton huirButton = new JButton("Huir");
        huirButton.setFont(huirButton.getFont().deriveFont(16.0f));
        huirButton.addActionListener(e -> this.dispose());
        huirButton.addActionListener(e -> controlador.mostrarPantallaSeleccionPlaneta(controlador.sistemaActual()));
        buttonPanel.add(huirButton);

        JButton pelearButton = new JButton("Pelear");
        pelearButton.setFont(pelearButton.getFont().deriveFont(16.0f));
        pelearButton.addActionListener(e -> {
            controlador.enfrentarEnemigo(idSistema);
            this.dispose();
        });
        buttonPanel.add(pelearButton);

        mainPanel.add(buttonPanel);

        // Añadir el panel principal al JLayeredPane en una capa superior
        layeredPane.add(mainPanel, Integer.valueOf(1));

        add(layeredPane);
    }
}
