package agent;

import modificacionesFaia.Search.BusquedaInformadaPrueba;
import modificacionesFaia.Search.SearchConPoda;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PokeAgentPodador extends PokeAgent{
    @Override
    public Action selectAction() {

        //BreathFirstSearch searchStrategy = new BreathFirstSearch();
        //DepthFirstSearch searchStrategy = new DepthFirstSearch();
        //ArbolPodadoSearch searchStrategy = new ArbolPodadoSearch();
        BusquedaInformadaPrueba searchStrategy = new BusquedaInformadaPrueba();
        //UniformCostSearch searchStrategy = new UniformCostSearch(new DummyStepCostFunction());
        //InformedSearchStrategy searchStrategy = new GreedySearch(new DummyEstimatedCostFunction());

        Search searchSolver = new SearchConPoda(searchStrategy);

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
}
