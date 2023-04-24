package environment;

import Utils.GenAdyacencias;
import Utils.GenMap;
import Utils.GenPokeUbicaciones;
import frsf.cidisi.faia.state.EnvironmentState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class PokeEnvironmentState extends EnvironmentState {
    private Collection<PokeUbicacion> pokeUbicaciones;
    private HashMap<Ubicacion, Collection<Ubicacion>> map;
    private final Ubicacion[][] adyacencia;
    private final Integer cantidadEnemigos;
    private final Integer minimoEnergiaEnemigos;
    private final Integer maximoEnergiaEnemigos;
    public PokeEnvironmentState(Integer cantidadEnemigos,Integer minimoEnergiaEnemigos,Integer maximoEnergiaEnemigos){
        this.map = new HashMap<>();
        this.pokeUbicaciones = new ArrayList<>();
        this.adyacencia = GenAdyacencias.genAdyacencias();
        this.cantidadEnemigos = cantidadEnemigos;
        this.minimoEnergiaEnemigos = minimoEnergiaEnemigos;
        this.maximoEnergiaEnemigos = maximoEnergiaEnemigos;
    }
    @Override
    public void initState() {
        //Genera mapa de adyacencias.
        map = GenMap.genMap(adyacencia);
        //Genera pokeubicaciones, ademas genera enemigos
        pokeUbicaciones = GenPokeUbicaciones.genPokeUbicaciones(cantidadEnemigos,minimoEnergiaEnemigos,maximoEnergiaEnemigos);
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        return map.clone();
    }
    @Override
    public String toString() {
        String str = "";
        for (Ubicacion point : map.keySet()) {
            str = str + "[ " + point + " --> ";
            Collection<Ubicacion> successors = map.get(point);
            if (successors != null) {
                for (Ubicacion successor : successors) {
                    str = str + successor + " ";
                }
            }
            str = str + " ]\n";
        }
        return str;
    }
    public Collection<PokeUbicacion> getPokeUbicaciones() {
        return pokeUbicaciones;
    }
    public HashMap<Ubicacion, Collection<Ubicacion>> getMap() {
        return map;
    }
    public Integer getCantidadEnemigos() {
        return this.cantidadEnemigos;
    }
}
