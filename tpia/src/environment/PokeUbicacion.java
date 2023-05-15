package environment;

import enemigos.PokeEnemigo;

public class PokeUbicacion {
    private String nombre;
    private PokeEnemigo pokeEnemigo;
    private Integer energiaPokeparada;
    private Integer antiguedad;
    private Double xRatio;
    private Double yRatio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PokeUbicacion(String nombre, PokeEnemigo pokeEnemigo, Integer energiaPokeparada, Double ratioX, Double ratioY) {
        this.nombre = nombre;
        this.pokeEnemigo = pokeEnemigo;
        this.energiaPokeparada = energiaPokeparada;
        this.antiguedad = 0;
        this.xRatio = ratioX;
        this.yRatio = ratioY;
    }
    public PokeUbicacion(PokeUbicacion e) {
        this.nombre = e.nombre;
        this.pokeEnemigo = e.pokeEnemigo;
        this.energiaPokeparada = e.energiaPokeparada;
        this.antiguedad = e.antiguedad;
    }

    public Boolean esPokeparada(){
        return this.energiaPokeparada > 0;
    }

    public PokeEnemigo getPokeEnemigo(){
        return this.pokeEnemigo;
    }

    public void removerPokeEnemigo(){ this.pokeEnemigo.setEnergia(0);}

    public void setPokeEnemigo(PokeEnemigo pokeEnemigo) {
        this.pokeEnemigo = pokeEnemigo;
    }

    public void incrementAnt() {
        this.antiguedad += 1;
    }

    public void resetAnt(){
        this.antiguedad = 0;
    }

    public Integer getEnergiaPokeparada() {
        return energiaPokeparada;
    }

    public void setEnergiaPokeparada(Integer energiaPokeparada) {
        this.energiaPokeparada = energiaPokeparada;
    }

    public int getAnt() {return antiguedad;}

    @Override
    public boolean equals(Object o) {
        return this.nombre.equals(((PokeUbicacion) o).getNombre());
    }

    public boolean tieneEnemigo(){
        return (this.pokeEnemigo.getEnergia() != 0);
    }

    @Override
    public String toString() {
        return nombre;
    }
    public PokeUbicacion clone(){
        return (new PokeUbicacion(nombre,pokeEnemigo.clone(),energiaPokeparada,xRatio,yRatio));
    }
    public boolean isBoss(){
        return nombre.equals("Boss");
    }

    // recive en ancho de la imagen y devuelve la ubicación
    public int getx(int x) { return (int) (x * xRatio);    }

    // recive en alto de la imagen y devuelve la ubicación
    public int gety(int y) { return (int) (y * yRatio);    }
    public void usarPokeParada(){
        this.energiaPokeparada = 0;
    }

}
