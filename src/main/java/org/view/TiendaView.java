package org.view;

import org.controller.GameControllerV2;
import org.model.objectViews.ArmaView;
import org.model.objectViews.ComponenteView;
import org.model.objectViews.EscudoView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TiendaView extends JFrame {
    private GameControllerV2 controlador;
    private JLabel monedasLabel;

    public TiendaView(GameControllerV2 controlador) {
        this.controlador = controlador;

        setTitle("Tienda de Armas y Escudos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear y agregar el título
        JLabel tituloLabel = new JLabel("Tienda de Armas y Escudos", JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(tituloLabel, BorderLayout.NORTH);

        // Crear un panel para las dos columnas
        JPanel columnsPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        // Obtener la lista de componentes desde el controlador
        List<ComponenteView> tiendaComponentes = controlador.getTiendaComponentesView();
        List<ArmaView> listaArmasTienda = new ArrayList<>();
        List<EscudoView> listaEscudosTienda = new ArrayList<>();

        for (ComponenteView componente : tiendaComponentes) {
            if ("arma".equals(componente.getTipo())) {
                ArmaView arma = (ArmaView) componente;
                listaArmasTienda.add(arma);
            } else if ("escudo".equals(componente.getTipo())) {
                EscudoView escudo = (EscudoView) componente;
                listaEscudosTienda.add(escudo);
            }
        }

        // Crear paneles para armas y escudos
        JPanel armasPanel = createItemsPanel(listaArmasTienda, "Armas");
        JPanel escudosPanel = createItemsPanel(listaEscudosTienda, "Escudos");

        columnsPanel.add(armasPanel);
        columnsPanel.add(escudosPanel);

        mainPanel.add(columnsPanel, BorderLayout.CENTER);

        // Crear un panel para el texto de monedas disponibles y el botón de salir de la tienda
        JPanel bottomPanel = new JPanel(new BorderLayout());

        monedasLabel = new JLabel("Monedas disponibles: " + controlador.getUadeCoins(), JLabel.CENTER);
        monedasLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        bottomPanel.add(monedasLabel, BorderLayout.NORTH);

        JButton salirButton = new JButton("Salir de la tienda");
        salirButton.setFont(new Font("Arial", Font.PLAIN, 18));
        salirButton.addActionListener(e -> this.dispose());
        bottomPanel.add(salirButton, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createItemsPanel(List<? extends ComponenteView> items, String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JPanel itemsPanel = new JPanel(new GridLayout(items.size(), 1, 10, 10));

        for (ComponenteView item : items) {
            JPanel itemPanel = new JPanel(new BorderLayout());

            // Imagen del producto usando cargarImagen del controlador
            JLabel itemImageLabel = new JLabel();
            // Etiqueta con poder y precio
            JLabel itemInfoLabel = new JLabel();
            if (item instanceof ArmaView) {
                ArmaView arma = (ArmaView) item;
                itemInfoLabel = new JLabel("<html>Poder de Ataque: " + arma.getPoderDeArma() + "<br>Precio: " + arma.getPrecio() + " monedas</html>");
                ImageIcon itemIcon = controlador.cargarImagen("arma");
                itemImageLabel = new JLabel(controlador.resizeIcon(itemIcon, 100, 100));
            } else if (item instanceof EscudoView) {
                EscudoView escudo = (EscudoView) item;
                itemInfoLabel = new JLabel("<html>Defensa: " + escudo.getDefensa() + "<br>Precio: " + escudo.getPrecio() + " monedas</html>");
                ImageIcon itemIcon = controlador.cargarImagen("escudo");
                itemImageLabel = new JLabel(controlador.resizeIcon(itemIcon, 100, 100));
            }
            itemInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            // Botón de comprar
            JButton buyButton = new JButton("Comprar");
            buyButton.setFont(new Font("Arial", Font.PLAIN, 14));
            buyButton.addActionListener(e -> {
                controlador.comprarComponente(item.getIdComponente());
                updateMonedas();
                buyButton.setText("AGOTADO");
                buyButton.setEnabled(false);
            });

            itemPanel.add(itemImageLabel, BorderLayout.WEST);
            itemPanel.add(itemInfoLabel, BorderLayout.CENTER);
            itemPanel.add(buyButton, BorderLayout.EAST);
            itemsPanel.add(itemPanel);
        }

        panel.add(itemsPanel, BorderLayout.CENTER);

        return panel;
    }

    private void updateMonedas() {
        monedasLabel.setText("Monedas disponibles: " + controlador.getUadeCoins());
    }
}
