package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Programa main para ejecutar el juego
 * @Author LeivSuaxy
 * @Date 15/10/2023 11:40
 */
public class Test {
    public static void main(String[] args) {
        String nombre;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splash.dispose();

        nombre = JOptionPane.showInputDialog("Digite su nombre");

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaP((int) screenSize.getWidth(), (int) screenSize.getHeight(), nombre);
            }
        });
    }
}
