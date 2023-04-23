package enviroment;

import java.util.ArrayList;

public enum Ubicacion {
    TierraDelFuego,
    BuenosAires,
    Brasil,
    Peru,
    Cuba,
    Mexico,
    EEUU,
    Canada,
    Alaska,
    Groenlandia,
    Canarias,
    Sahara,
    Inglaterra,
    Noruega,
    Suecia,
    Egipto,
    Arabia,
    SurAfrica,
    Moscu,
    Siberia,
    Kamchatka,
    China,
    India,
    Japon,
    Indonesia,
    NuevaGuinea,
    Australia,
    NuevaZelanda,
    Boss;

    public static ArrayList<Ubicacion> getPokeParadas(){
        ArrayList<Ubicacion> pokeParadas = new ArrayList<>();
        pokeParadas.add(Ubicacion.Cuba);
        pokeParadas.add(Ubicacion.Groenlandia);
        pokeParadas.add(Ubicacion.Arabia);
        pokeParadas.add(Ubicacion.Siberia);
        pokeParadas.add(Ubicacion.NuevaZelanda);
        return pokeParadas;
    }

}
