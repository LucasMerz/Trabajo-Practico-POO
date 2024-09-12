package org.model;

public class Aegis extends Nave {
    public Aegis(int vida, int velocidad) {

        super(vida, velocidad);
        this.capacidadMaxCombustible = 100;
        this.cantCombustible = capacidadMaxCombustible;
    }
    @Override
    public int calcularPoderDeAtaque() {
        int sumaAtaqueArmas = 0;
        if (armas.isEmpty()) {
            return vida/velocidad;
        }
        for(int i = 0; i < armas.size(); i++){
            sumaAtaqueArmas += armas.get(i).getPoderDeAtaque();
        }
        return ((sumaAtaqueArmas) * vida) / velocidad;
    }

}