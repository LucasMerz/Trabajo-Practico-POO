package org.model;
import org.model.exceptions.CombustibleInsuficienteException;
import org.model.exceptions.GameOverException;
import org.model.exceptions.MonedasInsuficientesException;
import org.model.objectViews.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Nave {
    protected int vidaMaxima;
    protected int vida;
    protected int poderDeAtaque;
    protected int velocidad;
    protected int capacidadMaxCombustible;
    protected int cantCombustible;
    protected Escudo escudo;
    protected List<Arma> armas;
    protected List<Componente> inventario;
    protected int uadeCoins;
    protected Planeta planetaActual;

    protected Nave(int vida, int velocidad) {
        this.vidaMaxima = vida;
        this.vida = vida;
        this.velocidad = velocidad;
        this.inventario = new ArrayList<>();
        this.armas = new ArrayList<>();
        this.escudo = null;
        this.poderDeAtaque = calcularPoderDeAtaque();
        this.planetaActual= null;
        this.uadeCoins = 100;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getPoderDeAtaque() {
        return poderDeAtaque;
    }

    public void setPoderDeAtaque(int poderDeAtaque) {
        this.poderDeAtaque = poderDeAtaque;
    }

    public void setCantCombustible(int cantCombustible) {
        this.cantCombustible = cantCombustible;
    }


    public void setEscudo(Escudo escudo) {
        this.escudo = escudo;
    }

    public int getUadeCoins() {
        return uadeCoins;
    }

    public Planeta getPlanetaActual() {
        return planetaActual;
    }

    public void setPlanetaActual(Planeta planetaActual) {
        this.planetaActual = planetaActual;
    }

    public void recibirUadeCoins(int monedas) {
        this.uadeCoins = uadeCoins + monedas;
    }
    public void restarUadeCoins(int precio) {
        this.uadeCoins -= precio;
    }

    public int atravesarCinturon(int poderDeAsteroides) throws GameOverException {
        int vidaInicial = vida;
        recibirDaño(poderDeAsteroides);
        int vidaFinal= vida;
        if(vidaFinal<=0){
            throw new GameOverException("No sobreviviste al cinturon");
        }else{
            int monedaesRecibidas= (poderDeAsteroides-(vidaFinal-vidaInicial));
            recibirUadeCoins(monedaesRecibidas);
            return monedaesRecibidas;
        }
    }

    public void visitarPlanetaHostil(PlanetaHostil planetaH) throws CombustibleInsuficienteException {
        gastarCombustible(20);
        setPlanetaActual(planetaH);
    }

    public void visitarPlanetaNeutral(PlanetaNeutral planetaN) throws CombustibleInsuficienteException {
        gastarCombustible(10);
        setPlanetaActual(planetaN);
    }

    public void visitarPlanetaAliado(PlanetaAliado planetaA) throws CombustibleInsuficienteException {
        gastarCombustible(10);
        setPlanetaActual(planetaA);
    }

    public void gastarCombustible(int cantidad) throws CombustibleInsuficienteException {
        int cantidadFinal= calcularGastoCombustible(cantidad);
        if(cantCombustible > cantidadFinal){
            this.cantCombustible-=cantidadFinal;
        }else{
            throw new CombustibleInsuficienteException("No tienes suficiente combustible para el viaje");
        }
    }
    public int calcularGastoCombustible(int cantidadBase){
        int cantidadFinal = cantidadBase;
        for(Arma arma: armas ){
            cantidadFinal+= cantidadBase/2;
        }
        return cantidadFinal;
    }

    public void llenarTanque(int costeRecarga) throws MonedasInsuficientesException {
        if (uadeCoins < costeRecarga) {
            throw new MonedasInsuficientesException("No tienes monedas suficientes para la recarga");
        } else {
            this.setCantCombustible(capacidadMaxCombustible);
            this.uadeCoins -= costeRecarga;
        }
    }


    public void venderComponente(String idComponente){
        Componente componente=buscarComponente(idComponente);
        eliminarComponente(componente);
        recibirUadeCoins(componente.getPrecio());
    }

    private Componente buscarComponente(String idComponente){
        for(int i=0;i<inventario.size();i++){
            if(inventario.get(i).getIdComponente().equals(idComponente)){
                return inventario.get(i);
            }
        }
        for(int i=0;i<armas.size();i++){
            if(armas.get(i).getIdComponente().equals(idComponente)){
                return armas.get(i);
            }
        }
        if (escudo.getIdComponente().equals(idComponente)) {
            return escudo;
        }
        return null;
    }
    private void eliminarComponente(Componente componente) {
        if (!inventario.isEmpty()) {
            for (int i = 0; i < inventario.size(); i++) {
                if (inventario.get(i).getIdComponente().equals(componente.getIdComponente())) {
                    inventario.remove(i);
                }
            }
        } else {
            componente.eliminarComponente(this);
        }

    }

    public void repararNave(){
        setVida(vidaMaxima);
        escudo.repararEscudo();
    }

    public void comprarInformacionValiosa() throws MonedasInsuficientesException {
        if(uadeCoins>=500){
            restarUadeCoins(500);
        }else{
            throw new MonedasInsuficientesException("No tienes suficiente para comprar esta informacion");
        }
    }


    public void desequiparArmaMasDebil() {
        Arma armaMasDebil= armas.get(0);
        for(int i=1; i<armas.size(); i++){
            if (armas.get(i).getPoderDeAtaque() < armaMasDebil.getPoderDeAtaque()){
                armaMasDebil= armas.get(i);
            }
        }
        inventario.add(armaMasDebil);
        armas.remove(armaMasDebil);
    }


    public void guardarEnInventario(Componente componente) {
        inventario.add(componente);
    }

    public void recibirDaño(int daño){
        if(vidaEscudo() > 0){
            if(vidaEscudo() < daño){
                int dañoRestante= daño-vidaEscudo();
                escudo.setDefensa(0);
                recibirDaño(dañoRestante);
            }else{
                this.escudo.setDefensa(escudo.getDefensa()-daño);
            }
        } else {
            this.vida = vida - daño;
            setPoderDeAtaque(calcularPoderDeAtaque());
        }
    }

    public int calcularVidaPerdida(int vidaInicial) {
        return vidaInicial- this.getVida();
    }

    public void equiparArma(Arma arma){
        if(armas.size()<2){
            this.armas.add(arma);
        }else {
            this.desequiparArmaMasDebil();
            this.armas.add(arma);
            this.inventario.remove(arma);
        }
        this.poderDeAtaque=calcularPoderDeAtaque();
    }
    public void equiparEscudo(Escudo escudoN){
        if(this.escudo==null){
            this.setEscudo(escudoN);
        }else {
            this.guardarEnInventario(this.escudo);
            this.setEscudo(escudoN);
            this.inventario.remove(escudoN);
        }
    }
    public void desequiparArma(Arma arma){
        this.armas.remove(arma);
        this.inventario.add(arma);
    }
    public void desequiparEscudo(Escudo escudoN){
        this.escudo= null;
        this.inventario.add(escudoN);
    }
    
    public abstract int calcularPoderDeAtaque();

    public int vidaEscudo() {
        if (escudo == null)
                return 0;
        else
            return escudo.getDefensa();
    }
    public NaveView toView(){
        List<ArmaView> armasView= new ArrayList<>();
        for(Arma arma: armas){
            ArmaView armaView= arma.toView();
            armasView.add(armaView);
        }
        List<ComponenteView> inventarioView= new ArrayList<>();
        for(Componente componente: inventario){
            ComponenteView componenteView= componente.toView();
            inventarioView.add(componenteView);
        }
        if(escudo==null){
            return new NaveView(vidaMaxima, vida, poderDeAtaque, velocidad, capacidadMaxCombustible, cantCombustible, null, armasView, inventarioView, uadeCoins, null);
        }else {
            return new NaveView(vidaMaxima, vida, poderDeAtaque, velocidad, capacidadMaxCombustible, cantCombustible, escudo.toView(), armasView, inventarioView, uadeCoins, null);
        }
    }

}
