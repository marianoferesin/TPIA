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
    private BufferedImage enemigoImage;
    private BufferedImage bossImage;
    private BufferedImage agenteImage;
    private BufferedImage paradaImage;



    public DibujarMapa(PokeEnvironmentState e) {
        this.ambiente = e;

        backgroundImage= loadImage(".\\tpia\\src\\GUI\\Images\\smWorld.jpg");
        enemigoImage= loadImage(".\\tpia\\src\\GUI\\Images\\enemigo.png");
        bossImage= loadImage(".\\tpia\\src\\GUI\\Images\\boss.png");
        agenteImage= loadImage(".\\tpia\\src\\GUI\\Images\\agente.png");
        paradaImage= loadImage(".\\tpia\\src\\GUI\\Images\\parada.png");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }

        //dibujamos Aristas
        for (String lineas : ambiente.getMap().keySet()) {
            int xInicial = ambiente.getx(lineas, this.getWidth());
            int yInicial = ambiente.gety(lineas, this.getHeight());
            ArrayList<String> Destinos = new ArrayList<>();
            for (String a : ambiente.getMap().get(lineas)) {
                int xFinal = ambiente.getx(a, this.getWidth());
                int yFinal = ambiente.gety(a, this.getHeight());
                g2d.setColor(Color.blue);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawLine(xInicial, yInicial, xFinal, yFinal);
            }

        }


        //Dibujamos ubicaciones
        for (String key : ambiente.getPokeUbicaciones().keySet()) {
            PokeUbicacion ubi = ambiente.getPokeUbicaciones().get(key);
            int x = ambiente.getx(key, this.getWidth());
            int y = ambiente.gety(key, this.getHeight());
            g2d.setColor(Color.blue);
            g2d.setStroke(new BasicStroke(7));
            g2d.drawOval(x, y, 7, 7);

            Font font = new Font("Arial", Font.BOLD, 20);
            g2d.setFont(font);
            FontMetrics metrics = g2d.getFontMetrics(font);
            int textX = x - metrics.stringWidth(key) / 2;
            int textY = y - metrics.getHeight() / 2;
            // Dibujar sombra
            g2d.setColor(Color.white);
           // g2d.fillRect(textX, textY - 20, metrics.stringWidth(key), metrics.getHeight());
            g2d.drawString(key, textX-2, textY-2);

            // Dibujar texto

            g2d.setColor(Color.black);
            g2d.drawString(key, textX, textY);

            // Dibujar Enemigos
            if (ubi.tieneEnemigo() && (enemigoImage != null)) {
                if (ubi.isBoss()){
                    g.drawImage(bossImage, x-20, y+5, 60, 60, null);
                }else{
                    g.drawImage(enemigoImage, x-20, y+5, 60, 60, null);
                }
            }else if ( ubi.esPokeparada()){
                g.drawImage(paradaImage, x-20, y-5, 40, 40, null);
            }
        }

        // Dibuja Agente
        PokeUbicacion agente = ambiente.getUbicacionPokeLuchador();
        int x = ambiente.getx(agente.getNombre(), this.getWidth());
        int y = ambiente.gety(agente.getNombre(), this.getHeight());
        g.drawImage(agenteImage, x, y, 60, 60, null);


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

    private BufferedImage loadImage(String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return img;
    }

}
