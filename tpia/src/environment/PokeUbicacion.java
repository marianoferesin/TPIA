package environment;

import enemigos.PokeEnemigo;

import java.security.cert.PolicyNode;
import java.util.Objects;

public class PokeUbicacion {
    private Ubicacion ubicacion;
    private PokeEnemigo enemigo;
    private Integer energiaPokeparada;

    public PokeUbicacion(){};
    public PokeUbicacion(Ubicacion ubicacion){
        this.ubicacion = ubicacion;
        this.energiaPokeparada = 0;
    };
    public PokeUbicacion(Ubicacion ubicacion,Integer energiaPokeparada){
        this.ubicacion = ubicacion;
        this.energiaPokeparada = energiaPokeparada;
    };

    public PokeUbicacion(Ubicacion ubicacion, PokeEnemigo enemigo, Integer antiguedad, Integer energiaPokeparada) {
        this.ubicacion = ubicacion;
        this.enemigo = enemigo;

        this.energiaPokeparada = energiaPokeparada;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public PokeEnemigo getEnemigo() {
        return enemigo;
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
        if(this.enemigo != null)
            return ubicacion.toString() + " Enemigo: "+ enemigo.toString() + " ; PokeParada: " +energiaPokeparada.toString()  ;
        return ubicacion.toString() + " Enemigo: 0;  PokeParada: " +energiaPokeparada.toString()  ;
    }

    public boolean esPokeParada() {
        return this.energiaPokeparada > 0;
    }

    public void setEnemigo(PokeEnemigo enemigo) {
        this.enemigo = enemigo;
    }
}
