package org.model.objectViews;
import java.util.*;

public class PlanetaNeutralView extends PlanetaView {
    private List<ComponenteView> tiendaComponentes;

    public PlanetaNeutralView(String idPlaneta, String nombrePlaneta, List<ComponenteView> tienda) {
        super(idPlaneta, nombrePlaneta);
        this.tiendaComponentes = tienda;
    }

    public List<ComponenteView> getTiendaComponentes() {
        return tiendaComponentes;
    }
}
