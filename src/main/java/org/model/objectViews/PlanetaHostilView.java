package org.model.objectViews;

import org.model.Planeta;

public class PlanetaHostilView extends PlanetaView {
    private EnemigoView enemigo;
    private boolean tesoro;

    public PlanetaHostilView(String idPlaneta, String nombrePlaneta, EnemigoView enemigo, boolean tesoro) {
        super(idPlaneta, nombrePlaneta);
        this.tesoro = tesoro;
        this.enemigo = enemigo;
    }

    public boolean isTesoro() {
        return tesoro;
    }

    public EnemigoView getEnemigo() {
        return enemigo;
    }
}
