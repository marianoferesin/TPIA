import agent.PokeAgentState;
import environment.PokeEnvironmentState;

public class Main {
    public static void main(String[] args) {
        PokeEnvironmentState estado = new PokeEnvironmentState();
        estado.initState();
        PokeAgentState estadoAgente = new PokeAgentState();
        estadoAgente.initState();
        System.out.println(estadoAgente.printPokeAtaques());
    }
}
