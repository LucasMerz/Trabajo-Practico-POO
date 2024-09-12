package org.model.objectViews;

public abstract class PlanetaView {
    protected String idPlaneta;
    protected String nombrePlaneta;

    public PlanetaView(String idPlaneta, String nombrePlaneta) {
        this.idPlaneta = idPlaneta;
        this.nombrePlaneta = nombrePlaneta;
    }

    public String getIdPlaneta() {
        return idPlaneta;
    }

    public String getNombrePlaneta() {
        return nombrePlaneta;
    }
}
