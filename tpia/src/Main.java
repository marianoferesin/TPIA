import agent.PokeAgent;
import environment.PokeEnvironment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class Main {
    public static void main(String[] args) {

        PokeEnvironment pokeEnvironment = new PokeEnvironment();
        System.out.println("HOLAAAAAAAA " + pokeEnvironment.getEnvironmentState().getUbicacionPokeLuchador());
        PokeAgent pokeAgent = new PokeAgent();
        SearchBasedAgentSimulator searchBasedAgentSimulator = new SearchBasedAgentSimulator(pokeEnvironment,pokeAgent);
        searchBasedAgentSimulator.start();
    }
}
