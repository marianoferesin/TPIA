package GUI.FXGLClasses;

import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.entity.*;

public class PokeFactory implements EntityFactory {
    @Spawns("agente")
    public Entity newAgent(SpawnData data){
        return (new EntityBuilder()).from(data).view("tpia/src/GUI/Images/agente.png").build();
    }
}
