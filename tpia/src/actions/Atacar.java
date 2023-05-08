package actions;

import agent.PokeAgentState;
import environment.PokeEnvironmentState;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Atacar extends SearchAction {
    //Actualiza el estado del agente
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PokeAgentState agentState = (PokeAgentState) s;
        PokeUbicacion ubi = agentState.getPokeUbicacion();
        if (ubi.tieneEnemigo() && (agentState.getPokeUbicacion().getPokeEnemigo().getEnergia() < agentState.getPokeEnergia())) {
            int energiaEnemigo = agentState.getPokeUbicacion().getPokeEnemigo().getEnergia();
            agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
            agentState.setPokeEnergia(agentState.getPokeEnergia() - energiaEnemigo + (0.2* energiaEnemigo));
            agentState.verificarPoderesEspeciales();
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
        if (ubi.tieneEnemigo() && ( agentState.getPokeEnergia() > agentState.getPokeUbicacion().getPokeEnemigo().getEnergia() ) ) {
            int energiaEnemigo = agentState.getPokeUbicacion().getPokeEnemigo().getEnergia();
            Double energia = agentState.getPokeEnergia();
            (pokeEnvironmentState.getPokeUbicaciones().get(ubi.getNombre())).getPokeEnemigo().setEnergia(0);
            agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
            agentState.setPokeEnergia(agentState.getPokeEnergia() - energiaEnemigo + (0.2* energiaEnemigo));
            agentState.verificarPoderesEspeciales();
            pokeEnvironmentState.setAgenteConVida(agentState.getPokeEnergia() > 0);
            System.out.println("Ataca con "+ energia +" y consigue:" + agentState.getPokeEnergia());
            return pokeEnvironmentState;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Atacar";
    }


    @Override
    public Double getCost() { return 1.0; }
}
