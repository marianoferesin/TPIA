package actions.deprecated;

import agent.PokeAgentState;
import environment.PokeEnvironmentState;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;

/**
 * Actualmente no se usa, para no cogestionar el arbol.
 */
public class RecargarEnergia extends SearchAction {
    //Actualiza el estado del agente
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PokeAgentState agentState = (PokeAgentState) s;
        PokeUbicacion ubi = agentState.getPokeUbicacion();
        if (ubi.esPokeparada()){
            agentState.setPokeEnergia( ubi.getEnergiaPokeparada()+ agentState.getPokeEnergia() );
            agentState.verificarPoderesEspeciales();
            ubi.setEnergiaPokeparada(0);
            return agentState;
        }
        return null;
    }


    // Actualiza el estado del agente y del ambiente
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        PokeAgentState agentState = (PokeAgentState) ast;
        PokeEnvironmentState pokeEnvironmentState = (PokeEnvironmentState) est;
        PokeUbicacion ubi = agentState.getPokeUbicacion();
        if(ubi.esPokeparada()){
            agentState.setPokeEnergia(ubi.getEnergiaPokeparada()+ agentState.getPokeEnergia());
            agentState.verificarPoderesEspeciales();
            pokeEnvironmentState.getPokeUbicaciones().get(ubi.getNombre()).setEnergiaPokeparada(0);
            ubi.setEnergiaPokeparada(0);
            System.out.println("PokeParada ahora tengo: "+agentState.getPokeEnergia());
            return pokeEnvironmentState;
        }
        return null;

    }

    @Override
    public String toString() {
        return "RecargarEnergia";
    }


    @Override
    public Double getCost() { return 1.0; }
}

