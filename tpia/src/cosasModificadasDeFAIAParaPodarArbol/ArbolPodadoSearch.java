package cosasModificadasDeFAIAParaPodarArbol;

import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.solver.search.Strategy;

import java.util.Collection;
import java.util.Vector;

public class ArbolPodadoSearch extends Strategy {
    public ArbolPodadoSearch() {
    }

    public void addNodesToExpand(Vector<NTree> nodes) {
        Vector<NTreeConPoda> treesConPoda = new Vector<>();
        for(NTree nTree : nodes){
            treesConPoda.add((NTreeConPoda) nTree);
        }
        //Add the nodes at the bottom of the list of nodes to expand
        for (NTreeConPoda nt : treesConPoda) {
            if(nt.getParent() != null){
                nt.setCost(nt.getParent().getCost());
            }else{
                nt.setCost(1);
            }
        }
        nodesToExpand.addAll(nodes);
    }

    public void addNodeToExpand(NTree node) {
        //Add the node at the top of the list of nodes to expand
        node.setCost(node.getParent().getCost());
        nodesToExpand.add(node);
    }

    @Override
    public String getStrategyName() {
        return "Depth First con poda de arbol";
    }

}
