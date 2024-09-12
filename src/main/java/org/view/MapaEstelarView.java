package org.view;

import org.controller.GameControllerV2;
import org.model.objectViews.SistemaEstelarView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapaEstelarView extends JFrame {
    public MapaEstelarView(GameControllerV2 controlador) {
        setTitle("Mapa Estelar");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo esta ventana
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear y agregar el texto descriptivo
        JLabel sistemasLabel = new JLabel("Seleccione un sistema estelar para visitar:", JLabel.CENTER);
        sistemasLabel.setFont(sistemasLabel.getFont().deriveFont(16.0f));
        mainPanel.add(sistemasLabel, BorderLayout.NORTH);

        // Crear un panel para los botones de sistemas estelares
        JPanel buttonPanel = new JPanel(new GridLayout(2, 5, 5, 5));

        // Obtener los sistemas estelares
        List<SistemaEstelarView> sistemas = controlador.sistemasEstelares();

        // Crear y agregar los botones de selección de sistemas estelares con imágenes de fondo
        for (SistemaEstelarView sistema : sistemas) {
            // Crear un panel para contener el JLabel y el JButton
            JPanel sistemaPanel = new JPanel(new BorderLayout());

            // Crear y agregar el JLabel con el nombre del sistema
            String nombreSistema = sistema.getNombre();
            JLabel sistemaLabel = new JLabel(nombreSistema, JLabel.CENTER);
            sistemaLabel.setFont(sistemaLabel.getFont().deriveFont(12.0f));
            sistemaPanel.add(sistemaLabel, BorderLayout.NORTH);

            // Crear y configurar el JButton con la imagen de fondo
            JButton sistemaButton = new JButton();
            sistemaButton.setFont(sistemaButton.getFont().deriveFont(12.0f));
            // Cargar la imagen de fondo
            ImageIcon icon = controlador.cargarImagen(nombreSistema.replaceAll(" ", ""));
            if (icon != null) {
                sistemaButton.setIcon(icon);
                sistemaButton.setHorizontalTextPosition(JButton.CENTER);
                sistemaButton.setVerticalTextPosition(JButton.BOTTOM);
            }
            int idSistema = sistema.getIdSistema();
            sistemaButton.addActionListener(e -> controlador.mostrarPantallaAvanzarSistema(idSistema));
            sistemaButton.addActionListener(e -> this.dispose());
            sistemaPanel.add(sistemaButton, BorderLayout.CENTER);

            buttonPanel.add(sistemaPanel);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Crear un panel para el botón "Cerrar" y las etiquetas de ubicación
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel buttonPanelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(e -> controlador.cerrarMapaEstelar(this));
        buttonPanelBottom.add(cerrarButton);

        // Etiqueta de ubicación actual
        String ubicacionActual = controlador.ubicacionActual();
        JLabel ubicacionLabel = new JLabel("Ubicación actual: " + ubicacionActual, JLabel.LEFT);
        ubicacionLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Etiqueta de ubicación del tesoro
        String ubicacionDelTesoro = controlador.revelarInfoValiosa();
        JLabel tesoroLabel = new JLabel(ubicacionDelTesoro, JLabel.LEFT);
        tesoroLabel.setFont(new Font("Arial", Font.BOLD, 14));

        bottomPanel.add(ubicacionLabel, BorderLayout.WEST);
        bottomPanel.add(buttonPanelBottom, BorderLayout.EAST);
        bottomPanel.add(tesoroLabel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
