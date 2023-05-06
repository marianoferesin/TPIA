package actions;

import agent.PokeAgentState;
import environment.PokeEnvironmentState;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;

public class GoToX extends SearchAction {

    private String destino= "";

    public GoToX(String destino) {
        this.destino = destino;
    }

    //Actualiza el estado del agente
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PokeAgentState agState = (PokeAgentState) s;
        PokeUbicacion ubi = agState.getPokeUbicacion();
        ArrayList<String> adyacentes = agState.getMap().get(ubi.getNombre());;
        System.out.println(adyacentes);
        if (adyacentes.contains(destino)) {
                agState.setPokeUbicacion(agState.getPokeUbicaciones().get(destino));
                agState.huir();
                return agState;
        }
        return null;
    }


    // Actualiza el estado del agente y del ambiente
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        PokeAgentState agState = (PokeAgentState) ast;
        PokeEnvironmentState pkState = (PokeEnvironmentState) est;
        PokeUbicacion ubi = agState.getPokeUbicacion();
        ArrayList<String> adyacentes = agState.getMap().get(ubi.getNombre());;
        for (int i = 0; i < adyacentes.size(); i++) {
            if (adyacentes.get(i).equals(destino)) {
                agState.setPokeUbicacion(agState.getPokeUbicaciones().get(destino));
                agState.huir();
                pkState.setUbicacionPokeLuchador(agState.getPokeUbicaciones().get(destino));
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
