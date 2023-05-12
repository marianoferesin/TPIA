package modificacionesFaia.Simulation;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.GoalBasedAgent;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.simulator.GoalBasedAgentSimulator;
import frsf.cidisi.faia.state.AgentState;
import java.util.Arrays;
import java.util.Vector;

public class SearchBasedAgentSimulatorGUI extends GoalBasedAgentSimulatorGUI {
    public SearchBasedAgentSimulatorGUI(Environment environment, Vector<Agent> agents) {
        super(environment, agents);
    }

    public SearchBasedAgentSimulatorGUI(Environment environment, Agent agent) {
        this(environment, new Vector(Arrays.asList(agent)));
    }

    public boolean agentSucceeded(Action actionReturned) {
        SearchBasedAgent sa = (SearchBasedAgent)this.getAgents().firstElement();
        Problem p = sa.getProblem();
        GoalTest gt = p.getGoalState();
        AgentState aSt = p.getAgentState();
        return gt.isGoalState(aSt);
    }

    public boolean agentFailed(Action actionReturned) {
        return this.environment.agentFailed(actionReturned);
    }

    public String getSimulatorName() {
        return "Search Based Simulator";
    }

    void showSolution() {
        GoalBasedAgent agent = (GoalBasedAgent)this.getAgents().firstElement();
        agent.getSolver().showSolution();
    }

    public void actionReturned(Agent agent, Action action) {
        this.updateState(action);
        this.showSolution();
    }
}
