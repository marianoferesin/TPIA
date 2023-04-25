package enemigos;

public class PokeEnemigo {
    private Integer id;
    private Integer energia;

    public PokeEnemigo(Integer id, Integer energia) {
        this.id = id;
        this.energia = energia;
    }

    public Integer getEnergia(){
        return this.energia;
    }
}
