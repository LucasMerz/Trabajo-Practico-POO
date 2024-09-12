package org.controller;
import org.model.Partida;
import java.awt.Image;

import org.model.exceptions.CombustibleInsuficienteException;
import org.model.exceptions.GameOverException;
import org.model.exceptions.MonedasInsuficientesException;
import org.model.objectViews.*;
import org.view.*;
import org.view.MapaEstelarView;

import javax.swing.*;
import java.io.File;
import java.util.*;
public class GameControllerV2 {
    private Partida partida;

    public GameControllerV2() {
        this.partida = Partida.getInstancia();
    }

    public void iniciar() {
        SwingUtilities.invokeLater(() -> {
            WelcomeView welcomeView = new WelcomeView(this);
            welcomeView.setVisible(true);
        });
    }

    public void iniciarNombrePiloto() {
        SwingUtilities.invokeLater(() -> {
            NamePilotView pantallaNombrePiloto = new NamePilotView(this);
            pantallaNombrePiloto.setVisible(true);
        });
    }

    public void mostrarPantallaSeleccionNave(String nombrePiloto) {
        SwingUtilities.invokeLater(() -> {
            partida.setPiloto(nombrePiloto);
            SelectShip pantallaSeleccionNave = new SelectShip(this);
            pantallaSeleccionNave.setVisible(true);
        });
    }

    public void mostrarPantallaSeleccionSistema(){
        SwingUtilities.invokeLater(() -> {
            SeleccionSistemaView pantallaSeleccionSistema = new SeleccionSistemaView(this);
            pantallaSeleccionSistema.setVisible(true);
        });
    }

    public void mostrarPantallaAvanzarSistema(int idSistema){
        SwingUtilities.invokeLater(() -> {
            AvanzarSistemaView pantallaAvanzarSistema = new AvanzarSistemaView(this, idSistema);
            pantallaAvanzarSistema.setVisible(true);
        });
    }

    public void mostrarPantallaInventario() {
        InventarioView inventarioView = new InventarioView(this);
        inventarioView.setVisible(true);
    }

    public void mostrarPantallaMapaEstelar(){
        MapaEstelarView mapaEstelar= new MapaEstelarView(this);
        mapaEstelar.setVisible(true);
    }

    public void mostrarPantallaSeleccionPlaneta(int idSistema) {
        SwingUtilities.invokeLater(() -> {
            ElegirPlanetaView pantallaSeleccionPlaneta = new ElegirPlanetaView(this, idSistema);
            pantallaSeleccionPlaneta.setVisible(true);
        });
    }

    public void seleccionarNave(int eleccionNave) {
        partida.seleccionarNave(eleccionNave);
        mostrarPantallaSeleccionSistema();
    }

    public List<SistemaEstelarView> sistemasEstelares(){
        return partida.sistemasEstelaresToView();
    }

    public NaveView getNaveView(){
        return partida.getNaveView();
    }

    public EnemigoView getEnemigoView(){
        return partida.getEnemigoView();
    }

    public int seleccionarSistema(int idSistema){
        return partida.visitarSistemaEstelar(idSistema);
    }
    public String buscarNombreSistema(int idSistema){
        return partida.buscarNombreSistema(idSistema);
    }
    public int avanzarEnSistema(int idSistema) {
        try {
            return partida.avanzarEnSistema(idSistema);
        }catch(GameOverException e){
            mostrarPantallaGameOver();
        }catch(CombustibleInsuficienteException e){
            mostrarPantallaCombustibleInsuficiente();
        }
        return 0;
    }
    public void mostrarPantallaAtravesarCinturon(int idSistema,int monedasRecibidas){
        SwingUtilities.invokeLater(() -> {
           AtravesarCinturonView cinturonView= new AtravesarCinturonView(this, idSistema,monedasRecibidas);
           cinturonView.setVisible(true);
        });
    }
    public void mostrarPantallaCombustibleInsuficiente() {
        SwingUtilities.invokeLater(() -> {
           CombustibleInsuficienteView pantallaCombustibleInsuficiente= new CombustibleInsuficienteView();
           pantallaCombustibleInsuficiente.setVisible(true);
        });
    }
    public List<PlanetaView> planetasView(int idSistema){
        return partida.planetasView(idSistema);
    }

    public void cerrarInventario(JFrame inventario) {
        inventario.dispose();
    }
    public void cerrarMapaEstelar(JFrame mapaEstelar) {
        mapaEstelar.dispose();
    }
    public void visitarPlanetaHostil(String idPlaneta, int idSistema, JFrame pantallaAnterior) {
        try {
            partida.visitarPlanetaHostil(idPlaneta);
            mostrarPantallaVisualizarEnemigo(idSistema);
            pantallaAnterior.dispose();
        }catch (CombustibleInsuficienteException e){
            mostrarPantallaCombustibleInsuficiente();
        }
    }

    public void mostrarPantallaVisualizarEnemigo(int idSistema) {
        SwingUtilities.invokeLater(() -> {
            VisualizarEnemigoView pantallaVisualizarEnemigo = new VisualizarEnemigoView(this, idSistema);
            pantallaVisualizarEnemigo.setVisible(true);
        });
    }
    public void visitarPlanetaNeutral(String idPlaneta, JFrame pantallaAnterior) {
        try {
            partida.visitarPlanetaNeutral(idPlaneta);
            mostrarPantallaComerciante();
            pantallaAnterior.dispose();
        }catch (CombustibleInsuficienteException e){
            mostrarPantallaGameOver();
        }
    }
    public void mostrarPantallaComerciante(){
        SwingUtilities.invokeLater(() -> {
            ComercianteView pantallaComerciante = new ComercianteView(this);
            pantallaComerciante.setVisible(true);
        });
    }
    public void visitarPlanetaAliado(String idPlaneta, JFrame pantallaAnterior) {
        try {
            partida.visitarPlanetaAliado(idPlaneta);
            mostrarPantallaTiendaAliada();
            pantallaAnterior.dispose();
        }catch (CombustibleInsuficienteException e){
            mostrarPantallaCombustibleInsuficiente();
        }
    }
    public void mostrarPantallaTiendaAliada(){
        SwingUtilities.invokeLater(() -> {
            TiendaAliadaView tiendaAliada = new TiendaAliadaView(this);
            tiendaAliada.setVisible(true);
        });
    }

