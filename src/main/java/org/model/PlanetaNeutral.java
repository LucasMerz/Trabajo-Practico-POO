package org.model;
import org.model.exceptions.MonedasInsuficientesException;
import org.model.objectViews.ComponenteView;
import org.model.objectViews.PlanetaView;
import org.model.objectViews.PlanetaNeutralView;

import java.util.*;

public class PlanetaNeutral extends Planeta {
    private List<Componente> tiendaComponentes;
    private int costeRecarga;

    public PlanetaNeutral(String nombre) {
        super(nombre);
        this.tiendaComponentes = generarTienda();
        this.costeRecarga = 50;
    }

    public void setTiendaComponentes(List<Componente> tiendaComponentes) {
        this.tiendaComponentes = tiendaComponentes;
    }

    public List<Componente> generarTienda() {
        List<Componente> tiendaComponentes = new ArrayList<>();
        List<String> nombresArmas = new ArrayList<>(Arrays.asList(
                "Espada del Alba", "Hacha del Trueno", "Daga del Crepúsculo",
                "Arco del Centauro", "Lanza del Dragón", "Martillo de la Tormenta",
                "Maza del Coloso", "Sable de la Sombra", "Ballesta del Viento",
                "Alabarda del Titán", "Espada del Fénix", "Hacha de la Ira",
                "Daga del Asesino", "Arco del Grifo", "Lanza del Viento",
                "Martillo del Caos", "Maza del Gladiador", "Sable del Guerrero",
                "Ballesta del Fuego", "Alabarda del Eclipse", "Espada de la Luz",
                "Hacha del Infierno", "Daga del Destino", "Arco del Guardián",
                "Lanza del León", "Martillo del Juez", "Maza de la Justicia",
                "Sable del Cazador", "Ballesta del Tigre", "Alabarda del Centinela",
                "Espada del Rey", "Hacha del Bárbaro", "Daga del Sigilo",
                "Arco del Halcón", "Lanza del Guerrero", "Martillo del Mago",
                "Maza del Hechicero", "Sable del Samurái", "Ballesta del Explorador",
                "Alabarda del Centinela", "Espada de la Aurora", "Hacha del Caos",
                "Daga de la Noche", "Arco del Lobo", "Lanza del Escorpión",
                "Martillo del Gigante", "Maza del Titán", "Sable del Espadachín",
                "Ballesta del Águila", "Alabarda del Conquistador"
        ));


        List<String> nombresEscudos = new ArrayList<>(Arrays.asList(
                "Escudo de la Aurora", "Escudo del Crepúsculo", "Escudo del Dragón",
                "Escudo del Fénix", "Escudo del Trueno", "Escudo del Viento",
                "Escudo de la Tormenta", "Escudo del Ocaso", "Escudo del Alba",
                "Escudo del Cíclope", "Escudo del Grifo", "Escudo de la Serpiente",
                "Escudo del Titán", "Escudo de la Esmeralda", "Escudo de la Rosa",
                "Escudo del León", "Escudo de la Luna", "Escudo del Sol",
                "Escudo del Eclipse", "Escudo del Centauro", "Escudo del Lobo",
                "Escudo del Gladiador", "Escudo de la Justicia", "Escudo de la Valquiria",
                "Escudo del Ángel", "Escudo del Escorpión", "Escudo del Coloso",
                "Escudo de la Espada", "Escudo de la Flecha", "Escudo del Manto",
                "Escudo del Cazador", "Escudo de la Fortaleza", "Escudo de la Resistencia",
                "Escudo de la Espiral", "Escudo del Tridente", "Escudo del Relámpago",
                "Escudo del Guerrero", "Escudo de la Vanguardia", "Escudo de la Protección",
                "Escudo de la Espada y el Escudo", "Escudo de la Victoria",
                "Escudo de la Corona", "Escudo de la Defensa", "Escudo del Guardián",
                "Escudo del Nigromante", "Escudo del Alma", "Escudo del Destino",
                "Escudo del Fuego", "Escudo del Hielo", "Escudo de la Libertad"
        ));;
        for(int i = 0; i < 3; i++) {
            Random random = new Random();
            int randomNombre = random.nextInt(nombresArmas.size());
            Arma arma = new Arma(nombresArmas.get(randomNombre));
            nombresArmas.remove(randomNombre);
            tiendaComponentes.add(arma);
            Escudo escudo = new Escudo(nombresEscudos.get(randomNombre));
            nombresEscudos.remove(randomNombre);
            tiendaComponentes.add(escudo);
        }
        return tiendaComponentes;
    }

