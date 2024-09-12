package org.view;

import org.controller.GameControllerV2;
import org.model.Nave;
import org.model.objectViews.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class InventarioView extends JFrame {
    public InventarioView(GameControllerV2 controlador) {
        setTitle("Inventario del Jugador");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JFrame pantallaInventario= this;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.cerrarInventario(pantallaInventario);
            }
        });

        NaveView nave = controlador.getNaveView();

        // Crear un panel para mostrar la información del inventario
        JPanel inventarioPanel = new JPanel();
        inventarioPanel.setLayout(new BoxLayout(inventarioPanel, BoxLayout.Y_AXIS));

        inventarioPanel.add(new JLabel("Vida: " + nave.getVida()));
        inventarioPanel.add(new JLabel("Monedas: " + nave.getUadeCoins()));
        inventarioPanel.add(new JLabel("Daño: " + nave.getPoderDeAtaque()));
        if(nave.getEscudo() != null) {
            inventarioPanel.add(new JLabel("Escudo: " + nave.getEscudo().getNombreComponente()+ ", defensa: "  +nave.getEscudo().getDefensa()));
        }else{
            inventarioPanel.add(new JLabel("No tienes escudo equipado"));
        }
        List<ArmaView> armas= nave.getArmas();
        if(!armas.isEmpty()) {
            for (ArmaView arma : armas) {
                inventarioPanel.add(new JLabel("Arma " + arma.getNombreComponente() + ", poder: " + arma.getPoderDeArma()));
            }
        }else{
            inventarioPanel.add(new JLabel("No tienes armas equipadas"));
        }
        inventarioPanel.add(new JLabel("Combustible: " + nave.getCantCombustible()));
        inventarioPanel.add(new JLabel("Velocidad: " + nave.getVelocidad()));
        List<ComponenteView> inventario = nave.getInventario();
        if(!inventario.isEmpty()) {
            for (ComponenteView componente : inventario) {
                inventarioPanel.add(new JLabel("Componente no equipado: " + componente.getNombreComponente()));
            }
        }else{
            inventarioPanel.add(new JLabel("Tu inventario está vacío"));
        }

        // Añadir el panel de inventario al JFrame
        add(new JScrollPane(inventarioPanel), BorderLayout.CENTER);
    }
}
