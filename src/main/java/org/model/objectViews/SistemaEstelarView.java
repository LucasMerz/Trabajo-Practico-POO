package org.model.objectViews;
import java.util.List;


public class SistemaEstelarView {
    private int idSistema;
    private String nombre;
    private CinturonDeAsteroidesView cinturonDeAsteroides;
    private List<PlanetaView> planetas;

    public SistemaEstelarView(int idSistema, String nombre, CinturonDeAsteroidesView cinturonDeAsteroides, List<PlanetaView> planetas) {
        this.idSistema = idSistema;
        this.nombre = nombre;
        this.cinturonDeAsteroides = cinturonDeAsteroides;
        this.planetas = planetas;
    }

    public int getIdSistema() {
        return idSistema;
    }

    public String getNombre() {
        return nombre;
    }

    public CinturonDeAsteroidesView getCinturonDeAsteroides() {
        return cinturonDeAsteroides;
    }

    public List<PlanetaView> getPlanetas() {
        return planetas;
    }
}
