package Backend;

import Backend.Cartas.*;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Clase Baraja, contenedora de un arreglo de 40 Cartas y los metodos necesarios para barajear y obtener las cartas necesarias
 * para el desarrollo del juego, se puede usar para crear barajas funcionales en cualquier programa desarrollado en java siempre
 * y cuando tenga los recursos visuales necesarios para poder cargar las direcciones de las imagenes
 * @Author LeivSuaxy
 * @Date 15/10/2023 14:19
 */
public class Baraja implements Serializable {
    private Carta[] baraja = new Carta[40];
    private ArrayList<Carta> barajaOpcional = new ArrayList<>();
    private int posCartaASacar;

    //Constructor
    public Baraja() {
        /* Creando baraja
          1 = 11
          3 = 10
          12 = 4
          11 = 3
          10 = 2
          Resto = 0
          */

        //Creando Copas
        baraja[0] = new Copa(11, 1, new ImageIcon(getClass().getResource("/Imagenes/Copa/1 Copa.png")));
        baraja[1] = new Copa(0, 2, new ImageIcon(getClass().getResource("/Imagenes/Copa/2 Copa.png")));
        baraja[2] = new Copa(10, 3, new ImageIcon(getClass().getResource("/Imagenes/Copa/3 Copa.png")));
        baraja[3] = new Copa(0, 4, new ImageIcon(getClass().getResource("/Imagenes/Copa/4 Copa.png")));
        baraja[4] = new Copa(0, 5, new ImageIcon(getClass().getResource("/Imagenes/Copa/5 Copa.png")));
        baraja[5] = new Copa(0, 6, new ImageIcon(getClass().getResource("/Imagenes/Copa/6 Copa.png")));
        baraja[6] = new Copa(0, 7, new ImageIcon(getClass().getResource("/Imagenes/Copa/7 Copa.png")));
        baraja[7] = new Copa(2, 10, new ImageIcon(getClass().getResource("/Imagenes/Copa/10 Copa.png")));
        baraja[8] = new Copa(3, 11, new ImageIcon(getClass().getResource("/Imagenes/Copa/11 Copa.png")));
        baraja[9] = new Copa(4, 12, new ImageIcon(getClass().getResource("/Imagenes/Copa/12 Copa.png")));

        //Creando BaOro
        baraja[10] = new Basto(11, 1, new ImageIcon(getClass().getResource("/Imagenes/Palo/1 Palo.png")));
        baraja[11] = new Basto(0, 2, new ImageIcon(getClass().getResource("/Imagenes/Palo/2 Palo.png")));
        baraja[12] = new Basto(10, 3, new ImageIcon(getClass().getResource("/Imagenes/Palo/3 Palo.png")));
        baraja[13] = new Basto(0, 4, new ImageIcon(getClass().getResource("/Imagenes/Palo/4 Palo.png")));
        baraja[14] = new Basto(0, 5, new ImageIcon(getClass().getResource("/Imagenes/Palo/5 Palo.png")));
        baraja[15] = new Basto(0, 6, new ImageIcon(getClass().getResource("/Imagenes/Palo/6 Palo.png")));
        baraja[16] = new Basto(0, 7, new ImageIcon(getClass().getResource("/Imagenes/Palo/7 Palo.png")));
        baraja[17] = new Basto(2, 10, new ImageIcon(getClass().getResource("/Imagenes/Palo/10 Palo.png")));
        baraja[18] = new Basto(3, 11, new ImageIcon(getClass().getResource("/Imagenes/Palo/11 Palo.png")));
        baraja[19] = new Basto(4, 12, new ImageIcon(getClass().getResource("/Imagenes/Palo/12 Palo.png")));
        //Creando Monedas
        baraja[20] = new Moneda(11, 1, new ImageIcon(getClass().getResource("/Imagenes/Oro/1 Oro.png")));
        baraja[21] = new Moneda(0, 2, new ImageIcon(getClass().getResource("/Imagenes/Oro/2 Oro.png")));
        baraja[22] = new Moneda(10, 3, new ImageIcon(getClass().getResource("/Imagenes/Oro/3 Oro.png")));
        baraja[23] = new Moneda(0, 4, new ImageIcon(getClass().getResource("/Imagenes/Oro/4 Oro.png")));
        baraja[24] = new Moneda(0, 5, new ImageIcon(getClass().getResource("/Imagenes/Oro/5 Oro.png")));
        baraja[25] = new Moneda(0, 6, new ImageIcon(getClass().getResource("/Imagenes/Oro/6 Oro.png")));
        baraja[26] = new Moneda(0, 7, new ImageIcon(getClass().getResource("/Imagenes/Oro/7 Oro.png")));
        baraja[27] = new Moneda(2, 10, new ImageIcon(getClass().getResource("/Imagenes/Oro/10 Oro.png")));
        baraja[28] = new Moneda(3, 11, new ImageIcon(getClass().getResource("/Imagenes/Oro/11 Oro.png")));
        baraja[29] = new Moneda(4, 12, new ImageIcon(getClass().getResource("/Imagenes/Oro/12 Oro.png")));
        //Creando Espadas
        baraja[30] = new Espada(11, 1, new ImageIcon(getClass().getResource("/Imagenes/Espada/1 Espada.png")));
        baraja[31] = new Espada(0, 2, new ImageIcon(getClass().getResource("/Imagenes/Espada/2 Espada.png")));
        baraja[32] = new Espada(10, 3, new ImageIcon(getClass().getResource("/Imagenes/Espada/3 Espada.png")));
        baraja[33] = new Espada(0, 4, new ImageIcon(getClass().getResource("/Imagenes/Espada/4 Espada.png")));
        baraja[34] = new Espada(0, 5, new ImageIcon(getClass().getResource("/Imagenes/Espada/5 Espada.png")));
        baraja[35] = new Espada(0, 6, new ImageIcon(getClass().getResource("/Imagenes/Espada/6 Espada.png")));
        baraja[36] = new Espada(0, 7, new ImageIcon(getClass().getResource("/Imagenes/Espada/7 Espada.png")));
        baraja[37] = new Espada(2, 10, new ImageIcon(getClass().getResource("/Imagenes/Espada/10 Espada.png")));
        baraja[38] = new Espada(3, 11, new ImageIcon(getClass().getResource("/Imagenes/Espada/11 Espada.png")));
        baraja[39] = new Espada(4, 12, new ImageIcon(getClass().getResource("/Imagenes/Espada/12 Espada.png")));

        barajear();

        barajaOpcional.addAll(Arrays.asList(baraja));

        posCartaASacar = barajaOpcional.size()-1;
    }

