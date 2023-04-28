package agent;

import FileReaders.FileReaders;
import enemigos.PokeEnemigo;
import environment.PokePercepcion;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.ArrayList;
import java.util.HashMap;

public class PokeAgentState extends SearchBasedAgentState {
    private String BOSS_LOCATION = "Boss";
    private String AGENT_INIT_LOCATION = "TierraDelFuego";
    private String pokeUbicacion;
    private Integer pokeEnergia;
    private Integer pokeEnergiaInicial;
    private Integer energiaContrincante;
    private Integer[][] pokeAtaques;
    //TODO
    //Debe existir una percepcion desee el ambiente que informe a la gente de la cantidad de pokemones.
    private Integer pokeCantidad;
    private HashMap<String, ArrayList<String>> map;
    private HashMap<String, PokeUbicacion> pokeUbicaciones;
    public PokeAgentState(){
        pokeUbicacion = AGENT_INIT_LOCATION;
        map = new HashMap<>();
        pokeUbicaciones = new HashMap<>();
    }

    public String getPokeUbicacion() {
        return pokeUbicacion;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public SearchBasedAgentState clone() {
        return null;
    }

    @Override
    public void updateState(Perception p) {
        PokePercepcion percep = (PokePercepcion) p;
        this.pokeUbicacion = percep.getMiUbicacion();
        this.map.putAll(percep.getMiMap());
        for (String ubi : this.pokeUbicaciones.keySet()) {
            PokeUbicacion unaUbi =  this.pokeUbicaciones.get(ubi);
            unaUbi.incrementAnt();
            this.pokeUbicaciones.put(ubi,unaUbi);
        }
        for (String ubi : percep.getMisUbicacionesVisibles().keySet()) {
            PokeUbicacion unaUbi =  percep.getMisUbicacionesVisibles().get(ubi);
            unaUbi.resetAnt();
            this.pokeUbicaciones.put(ubi,unaUbi);
        }
    }

    @Override
    public void initState() {
        initMap();
        initPlayer();
    }
    private void initPlayer(){
        pokeEnergiaInicial = (int) (Math.random()*(20-10+1)+10);
        pokeEnergia = pokeEnergiaInicial;
        energiaContrincante = 0;
        pokeAtaques = new Integer[][]{{0,0,0},{0,0,0}};
        pokeCantidad = 0;
    }

    public void setPokeUbicacion(String pokeUbicacion) {
        this.pokeUbicacion = pokeUbicacion;
    }

    public HashMap<String, ArrayList<String>> getMap() {
        return map;
    }

    public HashMap<String, PokeUbicacion> getPokeUbicaciones() {
        return pokeUbicaciones;
    }

    private void initMap(){
        ArrayList <String> ubicaciones = FileReaders.leerUbicaciones();
        for(String ubi: ubicaciones){
            map.put(ubi,new ArrayList<>());
        }

        //Get info of places
        ArrayList <ArrayList<String>> infoUbicaciones = FileReaders.leerInfoUbicaciones();
        for(ArrayList<String> info: infoUbicaciones){
            PokeEnemigo ene = new PokeEnemigo(Integer.valueOf(info.get(3)),Integer.valueOf(info.get(2)));
            PokeUbicacion ubi = new PokeUbicacion(info.get(0),ene,Boolean.valueOf(info.get(1)));
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
    public Integer[][] getPokeAtaques(){
        return pokeAtaques;
    }
    public String printPokeAtaques(){
        String str;
        str = "[ " + pokeAtaques[0][0] + " , " + pokeAtaques[0][1] + " , " + pokeAtaques[0][2] + " ] \n";
        str = str + "[ " + pokeAtaques[1][0] + " , " + pokeAtaques[1][1] + " , " + pokeAtaques[1][2] + " ]";
        return str;
    }
    @Override
    public String toString() {
        return "{ " + "Energia: "+ pokeEnergia + "," + pokeUbicacion + " }";
    }
}
