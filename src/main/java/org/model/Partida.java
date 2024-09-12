package org.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.model.exceptions.CombustibleInsuficienteException;
import org.model.exceptions.GameOverException;
import org.model.exceptions.MonedasInsuficientesException;
import org.model.objectViews.*;

public class Partida {
    // Atributos
    private static Partida instancia;
    private String piloto;
    private Nave nave;
    private boolean partidaTerminada;
    private MapaEstelar mapaEstelar;
    private boolean revelarInfoValiosa;

    // Metodos
    private Partida() {
        this.mapaEstelar = new MapaEstelar();
        this.partidaTerminada = false;
        this.revelarInfoValiosa = false;
    }

    // Getters
    public static Partida getInstancia() {
        if (instancia == null) {
            instancia = new Partida();
        }
        return instancia;
    }
    public String getPiloto() {
        return piloto;
    }


    public Nave getNave() {
        return nave;
    }
    public boolean isPartidaTerminada() {
        return partidaTerminada;
    }

    // Setters
    public void setPartidaTerminada(boolean partidaTerminada) {
        this.partidaTerminada = partidaTerminada;
    }
    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    // Logica Juego
    public void seleccionarNave(int nave) {
        if(nave == 1) {
            this.nave = new Aegis(300, 50);
            System.out.println("\nElegiste nave Aegis");
        } else if (nave == 2) {
            this.nave = new Swift(170, 75);
            System.out.println("\nElegiste nave swift");
        } else if (nave == 3) {
            this.nave = new Phantom(220, 50);
            System.out.println("\nElegiste nave Phantom");
        } else if (nave == 4) {
            this.nave = new Titan(250, 50);
            System.out.println("\nElegiste nave Titan");
        } else {
            System.out.println("Error al ingresar valor");
            System.out.println("Porfavor vuelva a seleccionar la nave.");
        }
    }

    public int visitarSistemaEstelar(int idSistema){
        return mapaEstelar.visitarSistemaEstelar(idSistema);
    }

    public int avanzarEnSistema(int idSistema) throws GameOverException, CombustibleInsuficienteException {
        return mapaEstelar.avanzarEnSistema(idSistema, this.nave);
    }

    public void visitarPlanetaHostil(String idPlaneta) throws CombustibleInsuficienteException {
        SistemaEstelar sistema = mapaEstelar.buscarSistemaPorPlaneta(idPlaneta);
        Planeta planeta = sistema.buscarPlaneta(idPlaneta);
        PlanetaHostil planetaH= (PlanetaHostil) planeta;
        nave.visitarPlanetaHostil(planetaH);

    }

    public void visitarPlanetaNeutral(String idPlaneta) throws CombustibleInsuficienteException {
        SistemaEstelar sistema=mapaEstelar.buscarSistemaPorPlaneta(idPlaneta);
        Planeta planeta=sistema.buscarPlaneta(idPlaneta);
        PlanetaNeutral planetaN= (PlanetaNeutral) planeta;
        nave.visitarPlanetaNeutral(planetaN);
    }

    public void visitarPlanetaAliado(String idPlaneta) throws CombustibleInsuficienteException {
        SistemaEstelar sistema=mapaEstelar.buscarSistemaPorPlaneta(idPlaneta);
        Planeta planeta=sistema.buscarPlaneta(idPlaneta);
        PlanetaAliado planetaA= (PlanetaAliado) planeta;
        nave.visitarPlanetaAliado(planetaA);
    }

    public boolean enfrentarEnemigo() throws GameOverException{
        Planeta planeta = nave.getPlanetaActual();
        PlanetaHostil planetaH = (PlanetaHostil) planeta;
        return planetaH.enfrentarEnemigo(nave);
    }

    public void venderComponente(String idComponente){
        Planeta planeta= nave.getPlanetaActual();
        PlanetaNeutral planetaN= (PlanetaNeutral) planeta;
        planetaN.venderComponente(idComponente, nave);
    }

    public void comprarComponente(String idComponente) throws MonedasInsuficientesException {
        Planeta planeta= nave.getPlanetaActual();
        PlanetaNeutral planetaN= (PlanetaNeutral) planeta;
        planetaN.comprarComponente(idComponente, nave);
    }

    public void repararNave(){
        Planeta planeta=nave.getPlanetaActual();
        PlanetaAliado planetaA=(PlanetaAliado) planeta;
        planetaA.repararNave(nave);
    }

    public void comprarInformacionValiosa() throws MonedasInsuficientesException {
            mapaEstelar.comprarInformacionValiosa(nave);
            revelarInfoValiosa = true;
    }
    public boolean getRevelarInfoValiosa(){
        return revelarInfoValiosa;
    }
    public String revelarInfoenMapa(){
        String nombreSistemaConTesoro= mapaEstelar.nombreSistemaConTesoro();
        String mensaje= "El tesoro se encuentra en el: " + nombreSistemaConTesoro;
        return mensaje;
    }

    public void reabastecerCombustible() throws MonedasInsuficientesException {
        Planeta planeta = nave.getPlanetaActual();
        PlanetaNeutral planetaN = (PlanetaNeutral) planeta;
        planetaN.reabastecerCombustible(nave);
    }
    public int calcularCombustible(int cantBase){
        return nave.calcularGastoCombustible(cantBase);
    }

    public List<SistemaEstelarView> sistemasEstelaresToView(){
        MapaEstelarView mapaEstelarView= this.mapaEstelar.toView();
        return mapaEstelarView.getSistemas();
    }

    public String buscarNombreSistema(int idSistema){
        return mapaEstelar.buscarNombreSistema(idSistema);
    }

    public NaveView getNaveView() {
        return this.nave.toView();
    }

    public List<PlanetaView> planetasView(int idSistema){
        return mapaEstelar.planetasView(idSistema);
    }
    public EnemigoView getEnemigoView(){
        PlanetaHostil planetaHostil= (PlanetaHostil) nave.getPlanetaActual();
        return planetaHostil.getEnemigoView();

    }
    public List<ComponenteView> getTiendaComponentesView(){
        PlanetaNeutral pNeutral= (PlanetaNeutral) nave.getPlanetaActual();
        return pNeutral.getTiendaComponentesView();
    }
    public int getUadeCoins(){
        return nave.getUadeCoins();
    }

    public int getCosteRecarga() {
        Planeta planeta = nave.getPlanetaActual();
        PlanetaNeutral planetaN = (PlanetaNeutral) planeta;
        return planetaN.getCosteRecarga();
    }
    public String nombrePlanetaActual(){
        return nave.getPlanetaActual().getNombre();
    }
    public String nombreSistemaActual() throws NullPointerException{
        Planeta planeta= nave.getPlanetaActual();
        if(planeta==null){
            throw new NullPointerException("Planeta no encontrado");
        }
        SistemaEstelar sistemaActual= mapaEstelar.buscarSistemaPorPlaneta(planeta.getIdPlaneta());
        return sistemaActual.getNombre();
    }
    public int idSistemaActual(){
        return mapaEstelar.idSistemaActual(nave.getPlanetaActual().getIdPlaneta());
    }


}
