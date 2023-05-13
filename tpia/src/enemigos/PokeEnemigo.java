package enemigos;

public class PokeEnemigo {
    protected Integer energia;
    private Integer cooldownMoverse;

    public String getUbicacionAnterior() {
        return UbicacionAnterior;
    }

    public void setUbicacionAnterior(String ubicacionAnterior) {
        UbicacionAnterior = ubicacionAnterior;
    }

    private String UbicacionAnterior;


    public PokeEnemigo(Integer energia) {
        this.energia = energia;
        this.cooldownMoverse = (int) ((Math.random() * ((3 - 1) + 1)) + 1);
    }

    public PokeEnemigo(PokeEnemigo e){
        this.energia = e.energia;
        this.cooldownMoverse = e.getCooldownMoverse();
    }
     public PokeEnemigo(Integer energia,Integer cooldownMoverse){
        this.energia = energia;
        this.cooldownMoverse = cooldownMoverse;
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

    public PokeEnemigo clone(){
        return new PokeEnemigo(this.getEnergia(),this.getCooldownMoverse());
    }
}
