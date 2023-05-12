package GUI;

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

    private CoordenadasUbicaciones mapa;

    private BufferedImage backgroundImage;

    public DibujarMapa() {
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



        mapa= new CoordenadasUbicaciones();

        //dibujamos Aristas
        for (String lineas : mapa.getAristas().keySet() ) {
            int xInicial = mapa.getx(lineas,this.getWidth());
            int yInicial = mapa.gety(lineas,this.getHeight());
            ArrayList<String> Destinos = new ArrayList<>();
            for(String a: mapa.getAristas().get(lineas)){
                int xFinal = mapa.getx(a,this.getWidth());
                int yFinal = mapa.gety(a,this.getHeight());
                g2d.setColor(Color.red);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(xInicial,yInicial,xFinal,yFinal);
            }

        }



        //Dibujamos ubicaciones
        for (String key : mapa.getMapUbicaciones().keySet() ) {
            int x = mapa.getx(key,this.getWidth());
            int y = mapa.gety(key,this.getHeight());
            g2d.setColor(Color.yellow);
            g2d.setStroke(new BasicStroke(7));
            g2d.drawOval(x,y,7,7);

            Font font = new Font("Arial", Font.PLAIN, 20);
            FontMetrics metrics = g2d.getFontMetrics(font);
            int textX = x - metrics.stringWidth(key)/2;
            int textY = y - metrics.getHeight()/2;
            // Dibujar sombra
            g2d.setFont(font);
            g2d.setColor(Color.black);
            g2d.drawString(key, textX + 3, textY + 3);
            // Dibujar texto
            g2d.setColor(Color.orange);
            g2d.drawString(key, textX, textY);
        }



    }
/*
    public void updateCoords() {
        int width = getWidth();
        int height = getHeight();

        xCoords[0] = width / 4;
        xCoords[1] = width / 2;
        xCoords[2] = (3 * width) / 4;
        xCoords[3] = width;

        yCoords[0] = height / 4;
        yCoords[1] = height / 2;
        yCoords[2] = height / 4;
        yCoords[3] = height / 2;

        repaint();
    }*/

    public static void main(String[] args) {
        JFrame frame = new JFrame("DibujarMapa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DibujarMapa mapaPanel = new DibujarMapa();
        frame.add(mapaPanel);
        frame.setSize(1400, 1000);
        frame.setVisible(true);

        mapaPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
              //  mapaPanel.updateCoords();
            }
        });
    }


}
