package agent;

import FileReaders.FileReaders;
import actions.GoToBuenosAires;
import actions.GoToTierraDelFuego;
import actions.GotoX;
import enemigos.PokeEnemigo;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.Solve;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.Search;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        //get todas las ubicaciones
        ArrayList <String> ubicaciones = FileReaders.leerUbicaciones();
        for(String ubi: ubicaciones){
            actionsList.add(new GotoX(ubi));
        }
        return actionsList;
    }
    @Override
    public Action selectAction() {
        // Breath first strategy
        BreathFirstSearch searchStrategy = new BreathFirstSearch();
//        DepthFirstSearch searchStrategy = new DepthFirstSearch();

        Search searchSolver = new Search(searchStrategy);

        // Set the search tree to be written in an XML file
        searchSolver.setVisibleTree(Search.GRAPHVIZ_TREE);

        // Set the search solver
        this.setSolver(searchSolver);

        // Run the actions selection process
        Action selectedAction = null;
        try {
            selectedAction = this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(PokeAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return the selected action
        selectedAction = new GoToBuenosAires();

        return selectedAction;
    }
    @Override
    public void see(Perception perception) {
            this.getAgentState().updateState(perception);
    }
}
