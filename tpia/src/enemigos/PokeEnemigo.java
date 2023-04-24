package enemigos;

public class PokeEnemigo {
    private final Integer energia;
    public PokeEnemigo(Integer energia) {
        this.energia=energia;
    }

    public Integer getEnergia() {
        return energia;
    }

    public String toString(){
        return this.energia.toString();
    }
}
