package environment;

import FileReaders.FileReaders;
import enemigos.PokeEnemigo;
import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class PokeEnvironmentState extends EnvironmentState {
    private String BOSS_LOCATION = "Boss";
    private String AGENT_LOCATION = "TierraDelFuego";
    private HashMap<String, ArrayList<String>> map;
    private HashMap<String, PokeUbicacion> pokeUbicaciones;
    private String ubicacionBoss;
    private Integer cdSatelite;
    private String ubicacionPokeLuchador;
 public PokeEnvironmentState(){
        this.map = new HashMap<String, ArrayList<String>>();
        this.pokeUbicaciones = new HashMap<String,PokeUbicacion>();
        this.cdSatelite = 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public void initState() {
        //Get all places
       ArrayList <String> ubicaciones = FileReaders.leerUbicaciones();
       for(String ubi: ubicaciones){
           this.map.put(ubi,new ArrayList<String>());
       }

       //Get info of places
        ArrayList <ArrayList<String>> infoUbicaciones = FileReaders.leerInfoUbicaciones();
        for(ArrayList<String> info: infoUbicaciones){
            PokeEnemigo ene = new PokeEnemigo(Integer.valueOf(info.get(3)),Integer.valueOf(info.get(2)));
            PokeUbicacion ubi = new PokeUbicacion(info.get(0),ene,(Integer.valueOf(info.get(1)) == 1) ? Boolean.TRUE : Boolean.FALSE);
            this.pokeUbicaciones.put(ubi.getNombre(),ubi);
        }
       //An edge is represented by an ArrayList
       //This means that a pokemon can go from position 0 to position 1 and viceversa
       ArrayList <ArrayList<String>> aristas = FileReaders.leerAristas();
       for(ArrayList<String> a: aristas) {
           this.map.get(a.get(0)).add(a.get(1));
           this.map.get(a.get(1)).add(a.get(0));
       }
       this.ubicacionBoss =  BOSS_LOCATION;
       this.ubicacionPokeLuchador = AGENT_LOCATION;

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
            if(this.pokeUbicaciones.get(point).esPokeparada()) str += " Pokeparada  --> ";
            else str += "Enemigo: " + this.pokeUbicaciones.get(point).getPokeEnemigo().getEnergia().toString() + " --> ";

            Collection<String> successors = this.map.get(point);
            if (successors != null) {
                for (String successor : successors) {
                    str = str + successor + " ";
                }
            }
            str = str + " ]\n";
        }

        return str;
    }
    

    private void actualizarCdSatelite() {
        if (this.cdSatelite < 10) {
            this.cdSatelite++;
        } else {
            this.cdSatelite =0;
        }
    }

    public boolean getSatelite(){
        if (this.cdSatelite == 0 ){
            this.actualizarCdSatelite();
            return true;
        }else{
            this.actualizarCdSatelite();
            return false;
        }
    }

    public void setUbicacionPokeLuchador(String ubicacionPokeLuchador) {
        this.ubicacionPokeLuchador = ubicacionPokeLuchador;
    }

    public String getUbicacionPokeLuchador() {
        return ubicacionPokeLuchador;
    }

    private void moverEnemigo(PokeUbicacion unaUbicacion){

        if (unaUbicacion.getPokeEnemigo() != null &&  unaUbicacion.getPokeEnemigo().moverse()){
            String ubi= unaUbicacion.getNombre();
            ArrayList<String> adyacentes = map.get(ubi);
            for (int i = 0; i < adyacentes.size(); i++) {
                PokeUbicacion ady = pokeUbicaciones.get(adyacentes.get(i));
                if (ady.getPokeEnemigo() != null || ady.esPokeparada()){
                    adyacentes.remove(i);
                }
            }
            if(adyacentes.size() != 0){
                PokeEnemigo enemigo = unaUbicacion.getPokeEnemigo();
                unaUbicacion.removerPokeEnemigo();
                pokeUbicaciones.put(ubi,unaUbicacion);
                System.out.println(adyacentes.size());
                int destino = ((new Random()).nextInt() % adyacentes.size());
                System.out.println(destino);
                if(destino<0) destino = destino*(-1);
                String dest = adyacentes.get(destino);
                PokeUbicacion nuevaUbi = pokeUbicaciones.get(dest);
                nuevaUbi.setPokeEnemigo(enemigo);
                pokeUbicaciones.put(nuevaUbi.getNombre(), nuevaUbi);
            }
        }
    }

    public void MoverEnemigos(){
        for (String point : pokeUbicaciones.keySet()) {
            this.moverEnemigo(pokeUbicaciones.get(point));
        }
    }

    public HashMap<String, ArrayList<String>> getMap() {
        return map;
    }

    public HashMap<String, PokeUbicacion> getPokeUbicaciones() {
        return pokeUbicaciones;
    }
}
