package GUI;

import FileReaders.FileReaders;
import environment.PokeUbicacion;

import java.util.ArrayList;
import java.util.HashMap;

public class CoordenadasUbicaciones {



    private HashMap<String, Coordenada> MapUbicaciones;

    private HashMap<String, ArrayList<String>> aristas;

    public CoordenadasUbicaciones() {
        this.MapUbicaciones =  new HashMap<>();
        this.MapUbicaciones.put("Alaska",new Coordenada(0.12,0.31));
        this.MapUbicaciones.put("Arabia",new Coordenada(0.62,0.47));
        this.MapUbicaciones.put("Australia",new Coordenada(0.86,0.78));
        this.MapUbicaciones.put("Boss",new Coordenada(0.71,0.83));
        this.MapUbicaciones.put("Brasil",new Coordenada(0.30,0.72));
        this.MapUbicaciones.put("BuenosAires",new Coordenada(0.28,0.81));
        this.MapUbicaciones.put("Canada",new Coordenada(0.28,0.34));
        this.MapUbicaciones.put("Canarias",new Coordenada(0.37,0.52));
        this.MapUbicaciones.put("China",new Coordenada(0.80,0.30));
        this.MapUbicaciones.put("Cuba",new Coordenada(0.25,0.52));
        this.MapUbicaciones.put("EEUU",new Coordenada(0.20,0.40));
        this.MapUbicaciones.put("Egipto",new Coordenada(0.54,0.50));
        this.MapUbicaciones.put("Groenlandia",new Coordenada(0.35,0.17));
        this.MapUbicaciones.put("India",new Coordenada(0.72,0.46));
        this.MapUbicaciones.put("Indonesia",new Coordenada(0.79,0.67));
        this.MapUbicaciones.put("Inglaterra",new Coordenada(0.44,0.34));
        this.MapUbicaciones.put("Japon",new Coordenada(0.88,0.39));
        this.MapUbicaciones.put("Kamchatka",new Coordenada(0.90,0.17));
        this.MapUbicaciones.put("Mexico",new Coordenada(0.17,0.51));
        this.MapUbicaciones.put("Moscu",new Coordenada(0.62,0.22));
        this.MapUbicaciones.put("Noruega",new Coordenada(0.50,0.16));
        this.MapUbicaciones.put("NuevaGuinea",new Coordenada(0.88,0.67));
        this.MapUbicaciones.put("NuevaZelanda",new Coordenada(0.97,0.91));
        this.MapUbicaciones.put("Peru",new Coordenada(0.24,0.70));
        this.MapUbicaciones.put("Sahara",new Coordenada(0.44,0.54));
        this.MapUbicaciones.put("Siberia",new Coordenada(0.74,0.19));
        this.MapUbicaciones.put("Sudafrica",new Coordenada(0.54,0.80));
        this.MapUbicaciones.put("Suecia",new Coordenada(0.51,0.25));
        this.MapUbicaciones.put("TierraDelFuego",new Coordenada(0.25,0.90));

        this.aristas =  new HashMap<>();
        ArrayList <String> ubicaciones = FileReaders.leerUbicaciones();
        for(String ubi: ubicaciones){
            this.aristas.put(ubi,new ArrayList<String>());
        }
        ArrayList <ArrayList<String>> fileAristas = FileReaders.leerAristas();

        for(ArrayList<String> a: fileAristas) {
            this.aristas.get(a.get(0)).add(a.get(1));
        }

    }

    public int getx(String nombre, int x){
       return ( (Coordenada) (this.MapUbicaciones.get(nombre))).getx(x);

    }

    public int gety(String nombre, int y){
        return ( (Coordenada) (this.MapUbicaciones.get(nombre))).gety(y);

    }

    public HashMap<String, Coordenada> getMapUbicaciones() {
        return MapUbicaciones;
    }

    public HashMap<String, ArrayList<String>> getAristas() {
        return aristas;
    }
}
