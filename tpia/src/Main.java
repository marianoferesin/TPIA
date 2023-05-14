import GUI.DibujarMapa;
import agent.PokeAgent;
import agent.PokeAgentPodador;
import environment.PokeEnvironment;
import environment.PokeEnvironmentState;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main {
    public static void main(String[] args) {
        PokeEnvironment pokeEnvironment = new PokeEnvironment();
        PokeAgent pokeAgent = new PokeAgent();
        lanzarGraficos(pokeEnvironment.getEnvironmentState());

        SearchBasedAgentSimulator searchBasedAgentSimulator = new SearchBasedAgentSimulator(pokeEnvironment,pokeAgent);
        searchBasedAgentSimulator.start();
    }

    public static void lanzarGraficos(PokeEnvironmentState e){
        JFrame frame = new JFrame("DibujarMapa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DibujarMapa mapaPanel = new DibujarMapa(e);
        frame.add(mapaPanel);
        frame.setSize(1400, 1000);
        frame.setVisible(true);
        mapaPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                mapaPanel.updateCoords();
            }
        });
    }

}