    public void comprarComponente(String idComponente, Nave nave) throws MonedasInsuficientesException {
        Componente componenteEncontrado = buscarComponente(idComponente);
        int precio= componenteEncontrado.getPrecio();
        boolean suficiente = sonSuficientesCoins(nave.getUadeCoins(),precio);
        if(suficiente){
            nave.restarUadeCoins(precio);
            componenteEncontrado.equiparComponente(nave);
            eliminarComponente(componenteEncontrado);
        }else{
            throw new MonedasInsuficientesException("Monedas insuficientes");
        }
    }

    public void venderComponente(String idComponente, Nave nave) {
        nave.venderComponente(idComponente);
    }
    private boolean sonSuficientesCoins(int cantMonedas, int precio) {
        return cantMonedas >= precio;
    }

    public void mostrarTienda(){
        System.out.println("Componentes disponibles en la tienda: ");
        int contador = 1;
        for (Componente componente : tiendaComponentes) {
            if (componente == null) {
                System.out.println(contador + ". AGOTADO");
            }
            else if (componente instanceof Arma) {
                Arma arma = (Arma) componente;
                System.out.println(contador + ". Arma: " + arma.getNombre() + ", Poder: " + arma.getPoderDeAtaque() +", ID: "+ arma.getIdComponente() +" Precio: " + arma.getPrecio() + "UC)");
            } else if (componente instanceof Escudo) {
                Escudo escudo = (Escudo) componente;
                System.out.println(contador + ". Escudo: " + escudo.getNombre() + ", Defensa: " + escudo.getDefensa() +", ID: "+ escudo.getIdComponente() +" Precio: " + escudo.getPrecio() + "UC)");
            } else {
                System.out.println(contador + ". Componente: " + componente.getNombre() + " Precio: " + componente.getPrecio() + "UC)");
            }
            contador++;
        }
        System.out.println(contador + ". Salir.");
    }


    private void eliminarComponente(Componente componente) {
        int index=0;
        for(int i=0; i<tiendaComponentes.size(); i++) {
            if(tiendaComponentes.get(i)!=null) {
                if (tiendaComponentes.get(i).idComponente.equals(componente.getIdComponente())) {
                    index = i;
                }
            }
        }
        tiendaComponentes.set(index, null);
    }

    public List<Componente> getTiendaComponentes() {
        return tiendaComponentes;
    }
    private Componente buscarComponente(String idComponente) {
        try {
            for (Componente componente: this.tiendaComponentes) {
                if(componente!=null) {
                    if (componente.getIdComponente().equals(idComponente)) {
                        return componente;
                    }
                }
            }
        } catch (NullPointerException e) {
            System.err.println("No se encontró el componente" + e.getMessage());
        }
        return null;
    }

    protected String generarIdUnico(){
        return "PN" + (++contadorID);
    }

    @Override
    public PlanetaView toView() {
        List<ComponenteView> tiendaView= new ArrayList<>(){};
        for (Componente componente : tiendaComponentes) {
            if (componente != null) {
                ComponenteView componenteView = componente.toView();
                tiendaView.add(componenteView);
            }
        }
        return new PlanetaNeutralView(this.idPlaneta, this.nombre, tiendaView);
    }
    public List<ComponenteView> getTiendaComponentesView(){
        List<ComponenteView> tiendaView= new ArrayList<>(){};
        for (Componente componente : tiendaComponentes) {
            if (componente != null) {
                ComponenteView componenteView = componente.toView();
                tiendaView.add(componenteView);
            }
        }
        return tiendaView;
    }

    public void reabastecerCombustible(Nave nave) throws MonedasInsuficientesException {
        nave.llenarTanque(costeRecarga);
    }

    public int getCosteRecarga() {
        return costeRecarga;
    }

}
