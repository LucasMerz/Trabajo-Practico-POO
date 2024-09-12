package org.model.objectViews;

public class EnemigoView {
    private int vida;
    private int poderDeAtaque;

    public EnemigoView(int vida, int poderDeAtaque) {
        this.vida = vida;
        this.poderDeAtaque = poderDeAtaque;
    }

    public int getVida() {
        return vida;
    }

    public int getPoderDeAtaque() {
        return poderDeAtaque;
    }
}
