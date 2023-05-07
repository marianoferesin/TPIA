package actions;

import agent.PokeAgentState;
import environment.PokeEnvironmentState;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;

public class AtaqueEspecial1 extends SearchAction {

    Double multiplicador = 1.2;



    //Actualiza el estado del agente
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PokeAgentState agentState = (PokeAgentState) s;
        PokeUbicacion ubi = agentState.getPokeUbicacion();
        if ( agentState.AtaqueEspecial1Enabled() && ubi.tieneEnemigo() && (multiplicador * agentState.getPokeEnergia() > agentState.getPokeUbicacion().getPokeEnemigo().getEnergia()) ) {
            int energiaEnemigo = agentState.getPokeUbicacion().getPokeEnemigo().getEnergia();
            agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
            agentState.setPokeEnergia( multiplicador * agentState.getPokeEnergia() - energiaEnemigo + (multiplicador * energiaEnemigo));
            agentState.verificarPoderesEspeciales();
            return agentState;
        }
        return null;
    }


    // Actualiza el estado del agente y del ambiente
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        PokeAgentState agentState = (PokeAgentState) ast;
        PokeEnvironmentState enviromState = (PokeEnvironmentState) est;
        PokeUbicacion ubi = agentState.getPokeUbicacion();
        if ( agentState.AtaqueEspecial1Enabled() && ubi.tieneEnemigo() && (multiplicador * agentState.getPokeEnergia() > agentState.getPokeUbicacion().getPokeEnemigo().getEnergia()) ) {
            int energiaEnemigo = agentState.getPokeUbicacion().getPokeEnemigo().getEnergia();
            (enviromState.getPokeUbicaciones().get(ubi.getNombre())).getPokeEnemigo().setEnergia(0);
            agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
            agentState.setPokeEnergia( multiplicador * agentState.getPokeEnergia() - energiaEnemigo + (multiplicador * energiaEnemigo));
            agentState.verificarPoderesEspeciales();
            return enviromState;
        }
        return null;
    }

    @Override
    public String toString() {
        return "AtaqueEspecial1";
    }


    @Override
    public Double getCost() { return 1.0; }
}
