package Backend.Cartas;

import javax.swing.*;

/**
 * Clase de cartas de tipo Copa
 * @superClass Carta
 * @Author LeivSuaxy
 * @Date 15/10/2023 12:18
 */
public class Copa extends Carta{

    //Constructor
    public Copa(int valor, int idfCarta, ImageIcon frenteCarta){
        super(valor, idfCarta, frenteCarta);
    }

    /**
     * Metodo para obtener el tipo de carta
     * @return Tipo de carta : String
     */
    @Override
    public String getTipo() {
        return "Copa";
    }
}