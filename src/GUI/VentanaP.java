package GUI;

import Backend.Cartas.Carta;
import Backend.Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Interfaz Grafica principal para el funcionamiento visual del juego Briscas al Reves, con todas sus objetos, metodos y
 * eventos que desarrollan el ambiente del juego
 * @Author LeivSuaxy
 * @Date 15/10/2023 11:17
 */
public class VentanaP extends JFrame{
    //Atributos
    private Juego juego;
    private JPanel p;
    private FondoCartaJugador panelCarta;
    private FondoCartaJugador panelCarta1;
    private FondoCartaJugador panelCarta2;
    private Fondo panelCartaGanadora;
    private Fondo baraja;
    private Fondo[] cartaIA;
    private Fondo panelReciv1;
    private Fondo panelReciv2;
    private JLabel puntuacionIA;
    private JLabel puntuacionJugador;
    private JButton botonReset;

    //Metodos
    //Constructor

    /**
     * Constructor de Ventana Principal del juego Briscas al reves
     * @param w parametro para establecer el width de la ventana (largo)
     * @param h parametro para establecer el height de la ventana (ancho)
     */
    public VentanaP(int w, int h){
        setSize(w, h-50);
        setTitle("Briscas al reves");
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponents();
    }

    /**
     * Metodo de inicio de componentes de la ventana
     */
    public void initComponents(){
        initJuego();
        initPanel();
        initButton();
        initLabels();
        initMenu();
    }

    /**
     * Inicio del juego (Proxima implementacion se le pasa por parametro un String)
     */
    public void initJuego(){
        juego = new Juego("Pepe");
    }

