package enviroment;

import Utils.GenPokeUbicaciones;
import frsf.cidisi.faia.state.EnvironmentState;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class PokeEnvironmentState extends EnvironmentState {
    private Collection<PokeUbicacion> pokeUbicaciones;
    private HashMap<String, Collection<String>> map;

    public static final String[][] ADYACENCIA = new String[][]{
            {Ubicacion.TierraDelFuego.toString(),Ubicacion.BuenosAires.toString(),Ubicacion.Peru.toString()},
            {Ubicacion.BuenosAires.toString(), Ubicacion.TierraDelFuego.toString(), Ubicacion.Brasil.toString()},
            {Ubicacion.Brasil.toString(), Ubicacion.BuenosAires.toString(), Ubicacion.Peru.toString()},
            {Ubicacion.Peru.toString(),Ubicacion.TierraDelFuego.toString(),Ubicacion.Brasil.toString(),Ubicacion.Mexico.toString(),Ubicacion.Canarias.toString()},
            {Ubicacion.Mexico.toString(),Ubicacion.Peru.toString(),Ubicacion.Cuba.toString(),Ubicacion.EEUU.toString()},
            {Ubicacion.EEUU.toString(),Ubicacion.Mexico.toString(),Ubicacion.Canada.toString(),Ubicacion.Alaska.toString()},
            {Ubicacion.Alaska.toString(),Ubicacion.EEUU.toString()},
            {Ubicacion.Canada.toString(),Ubicacion.EEUU.toString(),Ubicacion.Canarias.toString(),Ubicacion.Inglaterra.toString(),Ubicacion.Groenlandia.toString()},
            {Ubicacion.Cuba.toString(),Ubicacion.Mexico.toString(),Ubicacion.Canarias.toString()},
            {Ubicacion.Canarias.toString(),Ubicacion.Peru.toString(),Ubicacion.Cuba.toString(),Ubicacion.Canada.toString(),Ubicacion.Sahara.toString()},
            {Ubicacion.Sahara.toString(),Ubicacion.Canarias.toString(),Ubicacion.Egipto.toString()},
            {Ubicacion.Egipto.toString(),Ubicacion.Sahara.toString(),Ubicacion.SurAfrica.toString(),Ubicacion.Suecia.toString(),Ubicacion.Moscu.toString()},
            {Ubicacion.SurAfrica.toString(),Ubicacion.Egipto.toString(), Ubicacion.Boss.toString()},
            {Ubicacion.Boss.toString(), Ubicacion.SurAfrica.toString(), Ubicacion.Australia.toString(), Ubicacion.Arabia.toString()},
            {Ubicacion.Australia.toString(),Ubicacion.Boss.toString(), Ubicacion.Indonesia.toString(), Ubicacion.NuevaZelanda.toString()},
            {Ubicacion.NuevaZelanda.toString(), Ubicacion.Australia.toString(), Ubicacion.NuevaGuinea.toString()},
            {Ubicacion.NuevaGuinea.toString(), Ubicacion.NuevaZelanda.toString(), Ubicacion.Japon.toString()},
            {Ubicacion.Japon.toString(), Ubicacion.NuevaGuinea.toString(),Ubicacion.India.toString(), Ubicacion.China.toString()},
            {Ubicacion.India.toString(),Ubicacion.Arabia.toString(), Ubicacion.Indonesia.toString(),Ubicacion.Japon.toString(),Ubicacion.Moscu.toString()},
            {Ubicacion.Indonesia.toString(),Ubicacion.Australia.toString(),Ubicacion.India.toString()},
            {Ubicacion.Arabia.toString(),Ubicacion.Boss.toString(), Ubicacion.India.toString()},
            {Ubicacion.China.toString(),Ubicacion.Siberia.toString(),Ubicacion.Japon.toString()},
            {Ubicacion.Siberia.toString(),Ubicacion.Kamchatka.toString(),Ubicacion.China.toString(),Ubicacion.Moscu.toString()},
            {Ubicacion.Kamchatka.toString(),Ubicacion.Siberia.toString()},
            {Ubicacion.Moscu.toString(),Ubicacion.Noruega.toString(), Ubicacion.Siberia.toString(),Ubicacion.Suecia.toString(),Ubicacion.Egipto.toString(),Ubicacion.India.toString()},
            {Ubicacion.Noruega.toString(),Ubicacion.Moscu.toString(),Ubicacion.Groenlandia.toString()},
            {Ubicacion.Suecia.toString(), Ubicacion.Inglaterra.toString(),Ubicacion.Egipto.toString(),Ubicacion.Moscu.toString()},
            {Ubicacion.Inglaterra.toString(),Ubicacion.Suecia.toString(),Ubicacion.Canada.toString(), Ubicacion.Groenlandia.toString()},
            {Ubicacion.Groenlandia.toString(),Ubicacion.Canada.toString(),Ubicacion.Inglaterra.toString(), Ubicacion.Noruega.toString()}
    };
    public PokeEnvironmentState(){
        this.map = new HashMap<>();
        this.pokeUbicaciones = new ArrayList<>();
    }
    @Override
    public void initState() {
        //Genera lista de adyacencias
        for (int i = 0; i < ADYACENCIA.length; i++) {
            ArrayList<String> successors = new ArrayList<>();
            for (int j = 1; j < ADYACENCIA[i].length; j++) {
                successors.add(ADYACENCIA[i][j]);
            }
            map.put(ADYACENCIA[i][0], successors);
        }
        //Genera pokeubicaciones
        pokeUbicaciones = GenPokeUbicaciones.genPokeUbicaciones();
        //TODO
        //FALTA GENERADOR DE ENEMIGOS



    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        return map.clone();
    }

    @Override
    public String toString() {
        String str = "";

        str = str + "[ \n";
        for (String point : map.keySet()) {
            str = str + "[ " + point + " --> ";
            Collection<String> successors = map.get(point);
            if (successors != null) {
                for (String successor : successors) {
                    str = str + successor + " ";
                }
            }
            str = str + " ]\n";
        }
        str = str + " ]";

        return str;
    }

    public Collection<PokeUbicacion> getPokeUbicaciones() {
        return pokeUbicaciones;
    }

    public HashMap<String, Collection<String>> getMap() {
        return map;
    }
}
