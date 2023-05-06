package actions;

import agent.PokeAgentState;
import environment.PokeEnvironmentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;

public class GotoX extends SearchAction {

    private String destino= "";

    public GotoX(String destino) {
        this.destino = destino;
    }

    //Actualiza el estado del agente
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        System.out.println("\u001B[43m" + "SearchBasedAgentState: "+ s);
        PokeAgentState agState = (PokeAgentState) s;
        String ubi = agState.getPokeUbicacion();
        ArrayList<String> adyacentes = agState.getMap().get(ubi);;
        for (int i = 0; i < adyacentes.size(); i++) {
            if (adyacentes.get(i).equals(destino)) {
                agState.setPokeUbicacion(destino);
                agState.huir();
                return agState;
            }
        }
        return null;
    }


    // Actualiza el estado del agente y del ambiente
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        PokeAgentState agState = (PokeAgentState) ast;
        PokeEnvironmentState pkState = (PokeEnvironmentState) est;
        String ubi = agState.getPokeUbicacion();
        ArrayList<String> adyacentes = agState.getMap().get(ubi);;
        for (int i = 0; i < adyacentes.size(); i++) {
            if (adyacentes.get(i).equals(destino)) {
                agState.setPokeUbicacion(destino);
                agState.huir();
                pkState.setUbicacionPokeLuchador(destino);
                return pkState;
            }
        }
        return null;

    }

    @Override
    public String toString() {
        return "GoTo"+destino;
    }


    @Override
    public Double getCost() { return 1.0; }
}
