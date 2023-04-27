package environment;

import enemigos.PokeEnemigo;
import java.util.Objects;

public class PokeUbicacion {
    private String nombre;
    private PokeEnemigo pokeEnemigo;
    private Boolean esPokeparada;

    private int antiguedad;

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
        this.antiguedad = 0;
    }

    public Boolean esPokeparada(){
        return this.esPokeparada;
    }

    public PokeEnemigo getPokeEnemigo(){
        return this.pokeEnemigo;
    }

    public void removerPokeEnemigo(){ this.pokeEnemigo = null;}

    public void setPokeEnemigo(PokeEnemigo pokeEnemigo) {
        this.pokeEnemigo = pokeEnemigo;
    }

    public PokeUbicacion incrementAnt() {
        this.antiguedad += 1;
        return this;
    }

    public PokeUbicacion resetAnt(){
        this.antiguedad = 0;
        return this;
    }

    public int getAnt() {return antiguedad;}
}
