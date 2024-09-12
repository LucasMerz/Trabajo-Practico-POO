package org.view;

import org.controller.GameControllerV2;
import org.model.objectViews.SistemaEstelarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class SeleccionSistemaView extends JFrame {
    public SeleccionSistemaView(GameControllerV2 controlador) {
        setTitle("Seleccionar Sistema Estelar");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear y agregar el texto descriptivo
        JLabel sistemasLabel = new JLabel("Seleccione un sistema estelar para visitar:", JLabel.CENTER);
        sistemasLabel.setFont(sistemasLabel.getFont().deriveFont(24.0f));
        mainPanel.add(sistemasLabel, BorderLayout.NORTH);

        // Crear un panel para los botones de sistemas estelares
        JPanel buttonPanel = new JPanel(new GridLayout(2, 5, 10, 10));

        // Obtener los sistemas estelares
        List<SistemaEstelarView> sistemas= controlador.sistemasEstelares();

        // Crear y agregar los botones de selección de sistemas estelares con imágenes de fondo
        for (SistemaEstelarView sistema : sistemas) {
            // Crear un panel para contener el JLabel y el JButton
            JPanel sistemaPanel = new JPanel(new BorderLayout());

            // Crear y agregar el JLabel con el nombre del sistema
            String nombreSistema= sistema.getNombre();
            JLabel sistemaLabel = new JLabel(nombreSistema, JLabel.CENTER);
            sistemaLabel.setFont(sistemaLabel.getFont().deriveFont(14.0f));
            sistemaPanel.add(sistemaLabel, BorderLayout.NORTH);

            // Crear y configurar el JButton con la imagen de fondo
            JButton sistemaButton = new JButton();
            sistemaButton.setFont(sistemaButton.getFont().deriveFont(14.0f));
            // Cargar la imagen de fondo
            ImageIcon icon = controlador.cargarImagen(nombreSistema.replaceAll(" ", ""));
            if (icon != null) {
                sistemaButton.setIcon(icon);
                sistemaButton.setHorizontalTextPosition(JButton.CENTER);
                sistemaButton.setVerticalTextPosition(JButton.BOTTOM);
            }
            int idSistema= sistema.getIdSistema();
            sistemaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controlador.mostrarPantallaAvanzarSistema(idSistema);

                }
            });
            sistemaButton.addActionListener(e -> controlador.seleccionarSistema(idSistema));
            sistemaPanel.add(sistemaButton, BorderLayout.CENTER);

            buttonPanel.add(sistemaPanel);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

}

