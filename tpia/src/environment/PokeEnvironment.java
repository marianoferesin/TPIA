package environment;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.AgentState;
import modificacionesFaia.Search.StaticEnvironmentList;

import java.util.ArrayList;
import java.util.HashMap;

public class PokeEnvironment extends Environment {
    public PokeEnvironment(){
        this.environmentState = new PokeEnvironmentState();
        this.environmentState.initState();
    }@Override

    public PokeEnvironmentState getEnvironmentState() {
        return (PokeEnvironmentState) this.environmentState;
    }

    @Override
    //Metodo llamado por el simulador para armar la percepción que será enviada al agente
    public Perception getPercept() {
        //TODO los enemigos se mueven al principio del ciclo por ahora, despues habria que cambiarlo.
        this.getEnvironmentState().MoverEnemigos();
        HashMap<String, ArrayList<String>> mapPerception = new HashMap<>();
        HashMap<String, PokeUbicacion> pokeUbicacionesPerception = new HashMap<>();
        PokePercepcion perception = new PokePercepcion();
        PokeUbicacion miUbicacion = this.getEnvironmentState().getUbicacionPokeLuchador();
        String nombreUbi = miUbicacion.getNombre();
        perception.setMiUbicacion(miUbicacion);
        // si se puede usar el satelite se envia el mapa completo
        if (this.getEnvironmentState().getSatelite()){
            perception.setMiMap(this.getEnvironmentState().getMap());
            perception.setMisUbicacionesVisibles(this.getEnvironmentState().getPokeUbicaciones());
        // si no se puede utilizar el satelite solo se envia la ubicación y los adyacentes
        }else{
            mapPerception.put(nombreUbi, this.getEnvironmentState().getMap().get(nombreUbi)) ;
            pokeUbicacionesPerception.put(nombreUbi,this.getEnvironmentState().getPokeUbicaciones().get(nombreUbi));
            ArrayList<String> adyacentes = mapPerception.get(nombreUbi);
            for (String adyacente : adyacentes) {
                pokeUbicacionesPerception.put(adyacente, this.getEnvironmentState().getPokeUbicaciones().get(adyacente));
            }
            perception.setMiMap(mapPerception);
            perception.setMisUbicacionesVisibles(pokeUbicacionesPerception);
        }
        return perception;
    }
    @Override
    public void updateState(AgentState ast, Action action) {
        this.environmentState = action.execute(ast, environmentState);
        StaticEnvironmentList.actions.add((SearchAction) action);
        StaticEnvironmentList.pokeEnvironmentsStates.add((PokeEnvironmentState) ((PokeEnvironmentState) this.environmentState).clone());
    }
    @Override
    public boolean agentFailed(Action actionReturned) {
        return !((PokeEnvironmentState) this.environmentState).isAgenteConVida();
    }
    public String toString(){
        return environmentState.toString();
    }
}
