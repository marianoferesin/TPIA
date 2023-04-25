package environment;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PokeEnvironment extends Environment {
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
