package GUI;

import environment.PokeEnvironmentState;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.SearchAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class DibujarMapa extends JPanel implements ActionListener {

    private PokeEnvironmentState ambiente;
    private BufferedImage backgroundImage;
    private Image enemigoImage;
    private Image bossImage;
    private Image agenteImage;
    private Image paradaImage;
    private String action;

    public DibujarMapa(PokeEnvironmentState e, String searchAction) {
        this.ambiente = e;

        String MACOS_NAME = "Mac OS X";

        String pathBackground = ".\\tpia\\src\\GUI\\Images\\smWorld.jpg";
        if(System.getProperty("os.name").equals(MACOS_NAME))pathBackground = "tpia/src/GUI/Images/smWorld.jpg";
        backgroundImage= loadImage(pathBackground);

        String pathEnemigo = ".\\tpia\\src\\GUI\\Images\\enemigo.png";
        if(System.getProperty("os.name").equals(MACOS_NAME))pathEnemigo = "tpia/src/GUI/Images/enemigo.png";
        enemigoImage= hacerTransparente(loadImage(pathEnemigo));

        String pathBoss = ".\\tpia\\src\\GUI\\Images\\Boss.png";
        if(System.getProperty("os.name").equals(MACOS_NAME))pathBoss = "tpia/src/GUI/Images/Boss.png";
        bossImage= hacerTransparente(loadImage(pathBoss));

        String pathAgente = ".\\tpia\\src\\GUI\\Images\\agente.png";
        if(System.getProperty("os.name").equals(MACOS_NAME)) pathAgente = "tpia/src/GUI/Images/agente.png";
        agenteImage= hacerTransparente(loadImage(pathAgente));

        String pathParada = ".\\tpia\\src\\GUI\\Images\\parada.png";
        if(System.getProperty("os.name").equals(MACOS_NAME))pathParada = "tpia/src/GUI/Images/parada.png";
        paradaImage= hacerTransparente(loadImage(pathParada));

        this.action = searchAction;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }

        Font font = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(font);
        g2d.setColor(Color.black);
        String accion = "Accion retornada: " + this.action;
        if(this.action == null) accion = "Accion retornada: " + "Ninguna";
        else if(this.action.equals("Pikachu WINS")) accion = this.action;
        else if(this.action.equals("Pikachu DEAD")) accion = this.action;

        g2d.drawString(accion,50,50);

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

            font = new Font("Arial", Font.BOLD, 20);
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
            int alto = 70;
            int ancho = 70;

            if (ubi.tieneEnemigo() && (enemigoImage != null)) {
                if (ubi.isBoss()){
                    g.drawImage(bossImage, x-ancho/2, y-alto/4, ancho, alto, null);
                }else{
                    g.drawImage(enemigoImage, x-ancho/2, y-alto/4, ancho, alto, null);
                }
            }else if ( ubi.esPokeparada()){
                g.drawImage(paradaImage, x-ancho/6, y-alto/6, ancho/3, alto/3, null);
            }
        }

        // Dibuja Agente
        PokeUbicacion agente = ambiente.getUbicacionPokeLuchador();
        int x = ambiente.getx(agente.getNombre(), this.getWidth());
        int y = ambiente.gety(agente.getNombre(), this.getHeight());
        g.drawImage(agenteImage, x-40, y-40, 80, 80, null);

    }

    public void updateCoords() {
        int width = getWidth();
        int height = getHeight();
        repaint();
    }

    private BufferedImage loadImage(String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Cambia el modo de color de la imagen a TRANSLUCENT
        BufferedImage transparentImage = new BufferedImage( img.getWidth(), img.getHeight(), BufferedImage.TRANSLUCENT);
        transparentImage.createGraphics().drawImage(img, 0, 0, null);
        return transparentImage;
    }

    private Image hacerTransparente(BufferedImage image){
        // Aplica un filtro que convierte el color de fondo en transparente
        ImageFilter filter = new RGBImageFilter() {
            int transparentColor = Color.WHITE.getRGB() | 0xFF000000; // Color de fondo a convertir en transparente

            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == transparentColor) {
                    // El píxel tiene el mismo color que el color de fondo
                    return 0x00FFFFFF & rgb; // Convierte a transparente
                } else {
                    // El píxel tiene otro color
                    return rgb;
                }
            }
        };

        // Crea un objeto ImageProducer a partir de la imagen con el filtro aplicado
        ImageProducer producer = new FilteredImageSource(image.getSource(), filter);

        // Crea una imagen a partir del ImageProducer
        Image transparentImage = Toolkit.getDefaultToolkit().createImage(producer);
        return transparentImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void setAmbiente(PokeEnvironmentState pokeEnvironmentState){
        ambiente = pokeEnvironmentState;
    }
    public void setAction(String searchAction){
        action = searchAction;
    }
}