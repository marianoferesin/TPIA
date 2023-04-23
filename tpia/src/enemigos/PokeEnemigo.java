package enemigos;

import environment.Ubicacion;

import java.util.Random;

public class PokeEnemigo {
    private Integer energia;
    private String ubicacion;

    public PokeEnemigo(String ubicacion) {
        Random rand = new Random();
        this.energia = rand.nextInt()%100 + 1 ; //QUEDA VER CUANTA ENERGIA PUEDEN LLEGAR A TENER
        if(this.energia <0) this.energia+=100;
        System.out.println("ene: " + this.energia.toString());

        this.ubicacion = ubicacion;
        System.out.println("ubi: " + this.ubicacion);
    }

    public String toString(){
        return this.energia.toString();
    }
}
