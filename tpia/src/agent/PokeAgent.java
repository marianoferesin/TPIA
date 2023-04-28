package agent;

import actions.GoToBuenosAires;
import actions.GoToTierraDelFuego;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;

import java.util.ArrayList;

public class PokeAgent extends SearchBasedAgent {
    private PokeGoal pokeGoal;
    private PokeAgentState pokeAgentState;
    private ArrayList<Action> actions;
    public PokeAgent(){
        this.pokeGoal = new PokeGoal();
        this.pokeAgentState = new PokeAgentState();
        this.pokeAgentState.initState();
        this.actions = initActions();
    }
    private ArrayList<Action> initActions(){
        ArrayList<Action> actionsList = new ArrayList<>();
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
