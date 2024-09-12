package org.model;
import org.model.objectViews.PlanetaView;

public abstract class Planeta {
    protected static int contadorID = 0;
    protected String nombre;
    protected String idPlaneta;

    public Planeta(String nombre) {
        this.nombre= nombre;
        this.idPlaneta= generarIdUnico();

    }

    public String getNombre() { return nombre;}

    public void setNombre(String nombre) { this.nombre = nombre;}

    public void setIdPlaneta(String idPlaneta) {this.idPlaneta = idPlaneta;}

    protected abstract String generarIdUnico();

    public String getIdPlaneta() {
        return idPlaneta;
    }

    public abstract PlanetaView toView();

}
