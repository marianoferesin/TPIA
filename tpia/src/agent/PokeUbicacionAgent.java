package agent;

import enemigos.PokeEnemigo;

public class PokeUbicacionAgent {
    private String nombre;
    private PokeEnemigo pokeEnemigo;
    private Integer antiguedad;
    private Boolean esPokeparada;

    public PokeUbicacionAgent(String nombre,PokeEnemigo pokeEnemigo,Integer antiguedad,Boolean esPokeparada){
        this.nombre = nombre;
        this.pokeEnemigo = pokeEnemigo;
        this.antiguedad = antiguedad;
        this.esPokeparada = esPokeparada;
    }
    public String getNombre(){
        return this.nombre;
    }
}
