package GUI;

import environment.PokeEnvironmentState;
import environment.PokeUbicacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class DibujarMapa extends JPanel {


    private PokeEnvironmentState ambiente;
    private BufferedImage backgroundImage;

    public DibujarMapa(PokeEnvironmentState e) {
        this.ambiente = e;
        try {
            String path = ".\\tpia\\src\\GUI\\Images\\smWorld.jpg";
            backgroundImage = ImageIO.read(new File(path));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }

        Graphics2D g2d = (Graphics2D) g;


        //dibujamos Aristas
        for (String lineas : ambiente.getMap().keySet() ) {
            int xInicial = ambiente.getx(lineas,this.getWidth());
            int yInicial = ambiente.gety(lineas,this.getHeight());
            ArrayList<String> Destinos = new ArrayList<>();
            for(String a: ambiente.getMap().get(lineas)){
                int xFinal = ambiente.getx(a,this.getWidth());
                int yFinal = ambiente.gety(a,this.getHeight());
                g2d.setColor(Color.red);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(xInicial,yInicial,xFinal,yFinal);
            }

        }



        //Dibujamos ubicaciones
        for (String key : ambiente.getPokeUbicaciones().keySet() ) {
            int x = ambiente.getx(key,this.getWidth());
            int y = ambiente.gety(key,this.getHeight());
            g2d.setColor(Color.yellow);
            g2d.setStroke(new BasicStroke(7));
            g2d.drawOval(x,y,7,7);

            Font font = new Font("Arial", Font.BOLD, 20);
            g2d.setFont(font);
            FontMetrics metrics = g2d.getFontMetrics(font);
            int textX = x - metrics.stringWidth(key)/2;
            int textY = y - metrics.getHeight()/2;
            // Dibujar sombra
            g2d.setColor(Color.black);
            g2d.fillRect(textX,textY-20, metrics.stringWidth(key), metrics.getHeight());

            // Dibujar texto

            g2d.setColor(Color.orange);
            g2d.drawString(key, textX, textY);
        }



    }

    public void updateCoords() {
        int width = getWidth();
        int height = getHeight();
/*
        xCoords[0] = width / 4;
        xCoords[1] = width / 2;
        xCoords[2] = (3 * width) / 4;
        xCoords[3] = width;

        yCoords[0] = height / 4;
        yCoords[1] = height / 2;
        yCoords[2] = height / 4;
        yCoords[3] = height / 2;
*/
        repaint();
    }

}
