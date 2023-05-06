package GUI;

import GUI.PokePanel;

import javax.swing.*;

public class PokeFrame extends JFrame {
    private JPanel pokePanel;
    public PokeFrame(){
        super("Simulation");
        pokePanel = new PokePanel();
        this.setContentPane(pokePanel);
        this.setSize(2522/2,1316/2);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
