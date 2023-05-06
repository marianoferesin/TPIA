package enemigos;

public class PokeEnemigo {

    protected Integer id;
    protected Integer energia;
    private Integer cooldownMoverse;


    public PokeEnemigo(Integer id, Integer energia) {
        this.id = id;
        this.energia = energia;
        this.cooldownMoverse = (int) ((Math.random() * ((3 - 1) + 1)) + 1);
    }

    public PokeEnemigo(PokeEnemigo e){
        this.id = e.id;
        this.energia = e.energia;
        this.cooldownMoverse = e.getCooldownMoverse();
    }

    public Integer getCooldownMoverse() {
        return this.cooldownMoverse;
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
