import agent.PokeAgent;
import agent.PokeAgentPodador;
import environment.PokeEnvironment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import modificacionesFaia.Simulation.SearchBasedAgentSimulatorGUI;

public class Main {
    public static void main(String[] args) {
        PokeEnvironment pokeEnvironment = new PokeEnvironment();
        PokeAgent pokeAgent = new PokeAgentPodador();
        SearchBasedAgentSimulatorGUI searchBasedAgentSimulator = new SearchBasedAgentSimulatorGUI(pokeEnvironment,pokeAgent);
        searchBasedAgentSimulator.start();
        //TODO pal futuro
        //JFrame frame = new PokeFrame();
    }
}