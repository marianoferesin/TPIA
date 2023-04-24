package Utils;

import environment.Ubicacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class GenMap {
    public static HashMap<Ubicacion, Collection<Ubicacion>> genMap(Ubicacion[][] adyacencia){
        HashMap<Ubicacion, Collection<Ubicacion>> map = new HashMap<>();
        for (int i = 0; i < adyacencia.length; i++) {
            ArrayList<Ubicacion> successors = new ArrayList<>();
            for (int j = 1; j < adyacencia[i].length; j++) {
                successors.add(adyacencia[i][j]);
            }
            map.put(adyacencia[i][0], successors);
        }
        return map;
    }
}
