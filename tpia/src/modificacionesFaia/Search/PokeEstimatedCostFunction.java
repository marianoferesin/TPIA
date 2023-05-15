package modificacionesFaia.Search;

import agent.PokeAgentState;
import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class PokeEstimatedCostFunction implements IEstimatedCostFunction {
    @Override
    public double getEstimatedCost(NTree nTree) {
        double rtn = 0;
        if(nTree.getParent() != null){
            switch (((PokeAgentState) nTree.getParent().getAgentState()).getPokeUbicacion().getNombre()) {
                case ("TierraDelFuego") -> {
                    switch (nTree.getAction().toString()) {
                        case ("AtacarColapsado") -> rtn = -1;
                        case ("GoToBuenosAires") -> rtn = -2;
                        case ("GoToPeru") -> rtn=-3;
                    }
                }
                case ("BuenosAires") -> {
                    switch (nTree.getAction().toString()) {
                        case ("AtacarColapsado") ->rtn = -1;
                        case ("GoToTierraDelFuego") -> rtn = -2;
                        case ("GoToBrasil") -> rtn = -3;
                    }
                }
                case ("Brasil") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToBuenosAires") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToPeru") -> rtn = -3;
                    }
                }
                case ("Peru") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToBrasil") -> rtn = -1;
                        case ("GoToTierraDelFuego") -> rtn = -2;
                        case ("AtacarColapsado") -> rtn = -3;
                        case ("GoToMexico") -> {
                            if(((PokeAgentState) nTree.getAgentState()).leGanoAlBoss()){
                                rtn = -4;
                            }else {
                                rtn = -5;
                            }
                        }
                        case ("GoToCanarias") -> {
                            if(((PokeAgentState) nTree.getAgentState()).leGanoAlBoss()){
                                rtn = -5;
                            }else{
                                rtn = -5;
                            }
                        }
                    }
                }
                case ("Mexico") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToPeru") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToCuba") -> {
                            //Creo que la segunda condicion va a dar true siempre, tengo que prestar atencion a esta parte...
                            if(((PokeAgentState) nTree.getAgentState()).leGanoAlBoss() || ((PokeAgentState) nTree.getAgentState()).esPokeparada("Cuba") ){
                                rtn = -5;
                            }else{
                                rtn = -4;
                            }
                        }
                        case ("GoToEEUU") -> {
                            if(((PokeAgentState) nTree.getAgentState()).esPokeparada("Cuba") ){
                                rtn = -3;
                            }else{
                                rtn = -4;
                            }
                        }
                    }
                }
                case ("Cuba") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToMexico") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToCanarias") -> rtn = -3;
                    }
                }
                case ("Canarias") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToPeru") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToCanada") -> {
                            if(!((PokeAgentState) nTree.getAgentState()).leGanoAlBoss() && !((PokeAgentState) nTree.getAgentState()).esPokeparada("Cuba")){
                                rtn = -5;
                            }else{
                                rtn = -3;
                            }
                        }
                        case ("GoToCuba") -> {
                            if(!((PokeAgentState) nTree.getAgentState()).leGanoAlBoss() && ((PokeAgentState) nTree.getAgentState()).esPokeparada("Cuba")){
                                rtn = -5;
                            }else if(((PokeAgentState) nTree.getAgentState()).esPokeparada("Cuba")){
                                rtn = -4;
                            }else{
                                rtn = -2;
                            }
                        }
                        case ("GoToSahara") -> {
                            if(((PokeAgentState) nTree.getAgentState()).leGanoAlBoss()){
                                rtn = -5;
                            }else{
                                rtn = -2;
                            }
                        }
                    }
                }
                case ("Alaska") -> {
                    switch (nTree.getAction().toString()) {
                        case ("AtacarColapsado") -> rtn = -1;
                        case ("GoToEEUU") -> rtn = -2;
                    }
                }
                case ("EEUU") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToAlaska") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToMexico") -> rtn = -3;
                        case ("GoToCanada") -> rtn = -4;
                    }
                }
                case ("Canada") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToEEUU") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToGroenlandia") -> {
                            if(!((PokeAgentState) nTree.getAgentState()).leGanoAlBoss() && ((PokeAgentState) nTree.getAgentState()).esPokeparada("Groenlandia")){
                                rtn = -5;
                            }else{
                                rtn = -3;
                            }
                        }
                        case ("GoToInglaterra") -> {
                            if(!((PokeAgentState) nTree.getAgentState()).leGanoAlBoss() && !((PokeAgentState) nTree.getAgentState()).esPokeparada("Groenlandia")){
                                rtn = -5;
                            }else{
                                rtn = -3;
                            }
                        }
                        case ("GoToCanarias") -> {
                            if(((PokeAgentState) nTree.getAgentState()).leGanoAlBoss()){
                                rtn = -5;
                            }else{
                                rtn = -2;
                            }
                        }
                    }
                }
                case ("Groenlandia") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToCanada") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToNoruega") -> rtn = -3;
                        case ("GoToInglaterra") -> rtn = -4;
                    }
                }
                case ("Sahara") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToCanarias") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToEgipto") -> rtn = -3;
                    }
                }
                case ("Inglaterra") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToCanada") -> rtn = -1;
                        case ("GoToGroenlandia") -> rtn = -2;
                        case ("AtacarColapsado") -> rtn = -3;
                        case ("GoToSuecia") -> rtn = -4;
                    }
                }
                case ("Noruega") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToGroenlandia") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToMoscu") -> rtn = -3;
                    }
                }
                case ("Sudafrica") -> {
                    switch (nTree.getAction().toString()) {
                        case ("AtacarColapsado") -> rtn = -1;
                        case ("GoToEgipto") -> rtn = -2;
                        case ("GoToBoss") -> {
                            if(((PokeAgentState) nTree.getAgentState()).leGanoAlBoss()){
                                rtn = -3;
                            }else{
                                rtn = 0;
                            }
                        }
                    }
                }
                case ("Egipto") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToSahara") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToSuecia") -> rtn = -3;
                        case ("GoToMoscu") -> rtn = -4;
                        case ("GoToSudafrica") -> rtn = -5;
                    }
                }
                case ("Suecia") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToInglaterra") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToEgipto") -> rtn = -3;
                        case ("GoToMoscu") -> rtn = -4;
                    }
                }
                case ("Arabia") -> {
                    switch (nTree.getAction().toString()) {
                        case ("AtacarColapsado") -> rtn = -1;
                        case ("GoToIndia") -> {
                            if(!((PokeAgentState) nTree.getAgentState()).leGanoAlBoss()){
                                rtn = -3;
                            }else{
                                rtn = -2;
                            }
                        }
                        case ("GoToBoss") -> {
                            if(((PokeAgentState) nTree.getAgentState()).leGanoAlBoss()){
                                rtn = -3;
                            }else{
                                rtn = 0;
                            }
                        }
                    }
                }
                case ("Moscu") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToNoruega"), ("GoToSuecia") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToEgipto") -> rtn = -3;
                        case ("GoToSiberia") -> {
                            if(!((PokeAgentState) nTree.getAgentState()).leGanoAlBoss() && ((PokeAgentState) nTree.getAgentState()).esPokeparada("Siberia")){
                                rtn = -6;
                            }else{
                                rtn = -4;
                            }
                        }
                        case ("GoToIndia") -> {
                            if(((PokeAgentState) nTree.getAgentState()).leGanoAlBoss() || !((PokeAgentState) nTree.getAgentState()).esPokeparada("Siberia")){
                                rtn = -6;
                            }else{
                                rtn = -5;
                            }
                        }
                    }
                }
                case ("India") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToMoscu") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToJapon"), ("GoToIndonesia") -> {
                            if(!((PokeAgentState) nTree.getAgentState()).leGanoAlBoss() && !((PokeAgentState) nTree.getAgentState()).esPokeparada("Arabia")){
                                rtn = -5;
                            }else{
                                rtn = -4;
                            }
                        }
                        case ("GoToArabia") -> {
                            if(((PokeAgentState) nTree.getAgentState()).leGanoAlBoss() || ((PokeAgentState) nTree.getAgentState()).esPokeparada("Arabia")){
                                rtn = -5;
                            }else{
                                rtn = -2;
                            }
                        }
                    }
                }
                case ("Siberia") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToKamchatka") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToMoscu") -> rtn = -3;
                        case ("GoToChina") -> rtn = -4;
                    }
                }
                case ("Indonesia") -> {
                    switch (nTree.getAction().toString()) {
                        case ("AtacarColapsado") -> rtn = -1;
                        case ("GoToIndia") -> rtn = -2;
                        case ("GoToAustralia") -> {
                            if(!((PokeAgentState) nTree.getAgentState()).leGanoAlBoss() && ((PokeAgentState) nTree.getAgentState()).esPokeparada("NuevaZelanda")){
                                rtn = -3;
                            }else{
                                rtn = -1;
                            }
                        }
                    }
                }
                case ("China") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToSiberia") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToJapon") -> rtn = -3;
                    }
                }
                case ("NuevaZelanda") -> {
                    switch (nTree.getAction().toString()) {
                        case ("AtacarColapsado") -> rtn = -1;
                        case ("GoToNuevaGuinea") -> rtn = -2;
                        case ("GoToAustralia") -> rtn = -3;
                    }
                }
                case ("Australia") -> {
                    switch (nTree.getAction().toString()) {
                        case ("AtacarColapsado") -> rtn = -1;
                        case ("GoToIndonesia") -> rtn = -2;
                        case ("GoToNuevaZelanda") -> {
                            if(!((PokeAgentState) nTree.getAgentState()).leGanoAlBoss() && ((PokeAgentState) nTree.getAgentState()).esPokeparada("NuevaZelanda")){
                                rtn = -4;
                            }else{
                                rtn = -1;
                            }
                        }
                        case ("GoToBoss") -> {
                            if(((PokeAgentState) nTree.getAgentState()).leGanoAlBoss()){
                                rtn = -4;
                            }else{
                                rtn = 0;
                            }
                        }
                    }
                }
                case ("NuevaGuinea") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToJapon") -> rtn = -1;
                        case ("AtacarColapsado") -> rtn = -2;
                        case ("GoToNuevaZelanda") -> rtn = -3;
                    }
                }
                case ("Japon") -> {
                    switch (nTree.getAction().toString()) {
                        case ("GoToChina") -> rtn = -1;
                        case ("GoToIndia") -> rtn = -2;
                        case ("AtacarColapsado") -> rtn = -3;
                        case ("GoToNuevaGuinea") -> rtn = -4;
                    }
                }
                case ("Kamchatka") -> {
                    switch (nTree.getAction().toString()) {
                        case ("AtacarColapsado") -> rtn = -1;
                        case ("GoToSiberia") -> rtn = -2;
                    }
                }
                default -> {
                    rtn= -1;
                }
            }
        }
        return rtn;
    }
}
