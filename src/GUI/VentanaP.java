package GUI;

import Backend.Cartas.Carta;
import Backend.Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Interfaz Grafica principal para el funcionamiento visual del juego Briscas al Reves, con todas sus objetos, metodos y
 * eventos que desarrollan el ambiente del juego
 * @Author LeivSuaxy
 * @Date 15/10/2023 11:17
 */
public class VentanaP extends JFrame{
    //Atributos
    private ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Imagenes/Fondo.png"));
    private String nombreJugador;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem guardado;
    private JMenuItem ayuda;
    private JMenuItem menuItem;
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

    //Metodos
    //Constructor

    /**
     * Constructor de Ventana Principal del juego Briscas al reves
     * @param w parametro para establecer el width de la ventana (largo)
     * @param h parametro para establecer el height de la ventana (ancho)
     */
    public VentanaP(int w, int h, String nombreJugador){
        this.nombreJugador = nombreJugador;
        setSize(w, h);
        setTitle("Briscas al reves");
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponents();
    }

    /**
     * Constructor sobrecargado de Ventana Principal del juego Briscas al reves para en caso que exista ya un juego
     * en curso
     * @param w parametro para establecer el width de la ventana (largo)
     * @param h parametro para establecer el height de la ventana (ancho)
     * @param juego parametro para pasar el juego en curso
     */
    public VentanaP(int w, int h, Juego juego){
        setSize(w, h);
        setTitle("Briscas al reves");
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponents(juego);
    }

    /**
     * Metodo de inicio de componentes de la ventana
     */
    public void initComponents(){
        initMenu();
        initJuego();
        initPanel();
        initLabels();
    }

    /**
     * Metodo por sobrecarga en caso que haya una partida existende
     * @param juego se le pasa el juego ya iniciado por parametro
     */
    public void initComponents(Juego juego){
        initMenu();
        this.juego = juego;
        initPanel();
        initLabels();
        if(juego.getCartaJugador() != null) {
            panelReciv1.setCarta(juego.getCartaJugador());
        }

        if(juego.getCartaIAPuesta() != null) {
            panelReciv2.setCarta(juego.getCartaIAPuesta());
        }
    }

    /**
     * Inicio del juego (Proxima implementacion se le pasa por parametro un String)
     */
    public void initJuego(){
        juego = new Juego(nombreJugador);
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

        baraja = new Fondo(imagenFondo, 155, 240);
        baraja.setLocation(575, 527);
        p.add(baraja);

        if(juego.getJugadorIA().getCarta(0) != null)
            cartaIA[0] = new Fondo(imagenFondo, 155, 240);
        else
            cartaIA[0] = new Fondo((Carta) null, 155, 240);

        if(juego.getJugadorIA().getCarta(1) != null)
            cartaIA[1] = new Fondo(imagenFondo, 155, 240);
        else
            cartaIA[1] = new Fondo((Carta) null, 155, 240);

        if(juego.getJugadorIA().getCarta(2) != null)
            cartaIA[2] = new Fondo(imagenFondo,155, 240);
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
                                jugarIA();
                                panelReciv2.setCarta(juego.getCartaIAPuesta());
                                panelReciv1.setFondoVerde();
                            }
                            for (int i = 0; i < 3; i++) {
                                if (juego.getJugadorIA().getCarta(i) == null)
                                    cartaIA[i].setCarta(juego.getJugadorIA().getCarta(i));
                                else
                                    cartaIA[i].setImagen(imagenFondo);
                            }

                            panel.setCarta(juego.getJugadorNormal().getCarta(panel.getPosMano()));
                            puntuacionIA.setText("Puntos: " + juego.getJugadorIA().getPuntuacion());
                            puntuacionJugador.setText("Puntos: " + juego.getJugadorNormal().getPuntuacion());
                            juego.jugadaEjecutada();

                            if (juego.getCantJugadas() == 0) {
                                JOptionPane.showMessageDialog(null, juego.getGanador());
                                int opcion = JOptionPane.showConfirmDialog(null, "El juego ha acabado desea volver a jugar");
                                if(opcion == JOptionPane.YES_OPTION){
                                    resetearJuego();
                                } else {
                                    dispose();
                                }
                            }
                        }
                    }
                } else { // Construccion del Click Derecho
                    if(juego.getJugadorNormal().getCarta(panel.getPosMano()) != null){
                        juego.intercambiarCartas(panel.getPosMano());
                        panel.setCarta(juego.getJugadorNormal().getCarta(panel.getPosMano()));
                        panelCartaGanadora.setCarta(juego.getPaloGanador());
                    }
                }
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
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        ayuda = new JMenuItem("Ayuda");
        guardado = new JMenuItem("Guardar");
        menuItem = new JMenuItem("Reiniciar Juego");

        ayuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAyuda ayudaInterface = new VentanaAyuda();
            }
        });

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "Estas seguro de reiniciar?");
                if(opcion == JOptionPane.YES_OPTION){
                    resetearJuego();
                }
            }
        });

        guardado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPartida();
            }
        });

        menu.add(menuItem);
        menu.add(guardado);
        menu.add(ayuda);

        menuBar.add(menu);

        setJMenuBar(menuBar);
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

    private void resetearJuego(){
        initJuego();
        panelCarta.setCarta(juego.getJugadorNormal().getCarta(0));
        panelCarta1.setCarta(juego.getJugadorNormal().getCarta(1));
        panelCarta2.setCarta(juego.getJugadorNormal().getCarta(2));
        panelCartaGanadora.setCarta(juego.getPaloGanador());
        panelReciv1.setFondoVerde();
        panelReciv2.setFondoVerde();
        for (int i = 0; i < 3; i++) {
            cartaIA[i].setImagen(imagenFondo);
        }
        puntuacionJugador.setText("Puntos: "+juego.getJugadorNormal().getPuntuacion());
        puntuacionIA.setText("Puntos: "+juego.getJugadorIA().getPuntuacion());
    }

    private void guardarPartida(){
        FileOutputStream fileStream;
        ObjectOutputStream objStream;

        try {
            fileStream = new FileOutputStream("archivo.txt");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error guardadndo datos!");
            throw new RuntimeException(e);
        }

        try {
            objStream = new ObjectOutputStream(fileStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            objStream.writeObject(juego);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            objStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JOptionPane.showMessageDialog(null, "Guardado!");
    }
}