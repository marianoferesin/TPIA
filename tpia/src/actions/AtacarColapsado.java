package actions;

import agent.PokeAgentState;
import environment.PokeEnvironmentState;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.util.Objects;

public class AtacarColapsado extends SearchAction {
    //Actualiza el estado del agente
    String tipo = "";
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PokeAgentState agentState = (PokeAgentState) s;
        PokeUbicacion ubi = agentState.getPokeUbicacion();
        int energiaEnemigo = agentState.getPokeUbicacion().getPokeEnemigo().getEnergia();
        if(ubi.tieneEnemigo()){
            //Ataque normal...
            if (energiaEnemigo <= agentState.getPokeEnergia()){
                agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
                agentState.setPokeEnergia(agentState.getPokeEnergia() - energiaEnemigo + (0.2* energiaEnemigo));
                agentState.verificarPoderesEspeciales();
                tipo = "Normal";
                return agentState;
            }
            //Ataques especial1
            else if(agentState.ataqueEspecial1Enabled() && posibilidadGanar1(agentState)){
                agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
                agentState.setPokeEnergia( 1.2 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                agentState.verificarPoderesEspeciales();
                agentState.setCoolDown1();
                tipo = "Especial Tipo 1";
                return agentState;
            }
            else if(agentState.ataqueEspecial2Enabled() && posibilidadGanar2(agentState)){
                agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
                agentState.setPokeEnergia( 1.3 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                agentState.verificarPoderesEspeciales();
                agentState.setCoolDown2();
                tipo = "Especial Tipo 2";
                return agentState;
            } else if (agentState.ataqueEspecial3Enabled() && posibilidadGanar3(agentState)){
                agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
                agentState.setPokeEnergia( 1.5 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                agentState.verificarPoderesEspeciales();
                agentState.setCoolDown3();
                tipo = "Especial Tipo 3";
                return agentState;
            }
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
        if(ubi.tieneEnemigo()){
            //Ataque normal...
            if (energiaEnemigo <= agentState.getPokeEnergia()){
                Double energia = agentState.getPokeEnergia();
                (pokeEnvironmentState.getPokeUbicaciones().get(ubi.getNombre())).getPokeEnemigo().setEnergia(0);
                agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
                agentState.setPokeEnergia(agentState.getPokeEnergia() - energiaEnemigo + (0.2* energiaEnemigo));
                agentState.verificarPoderesEspeciales();
                pokeEnvironmentState.setAgenteConVida(agentState.getPokeEnergia() > 0);
                System.out.println("Ataca con "+ energia +" y consigue:" + agentState.getPokeEnergia());
                return pokeEnvironmentState;
            }
            //Ataques especial1
            else if(agentState.ataqueEspecial1Enabled() && posibilidadGanar1(agentState)){
                System.out.println("Realice ataque especial 1.....");
                Double energia = agentState.getPokeEnergia();
                (pokeEnvironmentState.getPokeUbicaciones().get(ubi.getNombre())).getPokeEnemigo().setEnergia(0);
                agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
                agentState.setPokeEnergia( 1.2 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                agentState.verificarPoderesEspeciales();
                agentState.setCoolDown1();
                pokeEnvironmentState.setAgenteConVida(agentState.getPokeEnergia() > 0);
                System.out.println("Ataca con "+ energia +" y consigue:" + agentState.getPokeEnergia() + "...ataque especial 1");
                return pokeEnvironmentState;
            }
            else if(agentState.ataqueEspecial2Enabled() && posibilidadGanar2(agentState)){
                System.out.println("Realice ataque especial 2.....");
                Double energia = agentState.getPokeEnergia();
                (pokeEnvironmentState.getPokeUbicaciones().get(ubi.getNombre())).getPokeEnemigo().setEnergia(0);
                agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
                agentState.setPokeEnergia( 1.3 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                agentState.verificarPoderesEspeciales();
                agentState.setCoolDown2();                pokeEnvironmentState.setAgenteConVida(agentState.getPokeEnergia() > 0);
                System.out.println("Ataca con "+ energia +" y consigue:" + agentState.getPokeEnergia() + "...ataque especial 2");
                return pokeEnvironmentState;
            } else if (agentState.ataqueEspecial3Enabled() && posibilidadGanar3(agentState)){
                System.out.println("Realice ataque especial 3.....");
                Double energia = agentState.getPokeEnergia();
                (pokeEnvironmentState.getPokeUbicaciones().get(ubi.getNombre())).getPokeEnemigo().setEnergia(0);
                agentState.getPokeUbicacion().getPokeEnemigo().setEnergia(0);
                agentState.setPokeEnergia( 1.5 * agentState.getPokeEnergia() - energiaEnemigo + (0.2 * energiaEnemigo));
                agentState.verificarPoderesEspeciales();
                agentState.setCoolDown3();
                System.out.println("Ataca con "+ energia +" y consigue:" + agentState.getPokeEnergia() + "...ataque especial 3");
                return pokeEnvironmentState;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "AtacarColapsado";
    }
    @Override
    public Double getCost() {
        return 3.0;
    }

    private boolean posibilidadGanar1(PokeAgentState pokeAgentState){
        boolean rtn = false;
        int energiaEnemigo = pokeAgentState.getPokeUbicacion().getPokeEnemigo().getEnergia();
        if (1.2 * pokeAgentState.getPokeEnergia() > energiaEnemigo)rtn=true;
        return rtn;
    }
    private boolean posibilidadGanar2(PokeAgentState pokeAgentState){
        boolean rtn = false;
        int energiaEnemigo = pokeAgentState.getPokeUbicacion().getPokeEnemigo().getEnergia();
        if (1.3 * pokeAgentState.getPokeEnergia() > energiaEnemigo)rtn=true;
        return rtn;
    }
    private boolean posibilidadGanar3(PokeAgentState pokeAgentState){
        boolean rtn = false;
        int energiaEnemigo = pokeAgentState.getPokeUbicacion().getPokeEnemigo().getEnergia();
        if (1.5 * pokeAgentState.getPokeEnergia() > energiaEnemigo)rtn=true;
        return rtn;
    }
    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        return this.getClass() == o.getClass();
    }

}
