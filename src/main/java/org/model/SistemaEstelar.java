package org.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.model.exceptions.GameOverException;
import org.model.objectViews.SistemaEstelarView;
import org.model.objectViews.PlanetaView;

public class SistemaEstelar {
    private static int contadorID = 1;
    private int idSistema;
    private String nombre;
    private CinturonDeAsteroides cinturonDeAsteroides;
    private List<Planeta> planetasDelSistema;

    public SistemaEstelar(String nombre, boolean ubicarTesoro) {
        this.nombre = nombre;
        this.idSistema = contadorID++;
        this.planetasDelSistema = crearPlanetas(ubicarTesoro);
        this.cinturonDeAsteroides= new CinturonDeAsteroides();
    }

    public int getIdSistema() {
        return idSistema;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Planeta> getPlanetasDelSistema() {
        return planetasDelSistema;
    }

    public int atravesarCinturonDeAsteroides(Nave nave) throws GameOverException {
        int poderDeAsteroides = cinturonDeAsteroides.calcularDañoAsteroides();
        return nave.atravesarCinturon(poderDeAsteroides);
    }
    public int calcularDañoAsteroides(){
        return cinturonDeAsteroides.calcularDañoAsteroides();
    }
    public Planeta buscarPlaneta(String idPlaneta) {
        for (Planeta planeta : planetasDelSistema) {
            if (planeta.getIdPlaneta().equals(idPlaneta)) {
                return planeta;
            }
        }
        return null;
    }
    public List<Planeta> crearPlanetas(boolean ubicarTesoro){
        List<Planeta> planetas= new ArrayList<>();
        String[] planetasHostiles = {
                "Kragmor", "Zenthrax", "Volkaris", "Drakonis", "Scornia", "Venomor",
                "Ignorath", "Virox", "Korrath", "Blightar", "Tyrantis", "Brutalia",
                "Nefarion", "Ravagar", "Malvoria", "Darkon", "Grimnax", "Ferocia",
                "Thundara", "Magmaris", "Phoboros", "Wrathor", "Dreadium", "Slargh",
                "Karnath","Zarvok", "Noxar", "Blitzor", "Mordax", "Vandor"
        };

        String[] planetasNeutrales = {
                "Serenis", "Luminara", "Valtaris", "Eryndor", "Solara", "Verdantia",
                "Aquilon", "Celestia", "Auroria", "Borealis"
        };

        String[] planetasAliados = {
                "Aetheris", "Noveria", "Eridania", "Thaloria", "Veloria",
                "Solstice", "Equinox", "Galadorn", "Radiantis", "Starhaven"
        };
        Random random= new Random();
        int planetaConTesoro = random.nextInt(1,3);
        int entender = (((idSistema) - 1) * 3) + 3;
        for(int i = (idSistema - 1) * 3; i < entender; i++){
            PlanetaHostil planetaHostil;
            if(ubicarTesoro && planetaConTesoro==i){
                planetaHostil = new PlanetaHostil(planetasHostiles[i], true);
            }else{
                planetaHostil = new PlanetaHostil(planetasHostiles[i], false);
            }
            planetas.add(planetaHostil);

        }
        PlanetaNeutral pNeutral = new PlanetaNeutral(planetasNeutrales[idSistema-1]);
        planetas.add(pNeutral);

        PlanetaAliado pAliado = new PlanetaAliado(planetasAliados[idSistema-1]);
        planetas.add(pAliado);

        return planetas;
    }
    public void visualizarPlanetas() {
        int i = 1;
        for (Planeta planeta : planetasDelSistema) {
            System.out.println(i + ". Id: " + planeta.getIdPlaneta() + " Nombre planeta: " + planeta.getNombre());
            i++;
        }
        System.out.println("6. salir de sistema.");
    }

    public SistemaEstelarView toView(){
        List<PlanetaView> planetasView= new ArrayList<>();
        for (Planeta planeta : planetasDelSistema) {
            planetasView.add(planeta.toView());
        }
        return new SistemaEstelarView(this.idSistema, this.nombre, cinturonDeAsteroides.toView() ,planetasView);
    }

}
