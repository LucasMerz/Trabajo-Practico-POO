package org.model;

import org.model.objectViews.ComponenteView;

public abstract class Componente {
    protected static int contadorID = 0;
    protected String idComponente;
    protected String nombre;
    protected int precio;

    public Componente(String nombre) {
        this.idComponente = generarIdUnico();
        this.nombre = nombre;
    }

    public static int getContadorID() {
        return contadorID;
    }

    public String getIdComponente() {
        return idComponente;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }
    public abstract void equiparComponente(Nave nave);
    public abstract void desequiparComponente(Nave nave);
    public abstract void eliminarComponente(Nave nave);

    protected abstract String generarIdUnico();

    public abstract ComponenteView toView();
}
