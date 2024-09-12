package org.model;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import org.model.objectViews.ArmaView;

public class Arma extends Componente {
    private int poderDeAtaque;

    public Arma(String nombre) {
        super(nombre);
        this.poderDeAtaque = new Random().nextInt(10, 150);
        this.precio = calcularPrecio();
    }

    public int getPoderDeAtaque() {
        return poderDeAtaque;
    }

    protected String generarIdUnico(){
        return "ARM" + (++contadorID);
    }

    private int calcularPrecio(){
        return 50 +this.poderDeAtaque/2;
    }

    public void equiparComponente(Nave nave){
        nave.equiparArma(this);
    }
    public void desequiparComponente(Nave nave){
        nave.desequiparArma(this);
    }
    public void eliminarComponente(Nave nave){
        for (int i = 0; i < nave.armas.size(); i++) {
            if (nave.armas.get(i).getIdComponente().equals(idComponente)) {
                nave.armas.remove(i);
            }
        }
    }
    public ArmaView toView(){
        return new ArmaView(this.idComponente, this.nombre, this.precio, this.poderDeAtaque, "arma");
    }
}