    /**
     * Iniciando Botones
     */
    public void initButton(){
        botonReset = new JButton("Reset");
        botonReset.setBounds(50, 50, 50, 50);
        p.add(botonReset);

        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initComponents();
            }
        };

        botonReset.addActionListener(action);
    }

    /**
     * Iniciando labels
     */
    public void initLabels(){
        puntuacionIA = new JLabel("Puntos: "+juego.getJugadorIA().getPuntuacion());
        puntuacionIA.setForeground(Color.RED);
        puntuacionIA.setFont(new Font("arial", Font.BOLD, 40));
        puntuacionIA.setBounds(1300, 20, 300, 50);
        p.add(puntuacionIA);

        puntuacionJugador = new JLabel("Puntos: "+juego.getJugadorNormal().getPuntuacion());
        puntuacionJugador.setForeground(Color.BLUE);
        puntuacionJugador.setFont(new Font("arial", Font.BOLD, 40));
        puntuacionJugador.setBounds(1300, 750, 300, 50);
        p.add(puntuacionJugador);
    }

    /**
     * Iniciando paneles
     */
    public void initPanel(){
        cartaIA = new Fondo[3];
        p = new JPanel();
        p.setLayout(null);
        p.setBounds(0,0,this.getWidth(), this.getHeight());
        p.setBackground(new Color(1, 86, 0));

        this.getContentPane().add(p);

        panelCarta = new FondoCartaJugador(juego.getJugadorNormal().getCarta(0), 150, 235, 0);
        panelCarta.setLocation(750, 750);
        p.add(panelCarta);

        panelCarta.addMouseListener(cambioCartas(panelCarta));

        panelCarta1 = new FondoCartaJugador(juego.getJugadorNormal().getCarta(1), 150, 235, 1);
        panelCarta1.setLocation(930, 750);
        p.add(panelCarta1);

        panelCarta1.addMouseListener(cambioCartas(panelCarta1));

        panelCarta2 = new FondoCartaJugador(juego.getJugadorNormal().getCarta(2), 150, 235, 2);
        panelCarta2.setLocation(1110, 750);
        p.add(panelCarta2);

        panelCarta2.addMouseListener(cambioCartas(panelCarta2));

        panelCartaGanadora = new Fondo(juego.getPaloGanadorObjeto(), 150, 235);
        panelCartaGanadora.setLocation(575, 253);
        p.add(panelCartaGanadora);

        baraja = new Fondo("/Imagenes/Fondo.png", 155, 240);
        baraja.setLocation(575, 527);
        p.add(baraja);

        if(juego.getJugadorIA().getCarta(0) != null)
            cartaIA[0] = new Fondo("/Imagenes/Fondo.png", 155, 240);
        else
            cartaIA[0] = new Fondo((Carta) null, 155, 240);

        if(juego.getJugadorIA().getCarta(1) != null)
            cartaIA[1] = new Fondo("/Imagenes/Fondo.png", 155, 240);
        else
            cartaIA[1] = new Fondo((Carta) null, 155, 240);

        if(juego.getJugadorIA().getCarta(2) != null)
            cartaIA[2] = new Fondo("/Imagenes/Fondo.png",155, 240);
        else
            cartaIA[2] = new Fondo((Carta) null, 155, 240);

        cartaIA[0].setLocation(750, 20);
        p.add(cartaIA[0]);

        cartaIA[1].setLocation(930, 20);
        p.add(cartaIA[1]);

        cartaIA[2].setLocation(1110, 20);
        p.add(cartaIA[2]);

        panelReciv1 = new Fondo((Carta) null, 155, 240);
        panelReciv1.setLocation(840, 385);
        p.add(panelReciv1);

        if(juego.getTurno() == 1) {
            panelReciv2 = new Fondo((Carta) null, 155, 240);
        } else {
            panelReciv2 = new Fondo(juego.getCartaIAPuesta(), 155, 240);
        }

        panelReciv2.setLocation(1020, 385);
        panelReciv2.setBackground(new Color(1, 86, 0));
        p.add(panelReciv2);
    }

    /**
     * Listener para accionar los paneles y jugar
     * @param panel Panel que toca el usuario para obtener su carta y hacer las acciones posteriores
     * @return Evento que se ejecutara
     */
    private MouseListener cambioCartas (FondoCartaJugador panel){
        MouseListener evento = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    if (juego.getJugadorNormal().getCarta(panel.getPosMano()) != null) {
                        juego.ponerCartaJugador(panel.getPosMano());
                        panelReciv1.setCarta(juego.getCartaJugador());
                        juego.getJugadorNormal().setCartasPoseidas(panel.getPosMano(), null);
                        panel.setCarta(null);
                        if (juego.getTurno() != 2 || !juego.checkAllCards()) {
                            jugarIA();
                        }


                        actualizarPaneles();

                        if (juego.getCantJugadas() != 0) {
                            juego.checkGanador();
                            juego.repartirCartasInGame();
                            if (juego.checkTurnoPlay() && juego.getCantJugadas() != 1) {
                                // System.out.println("Turno IA Test");
                                jugarIA();
                                panelReciv2.setCarta(juego.getCartaIAPuesta());
                                panelReciv1.setFondoVerde();
                                // System.out.println("Final Turno IA Test");
                            }
                            //System.out.println("Iniciando for de actualizar Imagenes");
                            for (int i = 0; i < 3; i++) {
                                if (juego.getJugadorIA().getCarta(i) == null)
                                    cartaIA[i].setCarta(juego.getJugadorIA().getCarta(i));
                                else
                                    cartaIA[i].setImagen("/Imagenes/Fondo.png");
                                // System.out.println("Iteracion: "+i);
                            }
                            //System.out.println("Error encontrado finish");
                            panel.setCarta(juego.getJugadorNormal().getCarta(panel.getPosMano()));
                            puntuacionIA.setText("Puntos: " + juego.getJugadorIA().getPuntuacion());
                            puntuacionJugador.setText("Puntos: " + juego.getJugadorNormal().getPuntuacion());
                            juego.jugadaEjecutada();
                            System.out.println("CantJugadas: " + juego.getCantJugadas());
                            if (juego.getCantJugadas() == 0) {
                                JOptionPane.showMessageDialog(null, "El juego ha acabado");
                            }
                            System.out.println("Depuracion");
                        }
                    } else {
                        System.out.println("No existe carta en la mano");
                    }
                } else { // Construccion del Click Derecho
                    System.out.println("Click Derecho");
                    if(juego.getJugadorNormal().getCarta(panel.getPosMano()) != null){
                        juego.intercambiarCartas(panel.getPosMano(), juego.getJugadorNormal());
                    }
                }

                //actualizarPaneles();
                //CheckGame
                /*if(juego.endGame()){
                    System.out.println("EndGame");
                }*/
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setLocation(panel.getX(), panel.getY()-40);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setLocation(panel.getX(), panel.getY()+40);

            }
        };

        return evento;
    }

    /**
     * Futuro menu (Building)
     */
    private void initMenu(){

    }

    /**
     * Metodo para actualizar los paneles inGame
     */
    private void actualizarPaneles(){
        if(juego.getCartaIAPuesta() != null){
            panelReciv2.setCarta(juego.getCartaIAPuesta());
        } else {
            panelReciv2.setFondoVerde();
        }

        if(juego.getCartaJugador() != null){
            panelReciv1.setCarta(juego.getCartaJugador());
        } else {
            panelReciv2.setFondoVerde();
        }
    }

    /**
     * Metodo para que juegue la IA
     */
    private void jugarIA(){
        juego.jugarIA();
        panelReciv2.setCarta(juego.getCartaIAPuesta());
    }
}