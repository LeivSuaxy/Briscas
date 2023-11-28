package GUI;

/**
 * @Author LeivSuaxy
 * @Date 26/11/2023 11:59
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SplashScreen extends JWindow {
    private BufferedImage fondo;

    public SplashScreen() {
        try {
            fondo = ImageIO.read(new File("src/Imagenes/Splash.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, 1024, 768, this);
            }
        });
        setSize(1024, 768);
        setLocationRelativeTo(null);
    }
}