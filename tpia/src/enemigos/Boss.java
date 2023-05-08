package enemigos;

public class Boss extends PokeEnemigo{

    public Boss(Integer energia) {
        super(energia);
    }

    public Boss(PokeEnemigo e) {
        super(e);
    }
    @Override
    public Integer getCooldownMoverse() {
        return 99;
    }

    @Override
    public boolean moverse(){
        return false;
    }
}
