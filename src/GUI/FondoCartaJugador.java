package GUI;

import Backend.Cartas.Carta;

/**
 * Clase heredera de Fondo con una orientacion mas dedicada al uso por jugadores
 * @superClass Fondo
 * @Author LeivSuaxy
 * @Date 11/11/2023 20:08
 */
public class FondoCartaJugador extends Fondo{
    private int posMano;

    /**
     * Constructor del Panel de las cartas del jugador
     * @param cartaAlmacenada Carta que tendra almacenada para su pintado y representacion del panel
     * @param a Ancho
     * @param l Largo
     * @param posMano Posicion que representara en una mano
     */
    public FondoCartaJugador(Carta cartaAlmacenada, int a, int l, int posMano) {
        super(cartaAlmacenada, a, l);
        this.posMano = posMano;
    }

    /**
     * Metodo que retorna posicion de la mano entrada por parametro por el constructor de este objeto, usada para la interaccion
     * del juego
     * @return Numero entero entre 0 y 2 que representa la posicion de la mano al jugar
     */
    public int getPosMano(){
        return posMano;
    }

}
