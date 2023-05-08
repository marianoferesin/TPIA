package agent;

import environment.PokePercepcion;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class    PokeAgentState extends SearchBasedAgentState {
    private String BOSS_LOCATION = "Boss";
    private String AGENT_INIT_LOCATION = "TierraDelFuego";
    private PokeUbicacion pokeUbicacion;
    private Double pokeEnergia;
    private Double pokeEnergiaInicial;
    private Integer[][] pokeAtaques;
    //TODO
    //Debe existir una percepcion desede el ambiente que informe a la gente de la cantidad de pokemones.
    private Integer pokeCantidad;
    private HashMap<String, ArrayList<String>> map;
    private HashMap<String, PokeUbicacion> pokeUbicaciones;
    public PokeAgentState(){
        pokeUbicacion = new PokeUbicacion(AGENT_INIT_LOCATION,null,0);
        map = new HashMap<>();
        pokeUbicaciones = new HashMap<>();
    }
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public SearchBasedAgentState clone() {
        PokeAgentState pokeAgentState = new PokeAgentState();
        pokeAgentState.setPokeUbicacion(this.pokeUbicacion.clone());
        pokeAgentState.setPokeEnergia(this.pokeEnergia);
        pokeAgentState.setPokeEnergiaInicial(this.pokeEnergiaInicial);
        pokeAgentState.setPokeAtaques(this.pokeAtaques);
        pokeAgentState.setPokeCantidad(this.pokeCantidad);
        HashMap<String, ArrayList<String>> copyMap = new HashMap<>();
        for (Map.Entry<String, ArrayList<String>> entry : this.map.entrySet()) {
            copyMap.put(entry.getKey(), new ArrayList<String>(entry.getValue()));
        }
        pokeAgentState.setMap(copyMap);
        HashMap<String, PokeUbicacion> copyPokeUbicaciones = new HashMap<>();
        for (Map.Entry<String, PokeUbicacion> entry : this.pokeUbicaciones.entrySet()) {
            copyPokeUbicaciones.put(entry.getKey(), new PokeUbicacion(entry.getValue()));
        }
        pokeAgentState.setPokeUbicaciones(copyPokeUbicaciones);

        return pokeAgentState;
    }

    @Override
    public void updateState(Perception p) {
        //TODO actualizar bien el estado.
        PokePercepcion percep = (PokePercepcion) p;
        //Actualizo mi ubicacion
        this.pokeUbicacion = percep.getMiUbicacion();
        //Actualizo mi mapa
        for (String key : percep.getMiMap().keySet()){
            this.map.put(key,percep.getMiMap().get(key));
        }
       // this.map.putAll(percep.getMiMap());

        //Actualizo mis ubicaciones
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
        int random = (int) (Math.random()*(20-10+1)+10);
        pokeEnergiaInicial = random * 1.0;
        pokeEnergia = pokeEnergiaInicial;
        pokeAtaques = new Integer[][]{{0,0,0},{0,0,0}};
        pokeCantidad = 0;
    }
    public PokeUbicacion getPokeUbicacion() {
        return pokeUbicacion;
    }

    public void setPokeUbicacion(PokeUbicacion pokeUbicacion) {
        this.pokeUbicacion = pokeUbicacion;
    }

    public HashMap<String, ArrayList<String>> getMap() {
        return map;
    }

    public HashMap<String, PokeUbicacion> getPokeUbicaciones() {
        return pokeUbicaciones;
    }

    private void initMap(){

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

    public Double getPokeEnergia() {
        return pokeEnergia;
    }

    public void setPokeEnergia(Double pokeEnergia) {
            this.pokeEnergia = pokeEnergia;
    }
    public Double getPokeEnergiaInicial() {
        return pokeEnergiaInicial;
    }

    public void setPokeEnergiaInicial(Double pokeEnergiaInicial) {
        this.pokeEnergiaInicial = pokeEnergiaInicial;
    }
    public void setPokeAtaques(Integer[][] pokeAtaques) {
        this.pokeAtaques = pokeAtaques;
    }

    public void setPokeCantidad(Integer pokeCantidad) {
        this.pokeCantidad = pokeCantidad;
    }

    public void setMap(HashMap<String, ArrayList<String>> map) {
        this.map = map;
    }

    public void setPokeUbicaciones(HashMap<String, PokeUbicacion> pokeUbicaciones) {
        this.pokeUbicaciones = pokeUbicaciones;
    }

    public void huir(){
        if (this.pokeUbicacion.getPokeEnemigo().getEnergia() > 0){
            this.setPokeEnergia(this.pokeEnergia-(this.pokeUbicacion.getPokeEnemigo().getEnergia()/4));
        }
    }

    public void verificarPoderesEspeciales(){
        if( (1.2*this.pokeEnergiaInicial) >= this.pokeEnergia) { this.pokeAtaques[0][0] =1;}
        if( (1.3*this.pokeEnergiaInicial) >= this.pokeEnergia) { this.pokeAtaques[0][1] =1;}
        if( (1.5*this.pokeEnergiaInicial) >= this.pokeEnergia) { this.pokeAtaques[0][2] =1;}
    }

    public boolean AtaqueEspecial1Enabled(){
        return this.pokeAtaques[0][0] == 1;
    }

    public boolean AtaqueEspecial2Enabled(){
        return this.pokeAtaques[0][1] == 1;
    }

    public boolean AtaqueEspecial3Enabled(){
        return this.pokeAtaques[0][2] == 1;
    }

}
