package GUI;

import Backend.Juego;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Programa main para ejecutar el juego
 * @Author LeivSuaxy
 * @Date 15/10/2023 11:40
 */
public class Test {
    public static void main(String[] args) {
        boolean cargar = false;
        String nombre;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        File Archivo = new File("guardado.dat");
        ObjectInputStream object;
        Juego juegoCarga;

        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splash.dispose();

        if(Archivo.exists()){
            int opcion = JOptionPane.showConfirmDialog(null, "Tiene una partida guardada desea cargala?");
            if(opcion == JOptionPane.YES_OPTION)
                cargar = true;
        }

        if(cargar){
        try{
            object = new ObjectInputStream(new FileInputStream(Archivo));
            juegoCarga = (Juego) object.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

            java.awt.EventQueue.invokeLater(() -> new VentanaP((int) screenSize.getWidth(), (int) screenSize.getHeight(), juegoCarga));
        } else {

            nombre = JOptionPane.showInputDialog("Digite su nombre");

            java.awt.EventQueue.invokeLater(() -> new VentanaP((int) screenSize.getWidth(), (int) screenSize.getHeight(), nombre));
        }
    }
}