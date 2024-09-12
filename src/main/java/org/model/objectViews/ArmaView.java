package org.model.objectViews;

public class ArmaView extends ComponenteView {
    private int poderDeArma;

    public ArmaView(String idComponente, String nombreComponente, int precio,int poderDeArma, String tipo) {
        super(idComponente, nombreComponente, precio, tipo);
        this.poderDeArma = poderDeArma;
    }

    public int getPoderDeArma() {
        return poderDeArma;
    }
}
