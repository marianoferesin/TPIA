package agent;

import FileReaders.FileReaders;
import enemigos.PokeEnemigo;
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
    private HashMap<String, PokeUbicacionAgent> pokeUbicaciones;
    public PokeAgentState(){
        pokeUbicacion = AGENT_INIT_LOCATION;
        map = new HashMap<>();
        pokeUbicaciones = new HashMap<>();
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
    private void initMap(){
        ArrayList <String> ubicaciones = FileReaders.leerUbicaciones();
        for(String ubi: ubicaciones){
            map.put(ubi,new ArrayList<>());
        }

        //Get info of places
        ArrayList <ArrayList<String>> infoUbicaciones = FileReaders.leerInfoUbicaciones();
        for(ArrayList<String> info: infoUbicaciones){
            PokeEnemigo ene = null;
            PokeUbicacionAgent ubi = new PokeUbicacionAgent(info.get(0),ene,0,(Integer.valueOf(info.get(1)) == 1) ? Boolean.TRUE : Boolean.FALSE);
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
