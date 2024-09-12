package org.model;

public class Swift extends Nave {
    public Swift(int vida, int velocidad) {

        super(vida, velocidad);
        this.capacidadMaxCombustible= 100;
        this.cantCombustible= capacidadMaxCombustible;
    }

    @Override
    public int calcularPoderDeAtaque() {
        int sumaAtaqueArmas = 0;
        if (armas == null) {
            return vida + cantCombustible;
        }
        for(int i=0; i<armas.size(); i++){
            sumaAtaqueArmas+= armas.get(i).getPoderDeAtaque();
        }
        return (sumaAtaqueArmas * velocidad) + vida + cantCombustible;
    }



}