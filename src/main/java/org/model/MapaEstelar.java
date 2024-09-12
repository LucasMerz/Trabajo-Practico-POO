package org.model;
import org.model.exceptions.CombustibleInsuficienteException;
import org.model.exceptions.GameOverException;
import org.model.exceptions.MonedasInsuficientesException;
import org.model.objectViews.MapaEstelarView;
import org.model.objectViews.PlanetaView;
import org.model.objectViews.SistemaEstelarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapaEstelar {
    private List<SistemaEstelar> sistemas;
    private int ubicacionDelTesoro;

    public MapaEstelar() {
        this.sistemas=crearSistemas();
    }

    public List<SistemaEstelar> crearSistemas(){
        List<SistemaEstelar> sistemas= new ArrayList<>();
        String[] nombres= {
                "Sistema de Arcturus", "Sistema de Procyon", "Sistema de Vega",
                "Sistema de Rigel", "Sistema de Betelgeuse", "Sistema de Sirius",
                "Sistema de Antares", "Sistema de Aldebarán", "Sistema de Capella",
                "Sistema de Hollow"
        };
        Random random= new Random();
        ubicacionDelTesoro=random.nextInt(nombres.length-1)+1;
        for (int i = 0; i < nombres.length; i++) {
            boolean ubicarTesoro = ubicacionDelTesoro - 1 == i;
            SistemaEstelar sEstelar = new SistemaEstelar(nombres[i], ubicarTesoro);
            sistemas.add(sEstelar);
        }
        return sistemas;
    }
    public int visitarSistemaEstelar(int idSistema) {
        SistemaEstelar sistema = buscarSistemaEstelar(idSistema);
        int poderDeAsteroides = sistema.calcularDañoAsteroides();
        return poderDeAsteroides;
    }

    public int avanzarEnSistema(int idSistema,Nave nave) throws CombustibleInsuficienteException, GameOverException {
        SistemaEstelar sistema = buscarSistemaEstelar(idSistema);
        nave.gastarCombustible(30);
        return sistema.atravesarCinturonDeAsteroides(nave);
    }

    public SistemaEstelar buscarSistemaEstelar(int idSistema){
        for (SistemaEstelar sistema : sistemas) {
            if (sistema.getIdSistema() == idSistema) {
                return sistema;
            }
        }
        return null;
    }

    public void comprarInformacionValiosa(Nave nave) throws MonedasInsuficientesException {
        Planeta planeta = nave.getPlanetaActual();
        PlanetaAliado planetaA= (PlanetaAliado) planeta;
        planetaA.comprarInformacionValiosa(nave);
    }
    public SistemaEstelar buscarSistemaPorPlaneta(String idPlaneta){
        try {
            for(SistemaEstelar sistema: sistemas){
                if(sistema.buscarPlaneta(idPlaneta) != null){
                    return sistema;
                }
            }
        } catch (NullPointerException e) {
            System.err.println("No se encuentra el paneta que estas buscando. " + e.getMessage());
        }
        return null;
    }

    public MapaEstelarView toView(){
        List<SistemaEstelarView> sistemasViews=new ArrayList<>();
        for(SistemaEstelar sistema: this.sistemas){
            sistemasViews.add(sistema.toView());
        }
        return new MapaEstelarView(sistemasViews, ubicacionDelTesoro);
    }
    public String buscarNombreSistema(int idSistema){
        SistemaEstelar sistema= buscarSistemaEstelar(idSistema);
        return sistema.getNombre();
    }
    public String nombreSistemaConTesoro(){
        return buscarNombreSistema(ubicacionDelTesoro);
    }
    public List<PlanetaView> planetasView(int idSistema){
        SistemaEstelar sistema = buscarSistemaEstelar(idSistema);
        SistemaEstelarView sistemaView = sistema.toView();
        return sistemaView.getPlanetas();
    }
    public int idSistemaActual(String idPlaneta){
        SistemaEstelar sistema= buscarSistemaPorPlaneta(idPlaneta);
        return sistema.getIdSistema();
    }


}
