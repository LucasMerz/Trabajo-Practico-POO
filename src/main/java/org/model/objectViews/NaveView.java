package org.model.objectViews;

import org.model.Arma;
import org.model.Componente;
import org.model.Escudo;
import org.model.Planeta;

import java.util.List;

public class NaveView {
    private int vidaMaxima;
    private int vida;
    private int poderDeAtaque;
    private int velocidad;
    private int capacidadMaxCombustible;
    private int cantCombustible;
    private EscudoView escudo;
    private List<ArmaView> armas;
    private List<ComponenteView> inventario;
    private int uadeCoins;
    private PlanetaView planetaActual;

    public NaveView (int vidaMaxima, int vida, int poderDeAtaque, int velocidad, int capacidadMaxCombustible, int cantCombustible, EscudoView escudo, List<ArmaView> armas,List<ComponenteView> inventario,int uadeCoins, PlanetaView planetaActual) {
        this.vidaMaxima = vidaMaxima;
        this.vida = vida;
        this.poderDeAtaque = poderDeAtaque;
        this.velocidad = velocidad;
        this.capacidadMaxCombustible = capacidadMaxCombustible;
        this.cantCombustible = cantCombustible;
        this.escudo = escudo;
        this.armas = armas;
        this.inventario = inventario;
        this.uadeCoins = uadeCoins;
        this.planetaActual = planetaActual;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVida() {
        return vida;
    }

    public int getPoderDeAtaque() {
        return poderDeAtaque;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getCapacidadMaxCombustible() {
        return capacidadMaxCombustible;
    }

    public int getCantCombustible() {
        return cantCombustible;
    }

    public EscudoView getEscudo() {
        return escudo;
    }

    public List<ArmaView> getArmas() {
        return armas;
    }

    public List<ComponenteView> getInventario() {
        return inventario;
    }

    public int getUadeCoins() {
        return uadeCoins;
    }

    public PlanetaView getPlanetaActual() {
        return planetaActual;
    }
}
