package agent;

import actions.GoToBuenosAires;
import actions.GoToTierraDelFuego;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import java.util.Vector;

public class PokeAgent extends SearchBasedAgent {
    private Vector<SearchAction> actions;
    public PokeAgent(){
        PokeGoal pokeGoal = new PokeGoal();
        PokeAgentState pokeAgentState = new PokeAgentState();
        pokeAgentState.initState();
        this.setAgentState(pokeAgentState);
        actions = initActions();
        Problem problem = new Problem(pokeGoal,pokeAgentState,actions);
        this.setProblem(problem);
    }
    private Vector<SearchAction> initActions(){
        Vector<SearchAction> actionsList = new Vector<>();
        actionsList.add(new GoToBuenosAires());
        actionsList.add(new GoToTierraDelFuego());
        return actionsList;
    }
    @Override
    public Action selectAction() {
        return null;
    }

    @Override
    public void see(Perception p) {

    }
}
