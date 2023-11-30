package Backend;

import Backend.Cartas.*;
import Backend.Jugadores.JugadorH;
import Backend.Jugadores.JugadorIA;

import javax.swing.*;
import java.io.Serializable;
import java.util.Random;

/**
 * Clase destinada al desarrollo y funcionalidad general del juego Briscas al Reves, al instanciar un objeto de esta clase
 * accederias a todas las funcionalidades y metodos para obtener recursos visuales y funcionales del juego para su posterior
 * desarrollo
 *
 * @Author LeivSuaxy
 * @Date 28/10/2023 21:17
 */
public class Juego implements Serializable {

    //Atributos
    private JugadorH jugador;
    private JugadorIA jugadorIA = new JugadorIA("IA");
    private Baraja barajaJuego;
    private int turno;
    private int cantJugadas = 20;
    private Carta paloGanador;
    private Carta cartaJugador = null;
    private Carta cartaIA = null;

    //Metodos
    //Constructor
    public Juego(String nombre){
        //Creacion del Jugador
        this.jugador = new JugadorH(nombre);

        //Instanciar Baraja y barajear
        barajaJuego = new Baraja();

        //Repartir Cartas Iniciales
        for (int i = 0; i < 3; i++) {
            jugador.agnadirCarta(barajaJuego.sacarCarta());
            jugadorIA.agnadirCarta(barajaJuego.sacarCarta());
        }

        //Verificar Carta Ganadora
        paloGanador = barajaJuego.getCartaArray(0);

        //Turno
        if(Math.random() < 0.5) {
            turno = 1;
        }
        else {
            turno = 2;
            jugarIA();
        }
    }

    //Metodos Invocados

    /**
     * Getter de paloganador
     * @return paloganador : String
     */
    public Carta getPaloGanador(){
        return paloGanador;
    }

    /**
     * Metodo getter que obtiene de la baraja el paloGanador para el juego
     * @return paloganador en baraja : Carta
     */
    public Carta getPaloGanadorObjeto(){
        return barajaJuego.getCarta(0);
    }

    /**
     * Getter del turno de juego
     * @return turno : int
     */
    public int getTurno(){
        return turno;
    }

    /**
     * Metodo para obtener jugadorNormal del juego y acceder a sus metodos
     * @return jugadorNormal : JugadorH
     */
    public JugadorH getJugadorNormal() {
        return jugador;
    }

    /**
     * Metodo para obtener al jugadorIA del juego y acceder a sus metodos
     * @return jugadorIA : JugadorIA
     */
    public JugadorIA getJugadorIA() {
        return jugadorIA;
    }

    /**
     * Metodo para obtener la carta que la IA puso
     * @return carta colocada por la IA : Carta
     */
    public Carta getCartaIAPuesta(){
        return cartaIA;
    }

    /**
     * Metodo para obtener la carta que el jugador puso
     * @return carta colocada por jugador : Carta
     */
    public Carta getCartaJugador(){
        return cartaJugador;
    }

    //Mecanicas Juego

    /**
     * Metodo para que el jugador ponga una carta (La posicion de obtiene a partir del evento de interfaz lanzadao que
     * obtiene el valor del panel y lo lanza como valor de la posicion a obtener.
     * @param pos Posicion obtenida del panel que contiene la carta
     */
    public void ponerCartaJugador(int pos){
        cartaJugador = jugador.getCarta(pos);
    }

    /**
     * Metodo automatico para que juegue la IA (En construccion)
     */
    public void jugarIA(){
        int posicion;
        posicion = jugadorIA.posJugar(cartaJugador, paloGanador.getTipo(), cantJugadas);
        cartaIA = jugadorIA.getCarta(posicion);
        jugadorIA.setCartasPoseidas(posicion, null);
    }

