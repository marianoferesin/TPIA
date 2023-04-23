package environment;

import java.util.Objects;

public class PokeUbicacion {
    private Ubicacion ubicacion;
    private Integer energia;
    private Integer antiguedad;
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

    public PokeUbicacion(Ubicacion ubicacion, Integer energia, Integer antiguedad, Integer energiaPokeparada) {
        this.ubicacion = ubicacion;
        this.energia = energia;
        this.antiguedad = antiguedad;
        this.energiaPokeparada = energiaPokeparada;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getEnergia() {
        return energia;
    }

    public void setEnergia(Integer energia) {
        this.energia = energia;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
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
        return ubicacion.toString() + " ; " +energiaPokeparada.toString();
    }
}
