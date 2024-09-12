package org.model;

public class Phantom extends Nave {
    public Phantom(int vida, int velocidad) {

        super(vida, velocidad);
        this.capacidadMaxCombustible= 100;
        this.cantCombustible= capacidadMaxCombustible;
    }
    @Override
    public int calcularPoderDeAtaque() {
        int sumaAtaqueArmas = 0;
        if (armas == null) {
            return (vida / 2) + 50;
        }
        for(int i=0; i<armas.size(); i++){
            sumaAtaqueArmas+= armas.get(i).getPoderDeAtaque();
        }
        return (sumaAtaqueArmas * velocidad) + (vida / 2) + 50;
    }
    @Override
    public int atravesarCinturon(int poderDeAsteroides){
        recibirUadeCoins(poderDeAsteroides);
        return poderDeAsteroides;
    }
}