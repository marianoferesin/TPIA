package Utils;

import enviroment.PokeUbicacion;
import enviroment.Ubicacion;

import java.util.ArrayList;
import java.util.Collection;

public class GenPokeUbicaciones {
    public static Collection<PokeUbicacion> genPokeUbicaciones(){
        ArrayList<PokeUbicacion> pokeUbicaciones = new ArrayList<>();
        for (Ubicacion ubicacion : Ubicacion.values()) {
            if(Ubicacion.getPokeParadas().contains(ubicacion)){
                pokeUbicaciones.add(new PokeUbicacion(ubicacion, (int) (Math.random() * (10-5+1)+5)));
            }
            else {
                pokeUbicaciones.add(new PokeUbicacion(ubicacion));
            }
        }
        return pokeUbicaciones;
    }
}
