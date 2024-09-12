package org.model.objectViews;
import java.util.ArrayList;
import java.util.List;

public class MapaEstelarView {
    private static List<SistemaEstelarView> sistemas;
    private int ubicacionDelTesoro;

    public MapaEstelarView(List<SistemaEstelarView> sistemas, int ubicacionDelTesoro) {
        this.sistemas = sistemas;
        this.ubicacionDelTesoro = ubicacionDelTesoro;
    }

    public static List<SistemaEstelarView> getSistemas() {
        return sistemas;
    }

    public int getUbicacionDelTesoro() {
        return ubicacionDelTesoro;
    }
}
