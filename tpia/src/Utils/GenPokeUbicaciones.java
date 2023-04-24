package Utils;

import enemigos.PokeEnemigo;
import environment.PokeUbicacion;
import environment.Ubicacion;

import java.util.ArrayList;
import java.util.Collection;

public class GenPokeUbicaciones {
    public static Collection<PokeUbicacion> genPokeUbicaciones(Integer cantidadEnemigos,Integer minEnergia,Integer maxEnergia){
        ArrayList<PokeUbicacion> pokeUbicaciones = new ArrayList<>();
        for (Ubicacion ubicacion : Ubicacion.values()) {
            if(Ubicacion.getPokeParadas().contains(ubicacion)){
                pokeUbicaciones.add(new PokeUbicacion(ubicacion, (int) (Math.random() * (10-5+1)+5)));
            }
            else {
                pokeUbicaciones.add(new PokeUbicacion(ubicacion));
            }
        }
        for(int i=0;i<cantidadEnemigos;i++){
            int randomIndex = (int) (Math.random() * (pokeUbicaciones.size()));
            if(!Ubicacion.getPokeParadas().contains(pokeUbicaciones.get(randomIndex).getUbicacion()) && pokeUbicaciones.get(randomIndex).getEnemigo() == null){
                pokeUbicaciones.get(randomIndex).setEnemigo(new PokeEnemigo((int) (Math.random() * (maxEnergia-minEnergia+1)+minEnergia)));
            }else{
                i--;
            }
        }
        return pokeUbicaciones;
    }
}
