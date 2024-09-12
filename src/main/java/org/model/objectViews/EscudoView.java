package org.model.objectViews;

public class EscudoView extends ComponenteView{
    private final int defensaMaxima;
    private int defensa;

    public EscudoView(String idComponente, String nombre, int precio,int defensaMaxima, String tipo) {
        super(idComponente, nombre, precio, tipo);
        this.defensaMaxima = defensaMaxima;
        this.defensa = defensaMaxima;
    }

    public int getDefensaMaxima() {
        return defensaMaxima;
    }

    public int getDefensa() {
        return defensa;
    }
}
