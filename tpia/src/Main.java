import GUI.DibujarMapa;
import agent.PokeAgent;
import agent.PokeAgentPodador;
import environment.PokeEnvironment;
import environment.PokeEnvironmentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import modificacionesFaia.Search.StaticEnvironmentList;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PokeEnvironment pokeEnvironment = new PokeEnvironment();
        PokeAgent pokeAgent = new PokeAgent();

        //DibujarMapa dibujarMapa = lanzarGraficos((PokeEnvironmentState) pokeEnvironment.getEnvironmentState().clone(),null);

        SearchBasedAgentSimulator searchBasedAgentSimulator = new SearchBasedAgentSimulator(pokeEnvironment,pokeAgent);
        searchBasedAgentSimulator.start();
        /*
        for (int i = 0; i < StaticEnvironmentList.pokeEnvironmentsStates.size();i++){
            Thread.sleep(2500);
            dibujarMapa.setAmbiente(StaticEnvironmentList.pokeEnvironmentsStates.get(i));
            dibujarMapa.setAction(StaticEnvironmentList.actions.get(i).toString());
            dibujarMapa.updateCoords();
        }
        PokeEnvironmentState aux = StaticEnvironmentList.pokeEnvironmentsStates.get(StaticEnvironmentList.pokeEnvironmentsStates.size()-1);
        if(aux.isAgenteConVida()){
            dibujarMapa.setAmbiente(aux);
            dibujarMapa.setAction("Pikachu WINS");
            dibujarMapa.updateCoords();
        }else{
            dibujarMapa.setAmbiente(aux);
            dibujarMapa.setAction("Pikachu DEAD");
            dibujarMapa.updateCoords();
        }
    }

    public static DibujarMapa lanzarGraficos(PokeEnvironmentState e, String searchAction){
        JFrame frame = new JFrame("DibujarMapa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DibujarMapa mapaPanel = new DibujarMapa(e,searchAction);
        frame.add(mapaPanel);
        frame.setSize(1400, 1000);
        frame.setVisible(true);
        mapaPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                mapaPanel.updateCoords();
            }
        });
        return mapaPanel;

         */
    }



}