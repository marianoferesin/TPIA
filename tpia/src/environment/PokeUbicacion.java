package environment;

import enemigos.PokeEnemigo;
import java.util.Objects;

public class PokeUbicacion {
    private String nombre;
    private PokeEnemigo pokeEnemigo;
    private Boolean esPokeparada;
    private Integer antiguedad;

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
    public PokeUbicacion(PokeUbicacion e) {
        this.nombre = e.nombre;
        this.pokeEnemigo = e.pokeEnemigo;
        this.esPokeparada = e.esPokeparada;
        this.antiguedad = e.antiguedad;
    }

    public Boolean esPokeparada(){
        return this.esPokeparada;
    }

    public PokeEnemigo getPokeEnemigo(){
        return this.pokeEnemigo;
    }

    public void removerPokeEnemigo(){ this.pokeEnemigo.setEnergia(0);}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokeUbicacion that = (PokeUbicacion) o;
        return Objects.equals(nombre, that.nombre);
    }
}
