package agent;

import enemigos.PokeEnemigo;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PokeGoal extends GoalTest {
    @Override
    public boolean isGoalState(AgentState agentState) {
        return ((PokeAgentState) agentState).getPokeUbicacion().getNombre().equals("Boss") &&
                ((PokeAgentState) agentState).getPokeUbicacion().getPokeEnemigo().getEnergia() == 0;

    }
}
