package agent;

import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PokeGoal extends GoalTest {
    @Override
    public boolean isGoalState(AgentState agentState) {
        PokeUbicacion pokeDestino = new PokeUbicacion("BuenosAires",null,false);

        return ((PokeAgentState) agentState).getPokeUbicacion().equals(pokeDestino);

    }
}
