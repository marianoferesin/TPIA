package agent;

import FileReaders.FileReaders;
import actions.*;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.*;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PokeAgent extends SearchBasedAgent {
    private Vector<SearchAction> actions;
    private PokeAgentState pokeAgentState = new PokeAgentState();
    private PokeGoal pokeGoal = new PokeGoal();
    private Problem problem;
    public PokeAgent(){
        pokeAgentState.initState();
        this.setAgentState(pokeAgentState);
        actions = initActions();
        problem = new Problem(pokeGoal,pokeAgentState,actions);
        this.setProblem(problem);
    }
    private Vector<SearchAction> initActions(){
        Vector<SearchAction> actionsList = new Vector<>();
        //Funcionamiento sin poda de arbol de busqueda.
        ArrayList <String> ubicaciones = FileReaders.leerUbicaciones();
        for(String ubi: ubicaciones){
            actionsList.add(new GoToX(ubi));
        }
        actionsList.add(new Atacar());
        //actionsList.add(new RecargarEnergia());
        actionsList.add(new AtaqueEspecial1());
        actionsList.add(new AtaqueEspecial2());
        actionsList.add(new AtaqueEspecial3());

        //Arbol podado.
        /*PokeUbicacion ubicacion = pokeAgentState.getPokeUbicacion();
        ArrayList<String> adyacentes = pokeAgentState.getMap().get(ubicacion.getNombre());
        for (String adyacente : adyacentes) {
            actionsList.add(new GoToX(adyacente));
        }*/
        //actionsList.add(new GoToX("Peru"));
        //actionsList.add(new GoToX("BuenosAires"));

        return actionsList;
    }
    @Override
    public Action selectAction() {

        BreathFirstSearch searchStrategy = new BreathFirstSearch();
        //DepthFirstSearch searchStrategy = new DepthFirstSearch();
        //UniformCostSearch searchStrategy = new UniformCostSearch(new DummyStepCostFunction());
        //InformedSearchStrategy searchStrategy = new GreedySearch(new DummyEstimatedCostFunction());

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

        return selectedAction;
    }

    public void setPokeAgentState(PokeAgentState pokeAgentState) {
        this.pokeAgentState = pokeAgentState;
    }

    @Override
    public String toString(){
        return pokeAgentState.toString();
    }
    @Override
    public void see(Perception perception) {
            this.getAgentState().updateState(perception);
    }
}
