package environment;

import enemigos.PokeEnemigo;
import java.util.Objects;

public class PokeUbicacion {
    private String nombre;
    private PokeEnemigo pokeEnemigo;
    private Boolean esPokeparada;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PokeUbicacion(String nombre, PokeEnemigo pokeEnemigo, Boolean esPokeparada) {
        this.nombre = nombre;
        this.pokeEnemigo = pokeEnemigo;
        this.esPokeparada = esPokeparada;
    }
}
