package environment;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;
import java.util.HashMap;

public class PokeEnvironment extends Environment {
    public PokeEnvironment(){
        this.environmentState = new PokeEnvironmentState();
        this.environmentState.initState();
    }

    public PokeEnvironmentState getEnvironmentState() {
        return (PokeEnvironmentState) this.environmentState;
    }

    @Override
    //Metodo llamado por el simulador para armar la percepción que será enviada al agente
    public Perception getPercept() {
        HashMap<String, ArrayList<String>> mapPerception = new HashMap<String, ArrayList<String>>();
        HashMap<String, PokeUbicacion> pokeUbicacionesPerception = new HashMap<String,PokeUbicacion>();
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
        //mueve enemigos dado que estamos saliendo de un ciclo de percepcion
        this.getEnvironmentState().MoverEnemigos();
        return perception;
    }

    public String toString(){
        return super.environmentState.toString();
    }
}
