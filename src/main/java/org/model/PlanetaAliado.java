package org.model;

import org.model.exceptions.MonedasInsuficientesException;
import org.model.objectViews.PlanetaView;
import org.model.objectViews.PlanetaAliadoView;

public class PlanetaAliado extends Planeta{
    public PlanetaAliado(String nombre){
        super(nombre);
    }

    public void repararNave(Nave nave){
        nave.repararNave();
    }

    public void comprarInformacionValiosa(Nave nave) throws MonedasInsuficientesException {
        nave.comprarInformacionValiosa();
    }

    protected String generarIdUnico(){
        return "PA" + (++contadorID);
    }

    @Override
    public PlanetaView toView() {
        return new PlanetaAliadoView(this.idPlaneta,this.nombre);
    }

}
