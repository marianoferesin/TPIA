package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class PokePanel extends JPanel {
    public PokePanel(){

    }
    @Override
    public void paint(Graphics g){
        String path = ".\\tpia\\src\\GUI\\Images\\Mapa.png";

        if(System.getProperty("os.name").equals("Mac OS X")){
            path = "tpia/src/GUI/Images/Mapa.png";
        }
        ImageIcon mapa = new ImageIcon(path);
        g.drawImage(mapa.getImage(),0,0,getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}
