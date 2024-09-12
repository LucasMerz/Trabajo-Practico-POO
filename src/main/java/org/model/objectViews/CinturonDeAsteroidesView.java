package org.model.objectViews;

public class CinturonDeAsteroidesView {
    private int asteroides;
    private int daño;

    public CinturonDeAsteroidesView(int asteroides) {
        this.asteroides = asteroides;
        this.daño= asteroides*10;
    }
    public int getAsteroides() {return asteroides;}
    public int getDaño(){return daño;}
}
