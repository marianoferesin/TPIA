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
        HashMap<String, PokeUbicacion> ubiPerception = new HashMap<String,PokeUbicacion>();
        PokePercepcion perception = new PokePercepcion();
        String miUbicacionPerception = this.getEnvironmentState().getUbicacionPokeLuchador();
        perception.setMiUbicacion(miUbicacionPerception);
        // si se puede usar el satelite se envia todo completo
        if ( this.getEnvironmentState().getSatelite()){
            perception.setMiMap(this.getEnvironmentState().getMap());
            perception.setMisUbicacionesVisibles(this.getEnvironmentState().getPokeUbicaciones());
        // si no se puede utilizar el satelite solo se envia la ubicación y los adyacentes
        }else{
            mapPerception.put(miUbicacionPerception, this.getEnvironmentState().getMap().get(miUbicacionPerception)) ;
            ubiPerception.put(miUbicacionPerception,this.getEnvironmentState().getPokeUbicaciones().get(miUbicacionPerception));
            ArrayList<String> adyacentes = mapPerception.get(miUbicacionPerception);
            for (int i = 0; i < adyacentes.size(); i++) {
                ubiPerception.put(adyacentes.get(i),this.getEnvironmentState().getPokeUbicaciones().get(adyacentes.get(i)));
            }
        }
        //mueve enemigos dado que estamos saliendo de un ciclo de percepcion
        this.getEnvironmentState().MoverEnemigos();
        return perception;
    }

    public String toString(){
        return super.environmentState.toString();
    }
}
