package environment;

public class Main {

    public static void main(String[] args) {
        PokeEnvironmentState pokeEnvironmentState = new PokeEnvironmentState();
        pokeEnvironmentState.initState();

        System.out.println(pokeEnvironmentState.getPokeUbicaciones().toString());


    }
}
