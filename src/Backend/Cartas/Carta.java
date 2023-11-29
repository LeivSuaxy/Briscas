package Backend.Cartas;

import javax.swing.*;
import java.io.Serializable;

/**
 * Clase super y abstracta de las Cartas, contenedora de todos los metodos y atributos esenciales de las clases Hijas
 * @Author LeivSuaxy
 * @Date 15/10/2023 12:18
 */
public abstract class Carta implements Serializable {
    //Atributos
    protected String tipo;
    protected int valor;
    protected int idfCarta;
    protected ImageIcon frenteCarta;

    //Metodos
    //Constructor
    public Carta(int valor, int idfCarta, ImageIcon frenteCarta){
        this.valor = valor;
        this.idfCarta = idfCarta;
        this.frenteCarta = frenteCarta;
    }

    /**
     * Metodo para obtener direccion de la imagen que posee la carta
     * @return Direccion fisica de la imagen : String
     */
    public String getFrenteCarta() {
        return frenteCarta.getDescription();
    }

    /**
     * Metodo para obtener valor de la carta
     * @return Valor de carta : int
     */
    public int getValor(){
        return valor;
    }

    /**
     * Metodo para obtener identificacion de la carta
     * @return Identificador de la carta : int
     */
    public int getIdfCarta(){
        return idfCarta;
    }

    /**
     * Metodo para obtener el tipo de carta
     * @return Tipo de carta : String
     */
    public abstract String getTipo();
}