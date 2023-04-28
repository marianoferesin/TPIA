package enemigos;

public class PokeEnemigo {

    private Integer id;
    private Integer energia;
    private Integer cooldownMoverse;


    public PokeEnemigo(Integer id, Integer energia) {
        this.id = id;
        this.energia = energia;
        this.cooldownMoverse = (int) ((Math.random() * ((3 - 1) + 1)) + 1);
    }

    public boolean moverse(){
        this.cooldownMoverse-=1;
        if (cooldownMoverse == 0){
         this.cooldownMoverse = (int) ((Math.random() * ((3 - 1) + 1)) + 1);
         return true;
        }
        else return false;
    }

    public Integer getEnergia(){
        return this.energia;
    }

    public void setEnergia(Integer energia) {
        this.energia = energia;
    }
}
