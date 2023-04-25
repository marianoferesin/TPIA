package enemigos;

public class PokeEnemigo {
    private final Integer energia;
    private Integer cooldownMoverse;
    public PokeEnemigo(Integer energia) {
        this.energia=energia;
        this.cooldownMoverse = (int) ((Math.random() * ((3 - 1) + 1)) + 3);
    }

    public Integer getEnergia() {
        return energia;
    }

    public String toString(){
        return this.energia.toString();
    }

    public boolean moverse(){
        this.cooldownMoverse-=1;
        if (cooldownMoverse == 0){
         this.cooldownMoverse = (int) ((Math.random() * ((3 - 1) + 1)) + 3);
         return true;
        }
        else return false;
    }
}
