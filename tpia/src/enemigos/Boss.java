package enemigos;

public class Boss extends PokeEnemigo{

    public Boss(Integer id, Integer energia) {
        super(id, energia);
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