    //Metodo para obtener numero random
    private static int getRand(){
        Random rand = new Random();
        return rand.nextInt(40);
    }

    /**
     * Metodo para barajear mazo
     */
    public void barajear(){
        /*Se recorre esa cantidad de veces xq es el producto de la cantidad de cartas x 3, 40x3 para garantizar un ordenamiento
        alterno de las cartas*/

        int randInit;
        int randFinish;
        Carta aux;

        for (int i = 0; i < (baraja.length*3); i++) {
            randInit = 0;
            randFinish = 0;
            while(randInit == randFinish){
                randInit = getRand();
                randFinish = getRand();
            }
            aux = baraja[randInit];
            baraja[randInit] = baraja[randFinish];
            baraja[randFinish] = aux;
        }
    }

    /**
     * Metodo para obtener una carta dado su posicion Usada preferiblemente para obtener el palo ganador
     * @param pos Posicion 0 del palo ganador
     * @return Carta de la posicion : Carta
     */
    public Carta getCarta(int pos){
        return baraja[pos];
    }

    /**
     * Metodo para sacar una carta del mazo, usada preferiblemente para repartir cartas
     * @return Carta sacada : Carta
     */
    public Carta sacarCarta(){
        Carta cartaReturn = null;
        if(!barajaOpcional.isEmpty() && posCartaASacar >= 0){
            try {
                cartaReturn = barajaOpcional.get(posCartaASacar);
                barajaOpcional.set(posCartaASacar, null);
                posCartaASacar--;
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        return cartaReturn;
    }

    /**
     * Metodo Getter para verificar por cual posicion de sacar carta anda
     * @return posCartaASacar : int
     */
    public int getPosCartaASacar() {
        return posCartaASacar;
    }

    /**
     * Metodo para obtener una carta del array dada una posicion
     * @param pos Posicion para sacar carta del array
     * @return Carta ubicada en la posicion : Carta
     */
    public Carta getCartaArray(int pos){
        return barajaOpcional.get(pos);
    }
}