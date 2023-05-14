package modificacionesFaia.Search;

import agent.PokeAgentState;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.solver.search.Strategy;
import java.util.Vector;
public class BusquedaInformadaPrueba extends Strategy {
    private boolean isInitialized;

    public BusquedaInformadaPrueba() {
        isInitialized = false;
    }
    @Override
    /**
     * Este algoritmo de busqueda solo funciona si el agente comienza desde tierra del fuego
     * Habria que completar los condicionales de todas las ubicaciones para que el agente pueda ser colocado en cualqueir punto del mapa.
     * Porque, por ejemplo, suecia, inglaterra y noruega siempre trataran de mover el agente hacia moscu mediante las condiciones actuales
     * Completando las condiciones de dichas ubicaciones el agente podria empezar en kamchatka tambiem.
     */
    public void addNodesToExpand(Vector<NTree> nodes){
        for (NTree nt : nodes) {
            if(nt.getParent() != null){
                switch (((PokeAgentState) nt.getParent().getAgentState()).getPokeUbicacion().getNombre()) {
                    case ("TierraDelFuego") -> {
                        switch (nt.getAction().toString()) {
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToBuenosAires") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToPeru") -> nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("BuenosAires") -> {
                        switch (nt.getAction().toString()) {
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToTierraDelFuego") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToBrasil") -> nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Brasil") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToBuenosAires") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToPeru") -> nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Peru") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToBrasil") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToTierraDelFuego") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 3);
                            case ("GoToMexico") -> {
                                if(((PokeAgentState) nt.getAgentState()).leGanoAlBoss()){
                                    nt.setCost(nt.getParent().getCost() - 4);
                                }else {
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }
                            }
                            case ("GoToCanarias") -> {
                                if(((PokeAgentState) nt.getAgentState()).leGanoAlBoss()){
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }
                            }
                        }
                    }
                    case ("Mexico") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToPeru") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToCuba") -> {
                                //Creo que la segunda condicion va a dar true siempre, tengo que prestar atencion a esta parte...
                                if(((PokeAgentState) nt.getAgentState()).leGanoAlBoss() || ((PokeAgentState) nt.getAgentState()).esPokeparada("Cuba") ){
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 4);
                                }
                            }
                            case ("GoToEEUU") -> {
                                if(((PokeAgentState) nt.getAgentState()).esPokeparada("Cuba") ){
                                    nt.setCost(nt.getParent().getCost() - 3);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 4);
                                }
                            }
                        }
                    }
                    case ("Cuba") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToMexico") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToCanarias") -> nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Canarias") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToPeru") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToCanada") -> {
                                if(!((PokeAgentState) nt.getAgentState()).leGanoAlBoss() && !((PokeAgentState) nt.getAgentState()).esPokeparada("Cuba")){
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 3);
                                }
                            }
                            case ("GoToCuba") -> {
                                if(!((PokeAgentState) nt.getAgentState()).leGanoAlBoss() && ((PokeAgentState) nt.getAgentState()).esPokeparada("Cuba")){
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }else if(((PokeAgentState) nt.getAgentState()).esPokeparada("Cuba")){
                                    nt.setCost(nt.getParent().getCost() - 4);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 2);
                                }
                            }
                            case ("GoToSahara") -> {
                                if(((PokeAgentState) nt.getAgentState()).leGanoAlBoss()){
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 2);
                                }
                            }
                        }
                    }
                    case ("Alaska") -> {
                        switch (nt.getAction().toString()) {
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToEEUU") -> nt.setCost(nt.getParent().getCost() - 2);
                        }
                    }
                    //falta este
                    case ("EEUU") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToAlaska") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToMexico") -> nt.setCost(nt.getParent().getCost() - 3);
                            case ("GoToCanada") -> nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("Canada") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToEEUU") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToGroenlandia") -> {
                                if(!((PokeAgentState) nt.getAgentState()).leGanoAlBoss() && ((PokeAgentState) nt.getAgentState()).esPokeparada("Groenlandia")){
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 3);
                                }
                            }
                            case ("GoToInglaterra") -> {
                                if(!((PokeAgentState) nt.getAgentState()).leGanoAlBoss() && !((PokeAgentState) nt.getAgentState()).esPokeparada("Groenlandia")){
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 3);
                                }
                            }
                            case ("GoToCanarias") -> {
                                if(((PokeAgentState) nt.getAgentState()).leGanoAlBoss()){
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 2);
                                }
                            }
                        }
                    }
                    case ("Groenlandia") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToCanada") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToNoruega") -> nt.setCost(nt.getParent().getCost() - 4);
                            case ("GoToInglaterra") -> nt.setCost(nt.getParent().getCost() - 5);
                        }
                    }
                    case ("Sahara") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToCanarias") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToEgipto") -> nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("Inglaterra") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToCanada") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToGroenlandia") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 3);
                            case ("GoToSuecia") -> nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("Noruega") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToGroenlandia") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToMoscu") -> nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Sudafrica") -> {
                        switch (nt.getAction().toString()) {
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToEgipto") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToBoss") -> {
                                if(((PokeAgentState) nt.getAgentState()).leGanoAlBoss()){
                                    nt.setCost(nt.getParent().getCost() - 3);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 0);
                                }
                            }
                        }
                    }
                    case ("Egipto") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToSahara") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToSuecia") -> nt.setCost(nt.getParent().getCost() - 3);
                            case ("GoToMoscu") -> nt.setCost(nt.getParent().getCost() - 4);
                            case ("GoToSudafrica") -> nt.setCost(nt.getParent().getCost() - 5);
                        }
                    }
                    case ("Suecia") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToInglaterra") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToEgipto") -> nt.setCost(nt.getParent().getCost() - 3);
                            case ("GoToMoscu") -> nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("Arabia") -> {
                        switch (nt.getAction().toString()) {
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToIndia") -> {
                                if(!((PokeAgentState) nt.getAgentState()).leGanoAlBoss()){
                                    nt.setCost(nt.getParent().getCost() - 3);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 2);
                                }
                            }
                            case ("GoToBoss") -> {
                                if(((PokeAgentState) nt.getAgentState()).leGanoAlBoss()){
                                    nt.setCost(nt.getParent().getCost() - 3);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 0);
                                }
                            }
                        }
                    }
                    case ("Moscu") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToNoruega") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToSuecia") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToEgipto") -> nt.setCost(nt.getParent().getCost() - 3);
                            case ("GoToSiberia") -> {
                                if(!((PokeAgentState) nt.getAgentState()).leGanoAlBoss() && ((PokeAgentState) nt.getAgentState()).esPokeparada("Siberia")){
                                    nt.setCost(nt.getParent().getCost() - 6);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 4);
                                }
                            }
                            case ("GoToIndia") -> {
                                if(((PokeAgentState) nt.getAgentState()).leGanoAlBoss() || !((PokeAgentState) nt.getAgentState()).esPokeparada("Siberia")){
                                    nt.setCost(nt.getParent().getCost() - 6);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }
                            }
                        }
                    }
                    case ("India") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToMoscu") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToJapon"), ("GoToIndonesia") -> {
                                if(!((PokeAgentState) nt.getAgentState()).leGanoAlBoss() && !((PokeAgentState) nt.getAgentState()).esPokeparada("Arabia")){
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 4);
                                }
                            }
                            case ("GoToArabia") -> {
                                if(((PokeAgentState) nt.getAgentState()).leGanoAlBoss() || ((PokeAgentState) nt.getAgentState()).esPokeparada("Arabia")){
                                    nt.setCost(nt.getParent().getCost() - 5);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 2);
                                }
                            }
                        }
                    }
                    case ("Siberia") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToKamchatka") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToMoscu") -> nt.setCost(nt.getParent().getCost() - 3);
                            case ("GoToChina") -> nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("Indonesia") -> {
                        switch (nt.getAction().toString()) {
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToIndia") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToAustralia") -> {
                                if(!((PokeAgentState) nt.getAgentState()).leGanoAlBoss() && ((PokeAgentState) nt.getAgentState()).esPokeparada("NuevaZelanda")){
                                    nt.setCost(nt.getParent().getCost() - 3);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 1);
                                }
                            }
                        }
                    }
                    case ("China") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToSiberia") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToJapon") -> nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("NuevaZelanda") -> {
                        switch (nt.getAction().toString()) {
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToNuevaGuinea") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToAustralia") -> nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Australia") -> {
                        switch (nt.getAction().toString()) {
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToIndonesia") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToNuevaZelanda") -> {
                                if(!((PokeAgentState) nt.getAgentState()).leGanoAlBoss() && ((PokeAgentState) nt.getAgentState()).esPokeparada("NuevaZelanda")){
                                    nt.setCost(nt.getParent().getCost() - 4);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 1);
                                }
                            }
                            case ("GoToBoss") -> {
                                if(((PokeAgentState) nt.getAgentState()).leGanoAlBoss()){
                                    nt.setCost(nt.getParent().getCost() - 4);
                                }else{
                                    nt.setCost(nt.getParent().getCost() - 0);
                                }
                            }
                        }
                    }
                    case ("NuevaGuinea") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToJapon") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("GoToNuevaZelanda") -> nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Japon") -> {
                        switch (nt.getAction().toString()) {
                            case ("GoToChina") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToIndia") -> nt.setCost(nt.getParent().getCost() - 2);
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 3);
                            case ("GoToNuevaGuinea") -> nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("Kamchatka") -> {
                        switch (nt.getAction().toString()) {
                            case ("AtacarColapsado") -> nt.setCost(nt.getParent().getCost() - 1);
                            case ("GoToSiberia") -> nt.setCost(nt.getParent().getCost() - 2);
                        }
                    }
                    default -> {
                        nt.setCost(nt.getParent().getCost() - 1);
                    }
                }
            }
        }
        nodesToExpand.addAll(nodes);
    }
    @Override
    public void addNodeToExpand(NTree node) {
        if(node.getParent() == null){
            node.setCost(-1);
            nodesToExpand.add(node);
        }else{
            Vector<NTree> nodes = new Vector<>();
            nodes.add(node);
            addNodesToExpand(nodes);
        }
    }
    @Override
    public void initNodesToExpandList(NTree node) {
        if (!isInitialized) {
            addNodeToExpand(node);
            isInitialized = true;
        }

    }

    @Override
    public String getStrategyName() {
        return "Tecnica informada de prueba";
    }

}
