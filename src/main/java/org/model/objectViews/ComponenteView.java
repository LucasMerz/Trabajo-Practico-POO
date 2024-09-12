package org.model.objectViews;

public class ComponenteView {
    protected String idComponente;
    protected String nombreComponente;
    protected int precio;
    protected String tipo;

    public ComponenteView(String idComponente, String nombreComponente, int precio, String tipo) {
        this.idComponente = idComponente;
        this.nombreComponente = nombreComponente;
        this.precio = precio;
        this.tipo= tipo;
    }

    public String getIdComponente() {
        return idComponente;
    }

    public String getNombreComponente() {
        return nombreComponente;
    }

    public int getPrecio() {
        return precio;
    }

    public String getTipo() {
        return tipo;
    }
}
