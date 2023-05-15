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
        ArrayList<String> adyacentes = agentState.getMap().get(agentState.getPokeUbicacion().getNombre());
        if(agentState.isDead())return null;
        if(((PokeAgentState) s).getPokeUbicacion().isBoss()) return null;
        if (adyacentes.contains(destino)) {
            if(agentState.getPokeUbicacion().getNombre().equals(destino)) return null;
            //Si hay enemigo huyo
            agentState.huir();
            agentState.recargar(agentState.getPokeUbicacion().getEnergiaPokeparada());
            agentState.verificarPoderesEspeciales();
            agentState.getPokeUbicacion().usarPokeParada();
            agentState.getPokeUbicaciones().put(agentState.getPokeUbicacion().getNombre(),agentState.getPokeUbicacion());
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
    public Double getCost() {
        double rtn = 0.0;
        switch (destino) {
            case ("Boss") -> rtn = -3.0;
            case ("Sudafrica"), ("Arabia"), ("Australia") -> rtn = -2.0;
            case ("Egipto"), ("Indonesia"), ("NuevaZelanda"), ("India") -> rtn = -2.0;
            case ("NuevaGuinea"), ("Sahara"), ("Suecia"), ("Moscu"), ("Japon") -> rtn = -2.0;
            case ("Canarias"), ("Inglaterra"), ("Noruega"), ("Siberia"), ("China") -> rtn = -2.0;
            case ("Groenlandia"), ("Canada"), ("Cuba"), ("Peru"), ("Kamchatka") -> rtn = -2.0;
            case ("TierraDelFuego"), ("Brasil") -> rtn = -2.0;
            case ("BuenosAires") -> rtn = -2.0;
        }
        return rtn;
    }
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
