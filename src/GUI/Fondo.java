package GUI;

import Backend.Cartas.Carta;

import javax.swing.*;
import java.awt.*;

/**
 * Clase heredera de JPanel con metodos de pintado de cartas y contenedor de objetos para que el juego sea funcional con
 * el uso de Interfaz Grafica
 * @superClass JPanel
 * @Author LeivSuaxy
 * @Date 15/10/2023 11:33
 */
public class Fondo extends JPanel {
    private Carta cartaAlmacenada;
    private ImageIcon imagen;
    private boolean usarImagen;

    /**
     * Constructor de un panel de Carta
     * @param carta Objeto que contendra el panel para su posterior dibujado y representacion de carta en el panel
     * @param a Ancho
     * @param l Largo
     */
    public Fondo(Carta carta, int a, int l){
        this.cartaAlmacenada = carta;
        this.setSize(a, l);

        if(carta != null){
            this.imagen = carta.getFrenteCarta();
            usarImagen = true;
        } else {
            this.imagen = null;
            usarImagen = false;
        }
    }

    /**
     * Constructor de un panel de carta con imagen Predefinida (Usado para cartas viradas)
     * @param imagen Imagen (Preferiblemente la del fondo de una carta)
     * @param a Ancho
     * @param l Largo
     */
    public Fondo(ImageIcon imagen, int a, int l){
        this.imagen = imagen;
        this.setSize(a, l);

        if(imagen != null){
            this.imagen = imagen;
            usarImagen = true;
        }else{
            this.imagen = null;
            usarImagen = false;
        }
    }

    /**
     * Metodo Paint para dibujar una carta
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g){
        Dimension tamagno = getSize();
        if (usarImagen) {
            ImageIcon imagenFondo = new ImageIcon((imagen).getImage());
            g.drawImage(imagenFondo.getImage(), 0, 0, tamagno.width, tamagno.height, null);
        } else {
            g.setColor(new Color(1, 86, 0));
            g.fillRect(0, 0, tamagno.width, tamagno.height);
        }
        setOpaque(false);
        super.paintComponent(g);
    }

    /**
     * Metodo para establecer una imagen a la carta
     * @param nuevaImagen Aqui se pone la direccion de una imagen que desee insertarle al panel
     */
    public void setImagen(ImageIcon nuevaImagen) {
        this.imagen = nuevaImagen;
        this.usarImagen = true;
        repaint();
    }

    /**
     * Metodo para en caso de no existir carta o desear pintar del mismo color del fondo
     */
    public void setFondoVerde() {
        this.cartaAlmacenada = null;
        this.imagen = null;
        this.usarImagen = false;
        repaint();
    }

    /**
     * Metodo para asignarle una carta al panel
     * @param cartaAlmacenada Carta que se le asignara al panel
     */
    public void setCarta(Carta cartaAlmacenada){
        if(cartaAlmacenada != null) {
            this.cartaAlmacenada = cartaAlmacenada;
            this.imagen = cartaAlmacenada.getFrenteCarta();
            this.usarImagen = true;
            repaint();
        } else {
            this.cartaAlmacenada = null;
            this.imagen = null;
            this.usarImagen = false;
            repaint();
        }
    }

    /**
     * Metodo para obtener la carta almacenada en el panel
     * @return Carta para su uso : Carta
     */
    public Carta getCartaAlmacenada(){
        return cartaAlmacenada;
    }
}
