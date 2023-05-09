package cosasModificadasDeFAIAParaPodarArbol;

import agent.PokeAgentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.solver.search.NTree;
import java.util.Vector;

public class NTreeConPoda extends NTree{
    public NTreeConPoda(){};
    public NTreeConPoda(NTree firstNode, SearchAction searchAction, PokeAgentState ast, int nodeIdx) {
        this.deep = firstNode.getDeep() + 1;
        this.parent = firstNode;
        this.action = searchAction;
        this.sons = new Vector();
        this.agentState = ast;
        this.executionOrder = nodeIdx;
    }
    public void rollBack(){
        if(this.sons.size() == 0){
            if(!(((PokeAgentState) agentState).getPokeUbicacion().isBoss())){
                if(parent != null){
                    parent.getSons().remove(this);
                    if(parent.getParent() != null){
                    ((NTreeConPoda) parent).rollBack();
                    }
                }
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        return this.agentState.equals(((NTreeConPoda)obj).getAgentState());
    }

    @Override
    public String toString() {
        return "Id= " + this.executionOrder + "  Action= " + this.action + " ";
    }
    @Override
    public String toGraphviz() {
        String str = "";
        int var10000 = this.executionOrder;
        str = "nodo" + var10000 + "[label=\"{EO: " + this.executionOrder + "|cost: " + this.cost + "|A: " + this.getAction();
        if (this.getParent() != null && this.getParent().getParent() != null) {
            str = str + "|" + this.getParent().getAgentState().toString().replace(",", "").replace("[", "").replace("]", "").replace(" ", "\\n");
        }

        str = str + "}\"]";
        str = str + "\n";

        for(int i = 0; i < this.getSons().size(); ++i) {
            str = str + ((NTree)this.sons.elementAt(i)).toGraphviz();
            str = str + "nodo" + this.executionOrder + " -> nodo" + ((NTree)this.sons.elementAt(i)).getExecutionOrder() + ";\n";
        }

        str = str + "\n";
        return str;
    }
}

