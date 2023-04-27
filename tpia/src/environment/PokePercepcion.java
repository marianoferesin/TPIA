package environment;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;
import java.util.HashMap;

public class PokePercepcion extends Perception {
    // vecinos;
    // miUbicacion;
    // satelite
    private HashMap<String, ArrayList<String>> miMap;
    private int sateliteCount=10;
    private HashMap<String, PokeUbicacion> misUbicacionesVisibles;
    private String miUbicacion;


    public void setMiMap(HashMap<String, ArrayList<String>> miMap) {
        this.miMap = miMap;
    }

    public void setMisUbicacionesVisibles(HashMap<String, PokeUbicacion> misUbicacionesVisibles) {
        this.misUbicacionesVisibles = misUbicacionesVisibles;
    }

    public void setMiUbicacion(String miUbicacion) {
        this.miUbicacion = miUbicacion;
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {
        PokeEnvironment ambiente = (PokeEnvironment) environment;
        PokeEnvironmentState estadoAmbiente = (PokeEnvironmentState) environment.getEnvironmentState();
        this.miUbicacion = estadoAmbiente.getUbicacionPokeLuchador();
        this.miMap = estadoAmbiente.getMap();
        this.misUbicacionesVisibles = estadoAmbiente.getPokeUbicaciones();
        if (this.sateliteCount == 10){
            this.sateliteCount = 0;
            for (String ubi : misUbicacionesVisibles.keySet()) {
                misUbicacionesVisibles.get(ubi).resetAnt();
            }
        }else{
            this.sateliteCount+=1;
            for (String ubi : misUbicacionesVisibles.keySet()) {
                misUbicacionesVisibles.put(ubi,misUbicacionesVisibles.get(ubi).incrementAnt());
            }
            misUbicacionesVisibles.put(miUbicacion,(misUbicacionesVisibles.get(miUbicacion).resetAnt()));

            ArrayList<String> adyacentes = miMap.get(miUbicacion);
            for (int i = 0; i < adyacentes.size(); i++) {
                PokeUbicacion ady = misUbicacionesVisibles.get(adyacentes.get(i));
                misUbicacionesVisibles.put(adyacentes.get(i),ady.resetAnt());
            }
        }
    }





}
