package agent;

import environment.PokePercepcion;
import environment.PokeUbicacion;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.*;

public class    PokeAgentState extends SearchBasedAgentState {
    private String BOSS_LOCATION = "Boss";
    private String AGENT_INIT_LOCATION = "TierraDelFuego";
    private PokeUbicacion pokeUbicacion;
    private double pokeEnergia;
    private Double pokeEnergiaInicial;
    private Integer[][] pokeAtaques;
    //TODO
    //Debe existir una percepcion desede el ambiente que informe a la gente de la cantidad de pokemones.
    private Integer pokeCantidad;
    private HashMap<String, ArrayList<String>> map;
    private HashMap<String, PokeUbicacion> pokeUbicaciones;
    public PokeAgentState(){
        pokeUbicacion = new PokeUbicacion(AGENT_INIT_LOCATION,null,0,0.0,0.0);
        map = new HashMap<>();
        pokeUbicaciones = new HashMap<>();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PokeAgentState that)) {
            return false;
        }
        return this.pokeUbicacion.equals(that.pokeUbicacion) && this.pokeEnergia == that.pokeEnergia;
        /*
        return this.pokeUbicacion.equals(that.pokeUbicacion) &&
                      this.pokeEnergia == that.pokeEnergia &&
                      Objects.equals(this.pokeCantidad, that.pokeCantidad) &&
                      Objects.equals(this.pokeEnergiaInicial, that.pokeEnergiaInicial) &&
                      Arrays.deepEquals(this.pokeAtaques, that.pokeAtaques) &&
                      this.pokeUbicaciones.equals(that.pokeUbicaciones) &&
                      this.map.equals(that.map);

         */
    }

    @Override
    public SearchBasedAgentState clone() {
        PokeAgentState pokeAgentState = new PokeAgentState();
        pokeAgentState.setPokeUbicacion(this.pokeUbicacion.clone());
        pokeAgentState.setPokeEnergia(pokeEnergia);
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
        return "{ " + "Energia: "+ pokeEnergia + ", Ubicacion: " + pokeUbicacion + " }";
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
    public void recargar(Integer energiaPokeparada){
        pokeEnergia += energiaPokeparada;

    }

    public void verificarPoderesEspeciales(){

        refreshCoolDown();

        //TODO los poderes se estan activando presuntamente rapido.
        if( this.pokeEnergia >= (1.25*this.pokeEnergiaInicial)) {
            this.pokeAtaques[0][0] = 1;
            this.pokeAtaques[1][0] = 0;
        }
        if( this.pokeEnergia >= (1.75*this.pokeEnergiaInicial)) {
            this.pokeAtaques[0][1] =1;
            this.pokeAtaques[1][1] = 0;
        }
        if( this.pokeEnergia >= (2.2*this.pokeEnergiaInicial)) {
            this.pokeAtaques[0][2] =1;
            this.pokeAtaques[1][2] = 0;
        }
    }
    private void refreshCoolDown(){
        if(ataqueEspecial1Enabled() && this.pokeAtaques[1][0] > 0) this.pokeAtaques[1][0]--;

        if(ataqueEspecial2Enabled() && this.pokeAtaques[1][1] > 0) this.pokeAtaques[1][1]--;

        if(ataqueEspecial3Enabled() && this.pokeAtaques[1][2] > 0) this.pokeAtaques[1][2]--;
    }
    public boolean ataqueEspecial1Enabled(){
        return this.pokeAtaques[0][0] == 1 && this.pokeAtaques[1][0] == 0;
    }

    public boolean ataqueEspecial2Enabled(){
        return this.pokeAtaques[0][1] == 1 && this.pokeAtaques[1][1] == 0;
    }

    public boolean ataqueEspecial3Enabled(){
        return this.pokeAtaques[0][2] == 1 && this.pokeAtaques[1][2] == 0;
    }
    public boolean algunAtaqueEspecial(){
        return ataqueEspecial1Enabled() || ataqueEspecial2Enabled() || ataqueEspecial3Enabled();
    }
    public void setCoolDown1(){
        this.pokeAtaques[1][0] = 0;
    }
    public void setCoolDown2(){
        this.pokeAtaques[1][1] = 0;
    }
    public void setCoolDown3(){
        this.pokeAtaques[1][2] = 0;
    }
    public boolean isDead(){
        return pokeEnergia < 1;
    }
    public void decrementarEnergia(){pokeEnergia--;}

    public boolean leGanoAlBoss(){
        if(posibilidadGanarAlBoss()) return true;
        if(ataqueEspecial1Enabled() && posibilidadGanar1AlBoss()) return true;
        if(ataqueEspecial2Enabled() && posibilidadGanar2AlBoss()) return true;
        if(ataqueEspecial3Enabled() && posibilidadGanar3AlBoss()) return true;
        return false;
    }
    public boolean posibilidadGanarAlBoss(){
        boolean rtn = false;
        int energiaEnemigo = getPokeUbicaciones().get("Boss").getPokeEnemigo().getEnergia();
        if (getPokeEnergia() >= energiaEnemigo) rtn=true;
        return rtn;
    }
    public boolean posibilidadGanar1AlBoss(){
        boolean rtn = false;
        int energiaEnemigo = getPokeUbicaciones().get("Boss").getPokeEnemigo().getEnergia();
        if (1.2 * getPokeEnergia() > energiaEnemigo)rtn=true;
        return rtn;
    }
    public boolean posibilidadGanar2AlBoss(){
        boolean rtn = false;
        int energiaEnemigo = getPokeUbicaciones().get("Boss").getPokeEnemigo().getEnergia();
        if (1.3 * getPokeEnergia() > energiaEnemigo)rtn=true;
        return rtn;
    }
    public boolean posibilidadGanar3AlBoss(){
        boolean rtn = false;
        int energiaEnemigo = getPokeUbicaciones().get("Boss").getPokeEnemigo().getEnergia();
        if (1.5 * getPokeEnergia() > energiaEnemigo)rtn=true;
        return rtn;
    }
}
