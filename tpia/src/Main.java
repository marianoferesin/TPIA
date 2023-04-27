import environment.PokeEnvironmentState;

public class Main {
    public static void main(String[] args) {
        PokeEnvironmentState estado = new PokeEnvironmentState();
        estado.initState();
        System.out.println(estado.toString());

    }
}
