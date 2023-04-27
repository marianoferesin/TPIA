package environment;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PokeEnvironment extends Environment {
    private final Integer CANTIDAD_ENEMIGOS = 10;
    private final Integer MIN_ENERGIA_ENEMIGOS = 5;
    private final Integer MAX_ENERGIA_ENEMIGOS = 10;
    public PokeEnvironment(){
        //Create enviorement state
        this.environmentState = new PokeEnvironmentState();
    }
    @Override
    public Perception getPercept() {
        return null;
    }

    public String toString(){
        return this.toString();
    }
}
