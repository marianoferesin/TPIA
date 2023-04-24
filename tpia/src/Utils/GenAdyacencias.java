package Utils;

import environment.Ubicacion;

public class GenAdyacencias {
    public static Ubicacion[][] genAdyacencias(){
        return new Ubicacion[][]{
                {Ubicacion.TierraDelFuego,Ubicacion.BuenosAires,Ubicacion.Peru},
                {Ubicacion.BuenosAires, Ubicacion.TierraDelFuego, Ubicacion.Brasil},
                {Ubicacion.Brasil, Ubicacion.BuenosAires, Ubicacion.Peru},
                {Ubicacion.Peru,Ubicacion.TierraDelFuego,Ubicacion.Brasil,Ubicacion.Mexico,Ubicacion.Canarias},
                {Ubicacion.Mexico,Ubicacion.Peru,Ubicacion.Cuba,Ubicacion.EEUU},
                {Ubicacion.EEUU,Ubicacion.Mexico,Ubicacion.Canada,Ubicacion.Alaska},
                {Ubicacion.Alaska,Ubicacion.EEUU},
                {Ubicacion.Canada,Ubicacion.EEUU,Ubicacion.Canarias,Ubicacion.Inglaterra,Ubicacion.Groenlandia},
                {Ubicacion.Cuba,Ubicacion.Mexico,Ubicacion.Canarias},
                {Ubicacion.Canarias,Ubicacion.Peru,Ubicacion.Cuba,Ubicacion.Canada,Ubicacion.Sahara},
                {Ubicacion.Sahara,Ubicacion.Canarias,Ubicacion.Egipto},
                {Ubicacion.Egipto,Ubicacion.Sahara,Ubicacion.SurAfrica,Ubicacion.Suecia,Ubicacion.Moscu},
                {Ubicacion.SurAfrica,Ubicacion.Egipto, Ubicacion.Boss},
                {Ubicacion.Boss, Ubicacion.SurAfrica, Ubicacion.Australia, Ubicacion.Arabia},
                {Ubicacion.Australia,Ubicacion.Boss, Ubicacion.Indonesia, Ubicacion.NuevaZelanda},
                {Ubicacion.NuevaZelanda, Ubicacion.Australia, Ubicacion.NuevaGuinea},
                {Ubicacion.NuevaGuinea, Ubicacion.NuevaZelanda, Ubicacion.Japon},
                {Ubicacion.Japon, Ubicacion.NuevaGuinea,Ubicacion.India, Ubicacion.China},
                {Ubicacion.India,Ubicacion.Arabia, Ubicacion.Indonesia,Ubicacion.Japon,Ubicacion.Moscu},
                {Ubicacion.Indonesia,Ubicacion.Australia,Ubicacion.India},
                {Ubicacion.Arabia,Ubicacion.Boss, Ubicacion.India},
                {Ubicacion.China,Ubicacion.Siberia,Ubicacion.Japon},
                {Ubicacion.Siberia,Ubicacion.Kamchatka,Ubicacion.China,Ubicacion.Moscu},
                {Ubicacion.Kamchatka,Ubicacion.Siberia},
                {Ubicacion.Moscu,Ubicacion.Noruega, Ubicacion.Siberia,Ubicacion.Suecia,Ubicacion.Egipto,Ubicacion.India},
                {Ubicacion.Noruega,Ubicacion.Moscu,Ubicacion.Groenlandia},
                {Ubicacion.Suecia, Ubicacion.Inglaterra,Ubicacion.Egipto,Ubicacion.Moscu},
                {Ubicacion.Inglaterra,Ubicacion.Suecia,Ubicacion.Canada, Ubicacion.Groenlandia},
                {Ubicacion.Groenlandia,Ubicacion.Canada,Ubicacion.Inglaterra, Ubicacion.Noruega}
        };
    }
}
