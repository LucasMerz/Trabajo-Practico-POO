package org.view;

import org.controller.GameControllerV2;
import org.model.objectViews.PlanetaView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ElegirPlanetaView extends JFrame {
    public ElegirPlanetaView(GameControllerV2 controlador, int idSistema) {
        setTitle("Elección de Planeta");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<PlanetaView> planetas = controlador.planetasView(idSistema);
        PlanetaView pH1 = planetas.get(0);
        PlanetaView pH2 = planetas.get(1);
        PlanetaView pH3 = planetas.get(2);
        PlanetaView pN = planetas.get(3);
        PlanetaView pA = planetas.get(4);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear un panel para el título y los botones de la parte superior
        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel tituloLabel = new JLabel("Elige el planeta que deseas visitar", JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(tituloLabel, BorderLayout.CENTER);

        // Crear un panel para los botones "Mapa Estelar" e "Inventario"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton mapaEstelarButton = new JButton("Mapa Estelar");
        JButton inventarioButton = new JButton("Inventario");

        mapaEstelarButton.addActionListener(e -> controlador.mostrarPantallaMapaEstelar());
        inventarioButton.addActionListener(e -> controlador.mostrarPantallaInventario());

        buttonPanel.add(mapaEstelarButton);
        buttonPanel.add(inventarioButton);

        topPanel.add(buttonPanel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel planetButtonPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Crear botones para los planetas con imágenes de fondo
        int costoCombustiblePlanetaHostil= controlador.calcularCombustible(20);
        int costoCombustiblePlanetaNeutral= controlador.calcularCombustible(10);
        int costoCombustiblePlanetaAliado= controlador.calcularCombustible(10);
        planetButtonPanel.add(createPlanetButton(pH1, controlador, 1, idSistema, costoCombustiblePlanetaHostil));
        planetButtonPanel.add(createPlanetButton(pH2, controlador, 1, idSistema, costoCombustiblePlanetaHostil));
        planetButtonPanel.add(createPlanetButton(pH3, controlador, 1, idSistema, costoCombustiblePlanetaHostil));
        planetButtonPanel.add(createPlanetButton(pN, controlador, 2, idSistema, costoCombustiblePlanetaNeutral));
        planetButtonPanel.add(createPlanetButton(pA, controlador, 3, idSistema, costoCombustiblePlanetaAliado));

        mainPanel.add(planetButtonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JButton createPlanetButton(PlanetaView planeta, GameControllerV2 controlador, int num, int idSistema, int combustible) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        // Cargar la imagen de fondo usando el método del controlador
        ImageIcon icon = controlador.cargarImagen(getImagePath(num));
        button.setIcon(controlador.resizeIcon(icon, 500, 200));
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);

        // Crear y configurar el JLabel para el nombre del planeta
        JLabel nameLabel = new JLabel(planeta.getNombrePlaneta(), JLabel.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(18.0f));
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(0, 0, 0, 150)); // Fondo gris con baja opacidad
        nameLabel.setForeground(Color.WHITE); // Letra blanca

        // Crear y configurar el JLabel para el combustible necesario
        JLabel fuelLabel = new JLabel("Combustible necesario: " + combustible, JLabel.CENTER);
        fuelLabel.setFont(fuelLabel.getFont().deriveFont(16.0f));
        fuelLabel.setOpaque(true);
        fuelLabel.setBackground(new Color(0, 0, 0, 150)); // Fondo gris con baja opacidad
        fuelLabel.setForeground(Color.WHITE); // Letra blanca

        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        labelPanel.setOpaque(false);
        labelPanel.add(nameLabel);
        labelPanel.add(fuelLabel);

        button.add(labelPanel, BorderLayout.SOUTH);

        // Añadir acción según el tipo de planeta
        if (num == 1) {
            nameLabel.setForeground(Color.decode("#D7263D"));
            button.addActionListener(e -> controlador.visitarPlanetaHostil(planeta.getIdPlaneta(), idSistema, this));
        } else if (num == 2) {
            nameLabel.setForeground(Color.decode("#EFCA08"));
            button.addActionListener(e -> controlador.visitarPlanetaNeutral(planeta.getIdPlaneta(), this));
        } else if (num == 3) {
            nameLabel.setForeground(Color.decode("#A5BE00"));
            button.addActionListener(e -> controlador.visitarPlanetaAliado(planeta.getIdPlaneta(), this));
        }
        button.addActionListener(e-> this.dispose());

        return button;
    }

    private String getImagePath(int num) {
        if (num == 1) {
            return "planetaHostil";
        } else if (num == 2) {
            return "planetaNeutral";
        } else {
            return "planetaAliado";
        }
    }
}
