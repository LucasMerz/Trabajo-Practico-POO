package org.view;

import org.controller.GameControllerV2;
import org.model.objectViews.NaveView;

import javax.swing.*;
import java.awt.*;

public class AtravesarCinturonView extends JFrame {
    public AtravesarCinturonView(GameControllerV2 controlador, int idSistema, int monedasGanadas) {
        setTitle("Avanzando");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        NaveView nave= controlador.getNaveView();
        int vida= nave.getVida();

        // Crear y agregar el texto de daño recibido y monedas ganadas
        JLabel resultadoLabel = new JLabel("<html>¡Has avanzado en el sistema!<br>Tu vida actual es: " + vida + "<br>Monedas ganadas: " + monedasGanadas + "</html>", JLabel.CENTER);
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(resultadoLabel, BorderLayout.CENTER);

        // Crear un panel para el botón de continuar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton continuarButton = new JButton("Continuar");
        continuarButton.setFont(new Font("Arial", Font.PLAIN, 18));
        continuarButton.addActionListener(e -> {
            controlador.mostrarPantallaSeleccionPlaneta(idSistema);
            this.dispose();
        });

        buttonPanel.add(continuarButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
