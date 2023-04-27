package environment;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PokeEnvironment extends Environment {
    public PokeEnvironment(){
        //Create enviorement state
        this.environmentState = new PokeEnvironmentState();
    }

    public PokeEnvironmentState getEnvironmentState() {
        return (PokeEnvironmentState) super.getEnvironmentState();
    }

    @Override
    public Perception getPercept() {
        PokePercepcion perception = new PokePercepcion();
        perception.setMiMap(this.getEnvironmentState().getMap());
        perception.setMisUbicacionesVisibles(this.getEnvironmentState().getPokeUbicaciones());
        perception.setMiUbicacion(this.getEnvironmentState().getUbicacionPokeLuchador());
        return perception;
    }

    public String toString(){
        return this.toString();
    }
}
