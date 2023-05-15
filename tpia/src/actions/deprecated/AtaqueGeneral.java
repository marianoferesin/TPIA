package actions.deprecated;

import agent.PokeAgentState;
import environment.PokeEnvironmentState;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class AtaqueGeneral extends SearchAction {
    //Actualiza el estado del agente

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PokeAgentState agentState = (PokeAgentState) s;
        PokeUbicacion ubi = agentState.getPokeUbicacion();
        int energiaEnemigo = agentState.getPokeUbicacion().getPokeEnemigo().getEnergia();
        boolean hizoAtaques=false;
        if (ubi.tieneEnemigo()){
            if (agentState.ataqueEspecial3Enabled() && (1.5 * agentState.getPokeEnergia() > agentState.getPokeUbicacion().getPokeEnemigo().getEnergia()) ){
                agentState.setPokeEnergia( 1.5 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                agentState.setCoolDown3();
                hizoAtaques=true;
            }else{
                if (agentState.ataqueEspecial2Enabled() && (1.3 * agentState.getPokeEnergia() > agentState.getPokeUbicacion().getPokeEnemigo().getEnergia()) ){
                    agentState.setPokeEnergia( 1.3 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                    agentState.setCoolDown2();
                    hizoAtaques=true;
                }else{
                    if (agentState.ataqueEspecial1Enabled() && (1.2 * agentState.getPokeEnergia() > agentState.getPokeUbicacion().getPokeEnemigo().getEnergia()) ){
                        agentState.setPokeEnergia( 1.2 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                        agentState.setCoolDown1();
                        hizoAtaques=true;
                    }else{
                        if ( agentState.getPokeEnergia() > agentState.getPokeUbicacion().getPokeEnemigo().getEnergia()) {
                            agentState.setPokeEnergia(  agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                            hizoAtaques=true;
                        }else{
                            hizoAtaques=false;
                        }
                    }
                }
            }
            if(hizoAtaques){
                agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
                agentState.verificarPoderesEspeciales();
                return agentState;
            }else{ return null; }
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
        Double energia = agentState.getPokeEnergia();
        String nivelAtaque = "";
        boolean hizoAtaques=false;
        if (ubi.tieneEnemigo()){
            if (agentState.ataqueEspecial3Enabled() && (1.5 * agentState.getPokeEnergia() > agentState.getPokeUbicacion().getPokeEnemigo().getEnergia()) ){
                agentState.setPokeEnergia( 1.5 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                agentState.setCoolDown3();
                hizoAtaques=true;
                nivelAtaque= "Especial3";
            }else{
                if (agentState.ataqueEspecial2Enabled() && (1.3 * agentState.getPokeEnergia() > agentState.getPokeUbicacion().getPokeEnemigo().getEnergia()) ){
                    agentState.setPokeEnergia( 1.3 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                    agentState.setCoolDown2();
                    hizoAtaques=true;
                    nivelAtaque= "Especial2";
                }else{
                    if (agentState.ataqueEspecial1Enabled() && (1.2 * agentState.getPokeEnergia() > agentState.getPokeUbicacion().getPokeEnemigo().getEnergia()) ){
                        agentState.setPokeEnergia( 1.2 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                        agentState.setCoolDown1();
                        hizoAtaques=true;
                        nivelAtaque= "Especial1";
                    }else{
                        if ( agentState.getPokeEnergia() > agentState.getPokeUbicacion().getPokeEnemigo().getEnergia()) {
                            agentState.setPokeEnergia(agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                            hizoAtaques=true;
                            nivelAtaque= "Común";
                        }else{
                            hizoAtaques=false;
                        }
                    }
                }
            }
            if(hizoAtaques){
                agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
                (pokeEnvironmentState.getPokeUbicaciones().get(ubi.getNombre())).getPokeEnemigo().setEnergia(0);
                agentState.verificarPoderesEspeciales();
                pokeEnvironmentState.setAgenteConVida(agentState.getPokeEnergia() > 0);
                System.out.println("Ataque "+nivelAtaque+" con "+ energia +" y queda en " + agentState.getPokeEnergia());
                return pokeEnvironmentState;
            }else{ return null; }
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
