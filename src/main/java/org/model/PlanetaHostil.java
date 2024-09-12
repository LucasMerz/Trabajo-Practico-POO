package org.model;

import org.model.exceptions.GameOverException;
import org.model.objectViews.EnemigoView;
import org.model.objectViews.PlanetaView;
import org.model.objectViews.PlanetaHostilView;

public class PlanetaHostil extends Planeta {
    private boolean tesoro;
    private Enemigo enemigo;

    public PlanetaHostil(String nombre, boolean tesoro) {
        super(nombre);
        this.enemigo = new Enemigo();
        this.tesoro = tesoro;
    }

    public boolean getTesoro() {
        return tesoro;
    }

    public boolean enfrentarEnemigo(Nave nave) throws GameOverException {
        int vidaInicial = nave.getVida();
        while(nave.getVida() > 0 && enemigo.getVida() > 0) {
            enemigo.recibirDaño(nave.getPoderDeAtaque());
            if (enemigo.getVida() <= 0) {
                int vidaPerdida = nave.calcularVidaPerdida(vidaInicial);
                nave.recibirUadeCoins(enemigo.getPoderDeAtaque()*2-vidaPerdida);
                return tesoro;
            } else {
                nave.recibirDaño(enemigo.getPoderDeAtaque());
                if (nave.getVida() <= 0) {
                    throw new GameOverException("Game over");
                }
            }
        }
        return tesoro;
    }



    protected String generarIdUnico(){
        return "PH" + (++contadorID);
    }

    @Override
    public PlanetaView toView() {
        return new PlanetaHostilView(this.idPlaneta, this.nombre, this.enemigo.toView(), this.tesoro);
    }
    public EnemigoView getEnemigoView(){
        return this.enemigo.toView();
    }
}