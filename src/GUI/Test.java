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
        String nombre;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        File Archivo = new File("archivo.txt");
        FileInputStream ArchivoGuardado = null;
        ObjectInputStream object;
        Juego juegoCarga;


        if(Archivo.exists()){
        try {
            ArchivoGuardado = new FileInputStream(Archivo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            object = new ObjectInputStream(ArchivoGuardado);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            try {
                juegoCarga = (Juego) object.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            java.awt.EventQueue.invokeLater(() -> new VentanaP((int) screenSize.getWidth(), (int) screenSize.getHeight(), juegoCarga));
        } else {

        /*SplashScreen splash = new SplashScreen();
        splash.setVisible(true);

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splash.dispose();
        */
            nombre = JOptionPane.showInputDialog("Digite su nombre");

            java.awt.EventQueue.invokeLater(() -> new VentanaP((int) screenSize.getWidth(), (int) screenSize.getHeight(), nombre));
        }
    }
}