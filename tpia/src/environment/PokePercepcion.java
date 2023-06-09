package environment;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;
import java.util.HashMap;

public class PokePercepcion extends Perception {

    private HashMap<String, ArrayList<String>> miMap;
    private HashMap<String, PokeUbicacion> misUbicacionesVisibles;
    private PokeUbicacion miUbicacion;

    public HashMap<String, ArrayList<String>> getMiMap() {
        return miMap;
    }

    public HashMap<String, PokeUbicacion> getMisUbicacionesVisibles() {
        return misUbicacionesVisibles;
    }

    public PokeUbicacion getMiUbicacion() {
        return miUbicacion;
    }

    public void setMiMap(HashMap<String, ArrayList<String>> miMap) {
        this.miMap = miMap;
    }

    public void setMisUbicacionesVisibles(HashMap<String, PokeUbicacion> misUbicacionesVisibles) {
        this.misUbicacionesVisibles = misUbicacionesVisibles;
    }

    public void setMiUbicacion(PokeUbicacion miUbicacion) {
        this.miUbicacion = miUbicacion;
    }

    @Override
    // iniciacización basica de la percepción, solo obtiene la ubicación actual y los adyacentes
    public void initPerception(Agent agent, Environment environment) {
        PokeEnvironment ambiente = (PokeEnvironment) environment;
        PokeEnvironmentState estadoAmbiente = ambiente.getEnvironmentState();
        this.miUbicacion = estadoAmbiente.getUbicacionPokeLuchador();
        this.miMap.put(this.miUbicacion.getNombre(), estadoAmbiente.getMap().get(this.miUbicacion.getNombre())) ;
        PokeUbicacion miPokeUbicacion = estadoAmbiente.getPokeUbicaciones().get(this.miUbicacion.getNombre());

        this.misUbicacionesVisibles.put(this.miUbicacion.getNombre(),miPokeUbicacion);
        ArrayList<String> adyacentes = miMap.get(this.miUbicacion.getNombre());
        for (int i = 0; i < adyacentes.size(); i++) {
            PokeUbicacion adyUbicacion = estadoAmbiente.getPokeUbicaciones().get(adyacentes.get(i));
            this.misUbicacionesVisibles.put(adyacentes.get(i),adyUbicacion);
            }
    }

    @Override
    public String toString() {
        return "PokePercepcion: " + "miMap= {...}, misUbicacionesVisibles= {...} , miUbicacion=" + miUbicacion.getNombre();
    }
}
