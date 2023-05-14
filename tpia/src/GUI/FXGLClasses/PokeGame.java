package GUI.FXGLClasses;


import agent.PokeAgent;
import agent.PokeAgentPodador;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import environment.PokeEnvironment;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.GoalBasedAgent;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

import java.util.ArrayList;

public class PokeGame extends GameApplication {
    public static ArrayList<GoalBasedAgent> agentes = new ArrayList<>();
    public static ArrayList<Action> acciones = new ArrayList<>();
    public static boolean winFlag = false;
    @Override
    protected void initSettings(GameSettings gameSettings) {
        agentes = StaticTransport.agentes;
        acciones = StaticTransport.acciones;
        winFlag = StaticTransport.winFlag;

    }
    @Override
    protected void initGame() {
        //TODO ver que version de la libreria tiene el loco este, la mia no tiene las mismas clases.
    }

    public static void main(String[] args) {
        PokeEnvironment pokeEnvironment = new PokeEnvironment();
        PokeAgent pokeAgent = new PokeAgentPodador();
        SearchBasedAgentSimulator searchBasedAgentSimulator = new SearchBasedAgentSimulator(pokeEnvironment,pokeAgent);
        searchBasedAgentSimulator.start();

        launch(new String[]{});
    }
}
