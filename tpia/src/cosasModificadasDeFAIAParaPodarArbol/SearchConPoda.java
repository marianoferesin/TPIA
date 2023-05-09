package cosasModificadasDeFAIAParaPodarArbol;

import agent.PokeAgentState;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.solver.search.Strategy;

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

        // This iteration will occur while nodesToExpand have nodes and the actual node is not a goal node.-
        while (searchStrategy.getNodesToExpandSize() > 0 & !goal) {
            // This is the first node of the node's queue that will be expanded
            NTree firstNode = searchStrategy.getNode();

            //System.out.println("Profundidad: " + firstNode.getDeep());

            // If the actual node is a goal node then the search must finish.-
            if (goalTest.isGoalState(firstNode.getAgentState())) {
                goal = true;
                goalNode = (NTreeConPoda) firstNode;
            } else {	// If the actual node is not a goal node then it must be expanded.-

                // Every item in the action list represents a possible son for the actual node.-
                for (SearchAction action : actionList) {
                    // The state of the selected node must be cloned to assure consistence.-
                    PokeAgentState ast = (PokeAgentState) firstNode.getAgentState().clone();
                    // This is the action that can generate a new node.-
                    ast = (PokeAgentState) action.execute(ast);

                    if (ast != null && !ast.isDead()) {// If the action was correctly executed.-
                        NTree n = new NTreeConPoda(firstNode, action, ast, nodeIdx);
                        // If the node is not repeated in his search's tree branch
                        // then it can be added to the end of the branch.-
                        if (!existsNode(n, n.getParent())) {
                            //FORZAR LA POKEPARADA A 0.
                            firstNode.addSon(n);
                            nodeIdx++;
                            //System.out.println("Nodo nro: " + nodeIdx);
                        }
                    }
                }
                // The nodes are added to the queue of "nodes to expand",
                // so they can be expanded in the next cycles.-
                //if(firstNode.getSons().size() != 0){
                    searchStrategy.addNodesToExpand(firstNode.getSons());
                //}
                if(firstNode.getSons().size() == 0 && nodeIdx > 1) {
                    ((NTreeConPoda) firstNode).rollBack();
                }else{
                    searchStrategy.addNodesToExpand(firstNode.getSons());
                }
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

    private boolean existsNode(NTree node, NTree parent) {
        NTree p = (NTree) parent;//.clone();

        // This is an iteration through the node's parent (and ancestors) looking for a repeated node
        // in the same branch of the Search Tree.-
        while (p != null) {
            // If node already exists in the actual branch then the function return true.-
            if (node.equals(p)) {
                return true;
            }
            p = (NTree) p.getParent();
            //if (p!=null)
            //	p = (NTree)p.clone();
        }

        // At this point it's sure that the node does not exists in the branch of the Search Tree.-
        return false;
    }

    private Vector<NTree> getBestPath() {
        Vector<NTree> path = new Vector<NTree>();

        NTree node = (NTree) goalNode;//.clone();

        // This iteration will occur until the branch's top is reached.
        // The branch's top is not the root node of the tree. This is because there is no action
        // associated with the root node. So, the branch's top is a son of the root node.-
        while (node.getParent() != null) {
            // I insert every node at the first position, therefore I get the path from rootNode to lastNode
            path.insertElementAt(node, 0);
            node = (NTree) node.getParent(); //.clone();
        }

        return path;
    }
    private String toXml() {
        return tree.toXml();
    }
}
