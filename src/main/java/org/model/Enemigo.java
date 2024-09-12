package org.model;
import java.util.Random;
import org.model.objectViews.EnemigoView;

public class Enemigo {
    private int vida;
    private int poderDeAtaque;

    public Enemigo() {
        Random random = new Random();
        this.vida = random.nextInt(500, 1000);
        this.poderDeAtaque = random.nextInt(50, 150);
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getPoderDeAtaque() {
        return poderDeAtaque;
    }

    public void recibirDaño(int daño){
        this.vida-=daño;
    }

    public void setPoderDeAtaque(int poderDeAtaque) {
        this.poderDeAtaque = poderDeAtaque;
    }

    public EnemigoView toView(){
        return new EnemigoView(this.vida, this.poderDeAtaque);
    }
}