package cosasModificadasDeFAIAParaPodarArbol;

import agent.PokeAgentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.solver.search.Strategy;
import java.util.Vector;

public class NTreeConPoda extends NTree{
    public NTreeConPoda(){};
    public NTreeConPoda(NTree firstNode, SearchAction searchAction, PokeAgentState ast, int nodeIdx) {
        this.deep = firstNode.getDeep() + 1;
        this.parent = firstNode;
        this.action = searchAction;
        this.sons = new Vector<>();
        this.agentState = ast;
        this.executionOrder = nodeIdx;
    }
    public void rollBack(Strategy strategy){
        if(this.sons.size() == 0){
            if(!(((PokeAgentState) agentState).getPokeUbicacion().isBoss())){
                if(parent != null){
                    parent.getSons().remove(this);
                    if(parent.getParent() != null){
                        ((NTreeConPoda) parent).rollBack(strategy);
                    }
                }
            }
        }
    }
    public void rollBack(Strategy strategy,boolean llamadaExterna){
        if(strategy.getClass() == DepthFirstSearch.class){
            rollBack(strategy);
        }else if(strategy.getClass() == BusquedaInformadaPrueba.class){
                if(this.getSons().isEmpty()){
                    if(parent != null && this.getCost() == 0){
                        parent.getSons().remove(this);
                        if(/* parent.getParent() != null && */ llamadaExterna)((NTreeConPoda) parent).rollBack(strategy,true);
                    }
                }else{
                    for(int i = 0; i < sons.size();i++){
                        ((NTreeConPoda) sons.get(i)).rollBack(strategy,false);
                    }
                    if(sons.isEmpty())rollBack(strategy,true);
                    /*if(allSonCostZero() && parent != null){
                        for(int i = 0; i < sons.size();i++){
                            if(sons.get(i).getCost() == 0 && !sons.get(i).getSons().isEmpty()){
                                NTreeConPoda grandSon = null;
                                for(int j = 0; j < sons.get(i).getSons().size();j++){
                                    grandSon = (NTreeConPoda) sons.get(i).getSons().get(j);
                                    if(grandSon.allSonCostZero()){
                                        grandSon.parent.getSons().remove(grandSon);
                                    }
                                }
                                ((NTreeConPoda) sons.get(i)).rollBack(strategy,false);
                            }
                        }
                        parent.getSons().remove(this);
                        ((NTreeConPoda) parent).rollBack(strategy,false);
                    }

                     */
                }
        }else{System.out.println("El metodo no soporta esta estrategia de busqueda");}
    }
    public boolean allSonCostZero(){
        int sonCostZero = 0;
        for(NTree son : sons){
            if(son.getCost() == 0){
                sonCostZero++;
            }
        }
        return sonCostZero == sons.size();
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
        str = "nodo" + var10000 + "[label=\"{EO: " + this.executionOrder + "|cost: " + this.cost + "|A: " + ((this.getAction() == null) ? "Sin accion" :this.getAction());
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

