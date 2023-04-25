package environment;

import FileReaders.FileReaders;
import enemigos.PokeEnemigo;
import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class PokeEnvironmentState extends EnvironmentState {
    private HashMap<String, ArrayList<String>> map;
    private HashMap<String, PokeUbicacion> pokeUbicaciones;
    private String ubicacionBoss;

    public PokeEnvironmentState(){
        this.map = new HashMap<String, ArrayList<String>>();
        this.pokeUbicaciones = new HashMap<String,PokeUbicacion>();
    }
    @Override
    public void initState() {
        //Get all places
       ArrayList <String> ubicaciones = FileReaders.leerUbicaciones();
       for(String ubi: ubicaciones){
           map.put(ubi,new ArrayList<String>());
       }

       //Get info of places
        ArrayList <ArrayList<String>> infoUbicaciones = FileReaders.leerInfoUbicaciones();
        for(ArrayList<String> info: infoUbicaciones){
            PokeEnemigo ene = new PokeEnemigo(Integer.valueOf(info.get(3)),Integer.valueOf(info.get(2)));
            PokeUbicacion ubi = new PokeUbicacion(info.get(0),ene,(Integer.valueOf(info.get(1)) == 1) ? Boolean.TRUE : Boolean.FALSE);
            pokeUbicaciones.put(ubi.getNombre(),ubi);
        }
       //An edge is represented by an ArrayList
       //This means that a pokemon can go from position 0 to position 1 and viceversa
       ArrayList <ArrayList<String>> aristas = FileReaders.leerAristas();
       for(ArrayList<String> a: aristas) {
           map.get(a.get(0)).add(a.get(1));
           map.get(a.get(1)).add(a.get(0));
       }


    }
    @Override
    public Object clone() {
        return map.clone();
    }


    @Override
    public String toString() {
        //Display info about all world
        String str = "";

        for (String point : map.keySet()) {
            str = str + "[ " + point + " --> ";
            if(pokeUbicaciones.get(point).esPokeparada()) str += " Pokeparada  --> ";
            else str += "Enemigo: " + pokeUbicaciones.get(point).getPokeEnemigo().getEnergia().toString() + " --> ";

            Collection<String> successors = map.get(point);
            if (successors != null) {
                for (String successor : successors) {
                    str = str + successor + " ";
                }
            }
            str = str + " ]\n";
        }

        return str;
    }

    @Override
    public boolean equals(Object obj) {
        // Returns always true. This method is not used.
        return true;
    }

}
