package modificacionesFaia.Search;

import agent.PokeAgentState;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.solver.search.Strategy;
import frsf.cidisi.faia.util.LatexOutput;
import frsf.cidisi.faia.util.TreeMLWriter;
import modificacionesFaia.GraphPrint.GraphvizTreeModificado;
import modificacionesFaia.Search.NTreeConPoda;

import java.util.Vector;

public class SearchConPoda extends Search {
    private NTreeConPoda tree;
    private NTreeConPoda goalNode;
    private Strategy searchStrategy;
    private int visibleTree = Search.WHITHOUT_TREE;
    public SearchConPoda(Strategy strategy) {
        super(strategy);
        this.searchStrategy = strategy;
    }
    public Strategy getStrategy() {
        return searchStrategy;
    }
    public void setStrategy(Strategy s) {
        this.searchStrategy = s;
    }
    @Override
    public SearchAction solve(Object[] params) {
        Problem problem = (Problem) params[0];

        Vector<SearchAction> actionList = problem.getActions();
        SearchBasedAgentState agentState = problem.getAgentState();//.clone();
        GoalTest goalTest = problem.getGoalState();
        
        int nodeIdx = 1;

        tree = new NTreeConPoda();

        tree.setAgentState(agentState);

        searchStrategy.initNodesToExpandList(tree);

        boolean goal = false;

        int flagAntiBucle=0;

        while (searchStrategy.getNodesToExpandSize() > 0 & !goal) {
            NTree firstNode = searchStrategy.getNode();
            //TODO antibucles
            if(firstNode.getParent() != null && firstNode.getParent().getParent() != null){
                if(((PokeAgentState) firstNode.getAgentState()).getPokeUbicacion().getNombre().equals(((PokeAgentState) firstNode.getParent().getParent().getAgentState()).getPokeUbicacion().getNombre()) || firstNode.getAction().equals(firstNode.getParent().getParent().getAction())){
                flagAntiBucle = 1;
                }
            }

            if (goalTest.isGoalState(firstNode.getAgentState()) || flagAntiBucle == 1) {
                flagAntiBucle = 0;
                if(goalTest.isGoalState(firstNode.getAgentState())){
                    goal = true;
                    goalNode = (NTreeConPoda) firstNode;
                }
            } else {
                for (SearchAction action : actionList) {

                    PokeAgentState ast = (PokeAgentState) firstNode.getAgentState().clone();
                    ast = (PokeAgentState) action.execute(ast);
                    if (ast != null) {
                        NTreeConPoda n = new NTreeConPoda(firstNode, action, ast, nodeIdx);
                        if (!existsNode(n, (NTreeConPoda) n.getParent())) {
                            firstNode.addSon(n);
                            nodeIdx++;
                        }
                    }
                }
                searchStrategy.addNodesToExpand(firstNode.getSons());
                //if(firstNode.getSons().isEmpty())((NTreeConPoda) firstNode).rollBack(searchStrategy,true);

            }
        }
        if (goal && !getBestPath().isEmpty()) {
            // This variable store the branch's path where the node belongs.-
            Vector<NTree> path = getBestPath();
            // The first node of the branch has the action that must be executed by the agent.-
            return path.elementAt(0).getAction();
        }
        return null;
    }

    private boolean existsNode(NTreeConPoda node, NTreeConPoda parent) {
        NTree p = parent;
        while (p != null) {
            if (node.equals(p)) {
                return true;
            }
            p = (NTree) p.getParent();
        }
        return false;
    }

    private Vector<NTree> getBestPath() {
        Vector<NTree> path = new Vector<NTree>();
        NTree node = (NTree) goalNode;//.clone();
        while (node.getParent() != null) {
            path.insertElementAt(node, 0);
            node = (NTree) node.getParent(); //.clone();
        }
        return path;
    }
    private String toXml() {
        return tree.toXml();
    }
    @Override
    public void showSolution() {
        this.showTree();
    }
    @Override
    public void showTree() {
        switch (this.visibleTree) {
            case 2 -> LatexOutput.getInstance().printFile(this.tree, this.searchStrategy.getStrategyName());
            case 4 -> GraphvizTreeModificado.printFile(this.tree);
            case 5 -> TreeMLWriter.printFile(this.tree);
        }

    }
    @Override
    public void setVisibleTree(int visibleTree) {
        if (visibleTree == 2) {
            SimulatorEventNotifier.SubscribeEventHandler(EventType.SimulationFinished, LatexOutput.getInstance());
        }
        this.visibleTree = visibleTree;
    }
}
