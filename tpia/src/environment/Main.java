package environment;

import enemigos.PokeEnemigo;

public class Main {

    public static void main(String[] args) {
        PokeEnvironmentState pokeEnvironmentState = new PokeEnvironmentState(10,1,10);
        pokeEnvironmentState.initState();
        System.out.println(pokeEnvironmentState.getPokeUbicaciones().toString());
    }
}
