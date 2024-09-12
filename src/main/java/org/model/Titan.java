package org.model;

import org.model.exceptions.CombustibleInsuficienteException;

public class Titan extends Nave {
    private int viajesPorTurno;
    public Titan(int vida, int velocidad) {

        super(vida, velocidad);
        this.viajesPorTurno=2;
        this.capacidadMaxCombustible = 200;
        this.cantCombustible= capacidadMaxCombustible;
    }

    public int getViajesPorTurno() {
        return viajesPorTurno;
    }

    public void setViajesPorTurno(int viajesPorTurno) {
        this.viajesPorTurno = viajesPorTurno;
    }
    @Override
    public int calcularPoderDeAtaque() {
        int sumaAtaqueArmas=0;
        if (armas == null) {
            return 0;
        }
        for(int i=0; i<armas.size(); i++){
            sumaAtaqueArmas+= armas.get(i).getPoderDeAtaque();
        }
        return (sumaAtaqueArmas * vida * 2) / velocidad;
    }
    @Override
    public void gastarCombustible(int cantidad) throws CombustibleInsuficienteException {
        if(viajesPorTurno%2==0){
            if(cantCombustible < cantidad){
                throw new CombustibleInsuficienteException("No tienes combustible suficiente para realizar el viaje");
            }
            cantCombustible-=cantidad;
            viajesPorTurno-=1;
        }else{
            viajesPorTurno+=1;
        }
    }


}