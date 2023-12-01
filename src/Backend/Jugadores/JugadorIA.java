package Backend.Jugadores;

import Backend.Cartas.Carta;

import java.util.Random;

/**
 * Clase JugadorIA la cual contiene el motor de inteligencia para que la IA pueda jugar y representar un reto para el jugador
 * humano que se encuentre en contra
 * @superClass Jugador
 * @Author LeivSuaxy
 * @Date 31/10/2023 8:55
 */
public class JugadorIA extends Jugador{
    public JugadorIA(String nombre){
        super(nombre);
    }

    /**
     * Metodo de analisis de carta lanzada por el jugador para devolver la mejor opcion de juego
     *     Grados de Posible ganada
     *     Grado Especial: Carta 1 es palo ganador Carta 2 no es palo ganador
     *     Grado 2: Carta 1 es palo ganador Carta 2 es palo ganador pero carta 1 es superior a 2
     *     Grado 3: Ambas no son palo ganador pero la carta es Superior
     *
     *     En caso de Perder
     *     Grado Especial: Carta 2 es Palo ganador, buscar carta mas inutil
     *     Grado 2: Carta 2 no es palo ganador, hay que buscar carta mas inutil
     * @param cartaAnalisis Carta lanzada por el jugador contrario para su posterior analisis
     * @param paloGanador Carta de paloganador para analizar carta victoriosa
     * @return Retorna una posicion que es la posicion ganadora para que con otro metodo lanzar esa carta a partir de la
     * posicion
     */
    public int posJugar(Carta cartaAnalisis, String paloGanador){
        int[] puntuaciones = {0, 0, 0};
        boolean[] posibilidades = {false, false, false};

        if(cartaAnalisis != null) {
            for (int i = 0; i < 3; i++) {
                if (cartasPoseidas.get(i) != null) {
                    if (cartaAnalisis.getTipo().equals(paloGanador) && cartasPoseidas.get(i).getTipo().equals(paloGanador)) {
                        if (cartasPoseidas.get(i).getValor() == cartaAnalisis.getValor()) {
                            posibilidades[i] = false;
                        } else if (cartasPoseidas.get(i).getValor() > cartaAnalisis.getValor()) {
                            //Carta especial
                            puntuaciones[i] += 10;
                            posibilidades[i] = true;
                        } else if (cartasPoseidas.get(i).getValor() < cartaAnalisis.getValor()) {
                            //Carta no especial
                            posibilidades[i] = false;
                        }
                    } else if (cartaAnalisis.getTipo().equals(paloGanador) && !cartasPoseidas.get(i).getTipo().equals(paloGanador)) {
                        //Carta no especial
                        posibilidades[i] = false;
                    } else if (!cartaAnalisis.getTipo().equals(paloGanador) && cartasPoseidas.get(i).getTipo().equals(paloGanador)) {
                        puntuaciones[i] += 10;
                        posibilidades[i] = true;
                    } else if (!cartaAnalisis.getTipo().equals(paloGanador) && !cartasPoseidas.get(i).getTipo().equals(paloGanador)) {
                        if (cartaAnalisis.getValor() == cartasPoseidas.get(i).getValor()) {
                            //Grado 2
                            puntuaciones[i]++;
                            posibilidades[i] = true;
                        } else if (cartaAnalisis.getValor() > cartasPoseidas.get(i).getValor()) {
                            //Grado 2 con valor
                            puntuaciones[i] += 2;
                            posibilidades[i] = true;
                        } else if (cartaAnalisis.getValor() < cartasPoseidas.get(i).getValor()) {
                            posibilidades[i] = false;
                        }
                    }
                } else {
                    posibilidades[i] = false;
                }
            }
        }

        int posRetorno = 0;
        int puntuacionCheck = 0;
        Random rand = new Random();

        //CheckEverything
        for (int i = 0; i < 3; i++) {
            puntuacionCheck+=puntuaciones[i];
        }

        if(puntuacionCheck == 0){
            do {
                posRetorno = rand.nextInt(3);
            } while(cartasPoseidas.get(posRetorno) == null);
        } else {
            puntuacionCheck = 100;
            for (int i = 0; i < 3; i++) {
                if(puntuaciones[i] < puntuacionCheck && cartasPoseidas.get(i) != null){
                    posRetorno = i;
                }
            }
        }

        return posRetorno;
    }
}
