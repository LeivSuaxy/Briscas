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
        File Archivo = new File("archivo.txt");
        FileInputStream ArchivoGuardado = null;
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

            nombre = JOptionPane.showInputDialog("Digite su nombre");

            java.awt.EventQueue.invokeLater(() -> new VentanaP((int) screenSize.getWidth(), (int) screenSize.getHeight(), nombre));
        }
    }
}