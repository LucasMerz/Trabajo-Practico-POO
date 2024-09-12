package org.model;

import java.util.Random;
import org.model.objectViews.EscudoView;

public class Escudo extends Componente {
    private final int defensaMaxima;
    private int defensa;

    public Escudo(String nombre) {
        super(nombre);
        this.defensa = new Random().nextInt(10, 150);
        this.defensaMaxima = defensa;
        this.precio= calcularPrecio();
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    protected void repararEscudo(){
        setDefensa(defensaMaxima);
    }

    protected String generarIdUnico(){
        return "ESC" + (++contadorID);
    }

    public void equiparComponente(Nave nave){
        nave.equiparEscudo(this);
    }
    public void desequiparComponente(Nave nave){
        nave.desequiparEscudo(this);
    }
    public void eliminarComponente(Nave nave){
        if (nave.escudo.getIdComponente().equals(idComponente)) {
            nave.escudo = null;
        }
    }
    private int calcularPrecio(){
        return 50 +this.defensa/2;
    }
    public EscudoView toView(){
        return new EscudoView(this.idComponente, this.nombre, this.precio, this.defensaMaxima, "escudo");
    }
}