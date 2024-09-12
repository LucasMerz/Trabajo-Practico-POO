package org.view;

import org.controller.GameControllerV2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvanzarSistemaView extends JFrame {
    public AvanzarSistemaView (GameControllerV2 controlador, int idSistema) {

        JFrame pantalla= this;

        setTitle("Avanzar en sistema");
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 600));
        String nombreSistema= controlador.buscarNombreSistema(idSistema);

        // Crear y agregar el JLabel con la imagen de fondo
        ImageIcon imagenDeFondo = controlador.cargarImagen(nombreSistema.replaceAll(" ", ""));
        JLabel fondoLabel = new JLabel(imagenDeFondo);
        fondoLabel.setBounds(0, 0, 1000, 600);

        // Añadir la imagen de fondo al JLayeredPane en la capa más baja
        layeredPane.add(fondoLabel, Integer.valueOf(0));

        // Crear un panel transparente para los componentes
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBounds(0, 0, 1000, 600);

        int dañoAsteroides = controlador.seleccionarSistema(idSistema);

        // Panel para las etiquetas y el botón de inventario
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.setOpaque(false);

        JLabel avanzarSistLabel = new JLabel("Desea avanzar en el " + nombreSistema + "?", JLabel.CENTER);
        avanzarSistLabel.setFont(avanzarSistLabel.getFont().deriveFont(24.0f));
        avanzarSistLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        avanzarSistLabel.setOpaque(true);
        avanzarSistLabel.setBackground(Color.WHITE); // Establecer fondo blanco
        labelsPanel.add(avanzarSistLabel);

        JLabel dañoAsteroidesLabel = new JLabel("El daño causado por los asteroides será: " + dañoAsteroides, JLabel.CENTER);
        dañoAsteroidesLabel.setFont(dañoAsteroidesLabel.getFont().deriveFont(24.0f));
        dañoAsteroidesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dañoAsteroidesLabel.setOpaque(true);
        dañoAsteroidesLabel.setBackground(Color.WHITE); // Establecer fondo blanco
        labelsPanel.add(dañoAsteroidesLabel);

        topPanel.add(labelsPanel, BorderLayout.CENTER);


        // Panel secundario para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Puedes cambiar el layout según tus necesidades
        buttonPanel.setOpaque(false);

        JButton avanzarButton = new JButton("Avanzar");
        avanzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int monedasRecibidas=controlador.avanzarEnSistema(idSistema);
                controlador.mostrarPantallaAtravesarCinturon(idSistema, monedasRecibidas);
                //controlador.mostrarPantallaSeleccionPlaneta(idSistema);
                pantalla.dispose();

                System.out.println("Avanzar en el sistema");
            }
        });
        buttonPanel.add(avanzarButton);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla.dispose();
                System.out.println("Seleccionar sistema");
            }
        });
        buttonPanel.add(volverButton);

        // Botón Inventario en la esquina superior derecha
        JButton inventarioButton = new JButton("Inventario");
        inventarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarPantallaInventario();
            }
        });
        JPanel inventarioPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        inventarioPanel.setOpaque(false);
        inventarioPanel.add(inventarioButton);
        topPanel.add(inventarioPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Añadir el panel principal al JLayeredPane en una capa superior
        layeredPane.add(mainPanel, Integer.valueOf(1));
        add(layeredPane);
        pack();
    }


}
