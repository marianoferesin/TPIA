package environment;

import enemigos.PokeEnemigo;

public class Main {

    public static void main(String[] args) {
        PokeEnvironmentState pokeEnvironmentState = new PokeEnvironmentState();
        pokeEnvironmentState.initState();

        System.out.println("cant "+pokeEnvironmentState.getCantidadEnemigos().toString());
        System.out.println("cant "+pokeEnvironmentState.getCantidadEnemigos().toString());
        System.out.println(pokeEnvironmentState.getPokeUbicaciones().toString());



    }
}
