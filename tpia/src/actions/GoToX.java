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
        PokeAgentState agentState = (PokeAgentState) s;
        PokeUbicacion ubi = agentState.getPokeUbicacion();
        ArrayList<String> adyacentes = agentState.getMap().get(ubi.getNombre());
        if (adyacentes.contains(destino)) {
            //Si hay enemigo huyo
            agentState.huir();
            //Si es pokeparada recargo
            agentState.recargar(ubi.getEnergiaPokeparada());
            agentState.verificarPoderesEspeciales();
            ubi.setEnergiaPokeparada(0);

            agentState.setPokeUbicacion(agentState.getPokeUbicaciones().get(destino));
            return agentState;
        }
        return null;
    }
    // Actualiza el ambiente
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        PokeAgentState agentState = (PokeAgentState) ast;
        PokeEnvironmentState pokeEnvironmentState = (PokeEnvironmentState) est;
        PokeUbicacion ubi = agentState.getPokeUbicacion();
        ArrayList<String> adyacentes = agentState.getMap().get(ubi.getNombre());
        if (adyacentes.contains(destino)){
            //Si hay enemigo huyo
            System.out.println(" ("+ ubi.getNombre() +" --> "+destino +") Antes de huir de  :" + agentState.getPokeEnergia());
            agentState.huir();
            System.out.println("("+ ubi.getNombre()+" --> "+destino+") Despues de huir :" + agentState.getPokeEnergia());
            //Si es pokeparada recargo
            agentState.recargar(ubi.getEnergiaPokeparada());
            agentState.verificarPoderesEspeciales();
            if(ubi.getEnergiaPokeparada() > 0) System.out.println("Recargue energia.............. en " + ubi.getNombre());
            ubi.setEnergiaPokeparada(0);

            agentState.setPokeUbicacion(agentState.getPokeUbicaciones().get(destino));
            pokeEnvironmentState.setUbicacionPokeLuchador(agentState.getPokeUbicaciones().get(destino));
            pokeEnvironmentState.setAgenteConVida(agentState.getPokeEnergia() > 0);
            return pokeEnvironmentState;
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