    /**
     * Metodo para analizar la comparacion de cartas y establecer un ganador en la ronda
     */
    public void checkGanador(){
        //Inicio de variables
        int puntuacionJugador = 0;
        int puntuacionIA = 0;
        Carta[] cartas = new Carta[2];
        cartas[0] = cartaJugador;
        cartas[1] = cartaIA;

        //Comparacion de cartas
        //Si la carta es del Jugador y la de la IA son del palo ganador
        if(cartaJugador.getTipo().equals(paloGanador.getTipo()) && cartaIA.getTipo().equals(paloGanador.getTipo())){
            puntuacionIA++; //1
            puntuacionJugador++; //1
            //Si el valor de ambas son iguales
            if(cartaJugador.getValor() == cartaIA.getValor()){
               if(turno == 1){
                   puntuacionJugador++;
               } else {
                   puntuacionIA++;
               }
               //Si la carta del jugador tiene mayor valo que la de la IA
            } else if(cartaJugador.getValor() > cartaIA.getValor()){
                puntuacionJugador++; // 2
            } else {
                puntuacionIA++; // 2
            }
            //Si la del jugador es del palo ganador y la de la IA no lo es
        } else if(cartaJugador.getTipo().equals(paloGanador.getTipo()) && !cartaIA.getTipo().equals(paloGanador.getTipo())){
            puntuacionJugador++; // 1
            //Caso en que el del jugador no lo es y el de la IA si es
        } else if(!(cartaJugador.getTipo().equals(paloGanador.getTipo())) && cartaIA.getTipo().equals(paloGanador.getTipo())){
            puntuacionIA++;
            //Caso en que ambas no lo son
        } else if(!cartaJugador.getTipo().equals(paloGanador.getTipo()) && !cartaIA.getTipo().equals(paloGanador.getTipo())){
            //Si son del mismo valor
            if(cartaJugador.getValor() == cartaIA.getValor()){
                if(turno == 1){
                    puntuacionJugador++;
                } else {
                    puntuacionIA++;
                }
                //Si el valor del jugador es mayor que el de la IA
            } else if(cartaJugador.getValor() > cartaIA.getValor()){
                puntuacionJugador++;
            } else {
                puntuacionIA++;
            }
        }

        //Caso en que gana la IA
        if(puntuacionIA > puntuacionJugador){
            jugadorIA.agnadirCartasGanadoras(cartas);
            this.turno = 2;
            jugadorIA.setTurnoGanado(true);
            jugador.setTurnoGanado(false);
        } else { //Caso en que gana el jugador
            jugador.agnadirCartasGanadoras(cartas);
            this.turno = 1;
            jugadorIA.setTurnoGanado(false);
            jugador.setTurnoGanado(true);
        }

        //Metodo para sumar puntos
        jugador.sumarPuntuacion();
        jugadorIA.sumarPuntuacion();

        cartaIA = null;
        cartaJugador = null;
    }

    /**
     * Verificar si no hay cartas en el tablero
     * @return true or false en caso que hayan o no cartas
     */
    public boolean checkAllCards(){
        return cartaIA != null && cartaJugador != null;
    }

    /**
     * Verifica si es true : turno de la IA o sea 2 y si es false : turno del jugador o sea != 2
     * @return true or false dependiendo del turno
     */
    public boolean checkTurnoPlay(){
        return this.turno == 2;
    }

    /**
     * Metodo para repartir las cartas mientras se esta jugando, analiza si falta alguna carta en la mano y la juega
     */
    public void repartirCartasInGame(){
        if(barajaJuego.getPosCartaASacar() != 0) {
            jugador.agnadirCartaInGame(barajaJuego.sacarCarta());
            jugadorIA.agnadirCartaInGame(barajaJuego.sacarCarta());
        } else {
        }
    }

    /**
     * Metodo que hara que decrezca la cantidad de jugadas restantes
     */
    public void jugadaEjecutada(){
        cantJugadas--;
    }

    /**
     * Getter de la cantidad de jugadas restantes
     * @return cantidadJugadas : int
     */
    public int getCantJugadas(){
        return cantJugadas;
    }

    public void intercambiarCartas(int pos){
        if(jugador.getCarta(pos).getIdfCarta() == 2 && jugador.isTurnoGanado()){
            switch (paloGanador.getIdfCarta()) {
                case 7:
                case 6:
                case 5:
                case 4:
                    intercambiarCartaAccion(pos);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Imposible Cambiar Carta");
                    break;
            }
        } else if(jugador.getCarta(pos).getIdfCarta() == 7 && jugador.isTurnoGanado()){
            switch (paloGanador.getIdfCarta()) {
                case 1:
                case 3:
                case 12:
                case 10:
                case 11:
                    intercambiarCartaAccion(pos);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Imposible Cambiar Carta");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No es cambiable la carta");
        }
    }

    /**
     * Metodo interno de la clase juego para intercambiar cartas
     */
    private void intercambiarCartaAccion(int pos){
        Carta aux;

        aux = paloGanador;
        paloGanador = jugador.getCarta(pos);
        jugador.setCartasPoseidas(pos, aux);
    }

    /**
     * Metodo para saber quien fue el ganador
     * @return frase que te permitira saber quien gano
     */
    public String getGanador(){
        String frase = "NobodyKnows";

        if(jugador.getPuntuacion() == jugadorIA.getPuntuacion()){
            frase = "Empate!";
        } else if(jugador.getPuntuacion() < jugadorIA.getPuntuacion()){
            frase = "El ganador es "+jugador.getNombre();
        } else if(jugador.getPuntuacion() > jugadorIA.getPuntuacion()){
            frase = "El ganador es la computadora!";
        }

        return frase;
    }
}