package agent;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PokeGoal extends GoalTest {
    @Override
    public boolean isGoalState(AgentState agentState) {
        return false;
    }
}
