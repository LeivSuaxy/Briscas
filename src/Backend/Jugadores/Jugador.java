package Backend.Jugadores;

import Backend.Cartas.Carta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase super y abstracta de Jugador, contenedora de los atributos y metodos necesarios para simular la posicion de un jugador en el
 * juego de briscas, con manos de cartas y sistemas de calculo de puntuaciones
 * @Author LeivSuaxy
 * @Date 30/10/2023 21:20
 */
public abstract class Jugador implements Serializable {
    //Atributos
    protected String nombre;
    protected ArrayList<Carta> cartasPoseidas;
    protected ArrayList<Carta> cartasPuntos;
    protected int puntuacion;
    protected boolean turnoGanado;

    //Metodos

    /**
     * Constructor de clase Jugador
     */
    public Jugador(String nombre){
        this.nombre = nombre;
        this.cartasPoseidas = new ArrayList<>(3);
        this.cartasPuntos = new ArrayList<>();
        this.puntuacion = 0;
        this.turnoGanado = false;
        cartasPoseidas.ensureCapacity(3);
    }

    /**
     * Metodo para sumar puntuacion
     */
    public void sumarPuntuacion(){
        puntuacion = 0;

        for (Carta e:cartasPuntos) {
            puntuacion += e.getValor();
        }
    }

    /**
     * Metodo para añadir cartas
     */
    public void agnadirCarta(Carta carta){
        cartasPoseidas.add(carta);
    }

    /**
     * Metodo para añadir cartas mientras el juego esta en ejecucion
     */
    public void agnadirCartaInGame(Carta carta){
        for (int i = 0; i < cartasPoseidas.size(); i++) {
            if(cartasPoseidas.get(i) == null){
                cartasPoseidas.set(i, carta);
            }
        }
    }

    /**
     * Metodo para buscar si existe una carta de tipo 7 o 2 en
     * @param cartaBuscar
     * @return
     */
    public int hayCartas(Carta cartaBuscar){
        int pos = 4;

        for (int i = 0; i < 3; i++) {
            if(cartasPoseidas.get(i).getIdfCarta() == cartaBuscar.getIdfCarta()){
                pos = i;
            }
        }

        return pos;
    }

    /**
     * Metodo para checkear las cartas en mano del jugador
     * @return String de cartas
     */
    public String checkCartas(){
        return " "+cartasPoseidas.get(0).getIdfCarta()+" "+cartasPoseidas.get(1).getIdfCarta()+" "+cartasPoseidas.get(2).getIdfCarta();
    }

    /**
     * Metodo para añadir las cartas ganadoras al arreglo de cartasPuntos
     */
    public void agnadirCartasGanadoras(Carta[] cartas){
        cartasPuntos.addAll(Arrays.asList(cartas));
    }

    /**
     * Metodo para obtener nombre del Jugador
     * @return Nombre del Jugador : String
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Metodo para obtener la puntuacion de jugador
     * @return Puntuacion de jugador : int
     */
    public int getPuntuacion(){
        return puntuacion;
    }

    /**
     * Metodo para obtener una carta dado una posicion dada
     * @param pos para ubicar la posicion de la carta
     * @return Carta ubicada en la pos : Carta
     */
    public Carta getCarta(int pos){
        return cartasPoseidas.get(pos);
    }

    /**
     * Metodo para insertar una carta en una posicion
     * @param pos Para especificar posicion de la carta
     * @param carta Para insertar la carta deseada
     */
    public void setCartasPoseidas(int pos, Carta carta){
        cartasPoseidas.set(pos, carta);
    }

    /**
     * Metodo para establecer el turno ganador al jugador
     * @param ganador Variable para establecer true or false
     */
    public void setTurnoGanado(boolean ganador){
            this.turnoGanado = ganador;
    }

    /**
     * Metodo getter del atributo que identifica si el jugador ha ganado un turno
     * @return true or false dependiendo si el jugador gano o perdio
     */
    public boolean isTurnoGanado(){
        return turnoGanado;
    }
}