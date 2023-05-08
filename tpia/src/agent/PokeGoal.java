package agent;

import enemigos.PokeEnemigo;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PokeGoal extends GoalTest {
    @Override
    public boolean isGoalState(AgentState agentState) {
        PokeUbicacion pokeDestino = new PokeUbicacion("Boss",new PokeEnemigo(0),0);

        return ((PokeAgentState) agentState).getPokeUbicacion().equals(pokeDestino) && ((PokeAgentState) agentState).getPokeUbicacion().getPokeEnemigo().getEnergia() == 0;

    }
}
