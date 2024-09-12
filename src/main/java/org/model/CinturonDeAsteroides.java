package org.model;
import org.model.objectViews.CinturonDeAsteroidesView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CinturonDeAsteroides {
    private int asteroides;

    public CinturonDeAsteroides() {
        Random random = new Random();
        this.asteroides = random.nextInt(0, 10);
    }

    public int calcularDañoAsteroides(){
        return this.asteroides * 10;
    }

    public CinturonDeAsteroidesView toView(){
        return new CinturonDeAsteroidesView(this.asteroides);
    }
}
