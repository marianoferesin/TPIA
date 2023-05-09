import agent.PokeAgent;
import agent.PokeAgentPodador;
import environment.PokeEnvironment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
public class Main {
    public static void main(String[] args) {
        PokeEnvironment pokeEnvironment = new PokeEnvironment();
        PokeAgent pokeAgent = new PokeAgent();
        SearchBasedAgentSimulator searchBasedAgentSimulator = new SearchBasedAgentSimulator(pokeEnvironment,pokeAgent);
        searchBasedAgentSimulator.start();
        //TODO pal futuro
        //JFrame frame = new PokeFrame();
    }
}