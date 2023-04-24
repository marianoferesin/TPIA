package environment;

import enemigos.PokeEnemigo;
import java.util.Objects;

public class PokeUbicacion {
    private Ubicacion ubicacion;
    private PokeEnemigo pokeEnemigo;
    private Integer energiaPokeparada;

    public PokeUbicacion(){};
    public PokeUbicacion(Ubicacion ubicacion){
        this.ubicacion = ubicacion;
        this.pokeEnemigo = null;
        this.energiaPokeparada = 0;
    }
    public PokeUbicacion(Ubicacion ubicacion,Integer energiaPokeparada){
        this.ubicacion = ubicacion;
        this.pokeEnemigo = null;
        this.energiaPokeparada = energiaPokeparada;
    }
    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public PokeEnemigo getEnemigo() {
        return pokeEnemigo;
    }

    public Integer getEnergiaPokeparada() {
        return energiaPokeparada;
    }

    public void setEnergiaPokeparada(Integer energiaPokeparada) {
        this.energiaPokeparada = energiaPokeparada;
    }

    @Override
    public boolean equals(Object o) {
        PokeUbicacion that = (PokeUbicacion) o;
        return Objects.equals(ubicacion.toString(), that.getUbicacion().toString());
    }

    @Override
    public String toString() {
        return "{" + ubicacion.toString() + "; Enemigo: "+ (pokeEnemigo==null?"null":pokeEnemigo.toString()) + " ; PokeParada: " +energiaPokeparada.toString() + "}";
    }

    public boolean esPokeParada() {
        return this.energiaPokeparada > 0;
    }

    public void setEnemigo(PokeEnemigo enemigo) {
        this.pokeEnemigo = enemigo;
    }
}
