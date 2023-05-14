package actions;

import agent.PokeAgentState;
import environment.PokeEnvironmentState;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import java.util.ArrayList;
import java.util.Objects;

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

        //TODO modificacion de prueba, si estoy en el boss no me muevo
        if(((PokeAgentState) s).getPokeUbicacion().isBoss() && posibilidadGanar((PokeAgentState) s)) return null;

        if (adyacentes.contains(destino)) {

            if(ubi.getNombre().equals(destino)) return null;
            //Si hay enemigo huyo
            agentState.huir();
            //Si es pokeparada recargo
            if(ubi.esPokeparada()){
                agentState.recargar(ubi.getEnergiaPokeparada());
                agentState.verificarPoderesEspeciales();
                ubi.setEnergiaPokeparada(0);
            }
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
        return "GoTo"+ destino;
    }


    @Override
    public Double getCost() { return 1.0; }

    private boolean posibilidadGanar(PokeAgentState pokeAgentState){
        if(pokeAgentState.getPokeEnergia() >= pokeAgentState.getPokeUbicacion().getPokeEnemigo().getEnergia()) return true;
        return posibilidadGanar1(pokeAgentState) || posibilidadGanar2(pokeAgentState) || posibilidadGanar3(pokeAgentState);
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        GoToX goToX = (GoToX) o;
        return destino.equals(goToX.destino);
    }
}
