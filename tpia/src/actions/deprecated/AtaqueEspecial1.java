package actions.deprecated;

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
        int energiaEnemigo = agentState.getPokeUbicacion().getPokeEnemigo().getEnergia();
        if (agentState.ataqueEspecial1Enabled() && ubi.tieneEnemigo() && (multiplicador * agentState.getPokeEnergia() > energiaEnemigo)) {
            agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
            agentState.setPokeEnergia( multiplicador * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
            agentState.verificarPoderesEspeciales();
            agentState.setCoolDown1();
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
        int energiaEnemigo = agentState.getPokeUbicacion().getPokeEnemigo().getEnergia();
        if ( agentState.ataqueEspecial1Enabled() && ubi.tieneEnemigo() && (multiplicador * agentState.getPokeEnergia() > energiaEnemigo) ) {
            (pokeEnvironmentState.getPokeUbicaciones().get(ubi.getNombre())).getPokeEnemigo().setEnergia(0);
            agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
            agentState.setPokeEnergia( multiplicador * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
            agentState.verificarPoderesEspeciales();
            pokeEnvironmentState.setAgenteConVida(agentState.getPokeEnergia() > 0);
            agentState.setCoolDown1();
            return pokeEnvironmentState;
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