    public ImageIcon cargarImagen(String nombreArchivo) {
        String[] extensiones = { ".jpeg", ".jpg" };
        for (String extension : extensiones) {
            String ruta = "src/main/java/org/model/imagenes/" + nombreArchivo + extension;
            File archivo = new File(ruta);
            if (archivo.exists()) {
                ImageIcon originalIcon = new ImageIcon(ruta);
                Image imagenEscalada = originalIcon.getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
                return new ImageIcon(imagenEscalada);
            }
        }
        return null;
    }
    public ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
    public void mostrarPantallaEnfrentarEnemigoWin() {
        SwingUtilities.invokeLater(() -> {
            EnfrentarEnemigoWinView pantallaEnfrentarEnemigoWin = new EnfrentarEnemigoWinView(this);
            pantallaEnfrentarEnemigoWin.setVisible(true);
        });
    }

    public void mostrarPantallaNoHayTesoro(int idSistema) {
        SwingUtilities.invokeLater(() -> {
            NoHayTesoroView pantallaNoHayTesoro = new NoHayTesoroView(this, idSistema);
            pantallaNoHayTesoro.setVisible(true);
        });
    }

    public void mostrarPantallaGameOver() {
        SwingUtilities.invokeLater(() -> {
            GameOverView pantallaGameOver = new GameOverView(this);
            pantallaGameOver.setVisible(true);
        });
    }

    public void enfrentarEnemigo(int idSistema) {
        try {
            partida.enfrentarEnemigo();
            if (partida.enfrentarEnemigo()) {
                mostrarPantallaEnfrentarEnemigoWin();
            } else {
                mostrarPantallaNoHayTesoro(idSistema);
            }
        } catch (GameOverException e) {
            mostrarPantallaGameOver();
        }
    }
    public List<ComponenteView> getTiendaComponentesView(){
        return partida.getTiendaComponentesView();
    }

    public int getUadeCoins(){
        return partida.getUadeCoins();
    }

    public void comprarComponente(String idComponente){
        try {
            partida.comprarComponente(idComponente);
        }catch (MonedasInsuficientesException e){
            mostrarPantallaMonedasInsuficientes();
        }
    }
    public void mostrarPantallaMonedasInsuficientes() {
        SwingUtilities.invokeLater(() -> {
            MonedasInsuficientesView view = new MonedasInsuficientesView();
            view.setVisible(true);
        });
    }


    public void mostrarPantallaTienda() {
        SwingUtilities.invokeLater(() -> {
            TiendaView pantallaTienda = new TiendaView(this);
            pantallaTienda.setVisible(true);
        });
    }
    public String ubicacionActual(){
        try {
            String nombrePlaneta = partida.nombrePlanetaActual();
            String nombreSistema= partida.nombreSistemaActual();
            String mensaje= "Estás en el planeta " + nombrePlaneta+ ", ubicado en el: " + nombreSistema;
            return mensaje;
        }catch (NullPointerException e){
            return "";
        }
    }

//////////////////////////////////////////////////////////////////////////////////

    public void comprarInformacionValiosa() {
        try {
            partida.comprarInformacionValiosa();
            mostrarInfoValiosaView();
        }catch (MonedasInsuficientesException e){
            mostrarPantallaMonedasInsuficientes();
        }
    }

    public void mostrarInfoValiosaView() {
        // Implementar lógica para mostrar la vista InfoValiosaView
        InfoValiosaView infoValiosaView = new InfoValiosaView(this);
        infoValiosaView.setVisible(true);
    }
    public String revelarInfoValiosa(){
        if(partida.getRevelarInfoValiosa()){
            return partida.revelarInfoenMapa();
        }else{
            return "";
        }
    }
    public int calcularCombustible(int cantidadBase){
        return partida.calcularCombustible(cantidadBase);
    }

    public void reabastecerCombustible() {
        try {
            partida.reabastecerCombustible();
            mostrarLlenarTanqueView();
        }catch(MonedasInsuficientesException e){
            mostrarPantallaMonedasInsuficientes();
        }
    }


    public void repararNave() {
        partida.repararNave();
        mostrarNaveReparada();
    }



    public void mostrarNaveReparadaView() {
        NaveReparadaView naveReparadaView = new NaveReparadaView(this);
        naveReparadaView.setVisible(true);
    }

    public void mostrarTiendaAliadaView() {
        TiendaAliadaView tiendaAliadaView = new TiendaAliadaView(this);
        tiendaAliadaView.setVisible(true);
    }

    public void mostrarComprarInfoValiosaView() {
        ComprarInfoValiosaView comprarInfoValiosaView = new ComprarInfoValiosaView(this);
        comprarInfoValiosaView.setVisible(true);
    }

    public void mostrarNaveReparada() {
        NaveReparadaView naveReparada = new NaveReparadaView(this);
        naveReparada.setVisible(true);
    }

    public void mostrarLlenarTanqueView() {
        SwingUtilities.invokeLater(() -> {
            LlenarTanqueView llenarTanqueView = new LlenarTanqueView(this, partida.getCosteRecarga());
            llenarTanqueView.setVisible(true);
        });
    }
    public int sistemaActual(){
        return partida.idSistemaActual();
    }

}
