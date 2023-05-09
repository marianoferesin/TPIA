package cosasModificadasDeFAIAParaPodarArbol;

import agent.PokeAgentState;
import enemigos.PokeEnemigo;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.solver.search.Strategy;

import java.util.Vector;

public class NTreeConPoda extends NTree{
    private String str = "";
    public NTreeConPoda(){};
    public NTreeConPoda(NTree firstNode, SearchAction searchAction, PokeAgentState ast, int nodeIdx) {
        this.deep = firstNode.getDeep() + 1;
        this.parent = (NTreeConPoda) firstNode;
        this.sons = new Vector();
        this.agentState = ast;
        this.executionOrder = 0;
    }
    public void rollBack(){
        if(this.sons.size() == 0){
            if(!(((PokeAgentState) agentState).getPokeUbicacion().isBoss())){
                if(parent != null){
                    System.out.println("Removi algo");
                    parent.getSons().remove(this);
                    if(parent.getParent() != null){
                    ((NTreeConPoda) parent).rollBack();
                    }
                }
            }
        }
    }
}

