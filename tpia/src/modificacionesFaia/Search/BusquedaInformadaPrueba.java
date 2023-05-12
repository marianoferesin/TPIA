package modificacionesFaia.Search;

import actions.AtacarColapsado;
import actions.GoToX;
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

    public void addNodesToExpand(Vector<NTree> nodes){
        for (NTree nt : nodes) {
            if(nt.getParent() != null){
                switch (((PokeAgentState) nt.getAgentState()).getPokeUbicacion().getNombre()) {
                    case ("EEUU") -> {
                        if (nt.getAction().equals(new GoToX("Alaska"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Mexico"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("Canada"))) {
                            nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("Kamchatka") -> {
                        if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new GoToX("Siberia"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        }
                    }
                    case ("Alaska") -> {
                        if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new GoToX("EEUU"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        }
                    }
                    case ("TierraDelFuego") -> {
                        if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new GoToX("BuenosAires"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Peru"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("BuenosAires") -> {
                        if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new GoToX("Brasil"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("TierraDelFuego"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        }
                    }
                    case ("Brasil") -> {
                        if (nt.getAction().equals(new GoToX("BuenosAires"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Peru"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Peru") -> {
                        if (nt.getAction().equals(new GoToX("Brasil"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new GoToX("TierraDelFuego"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Mexico"))) {
                            nt.setCost(nt.getParent().getCost() - 4);
                        } else if (nt.getAction().equals(new GoToX("Canarias"))) {
                            nt.setCost(nt.getParent().getCost() - 5);
                        }
                    }
                    case ("Mexico") -> {
                        if (nt.getAction().equals(new GoToX("Peru"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("EEUU"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("Cuba"))) {
                            nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("Cuba") -> {
                        if (nt.getAction().equals(new GoToX("Mexico"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Canarias"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Canarias") -> {
                        if (nt.getAction().equals(new GoToX("Peru"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Canada"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("Sahara"))) {
                            nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("Canada") -> {
                        if (nt.getAction().equals(new GoToX("EEUU"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Groenlandia"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("Canarias"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Sahara") -> {
                        if (nt.getAction().equals(new GoToX("Canarias"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Egipto"))) {
                            nt.setCost(nt.getParent().getCost() - 10);
                        }
                    }
                    case ("Egipto") -> {
                        if (nt.getAction().equals(new GoToX("Sahara"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Suecia"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("Moscu"))) {
                            nt.setCost(nt.getParent().getCost() - 4);
                        } else if (nt.getAction().equals(new GoToX("Sudafrica"))) {
                            nt.setCost(nt.getParent().getCost() - 5);
                        }
                    }
                    case ("Sudafrica") -> {
                        if (nt.getAction().equals(new GoToX("Boss")) && !((PokeAgentState) nt.getAgentState()).leGanoAlBoss()) {
                            System.out.println("Tuve una idea de mierda");
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new GoToX("Egipto"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("Boss"))) {
                            nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("Groenlandia") -> {
                        if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new GoToX("Noruega"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Inglaterra"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("Canada"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Inglaterra") -> {
                        if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new GoToX("Canada"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Suecia"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Noruega") -> {
                        if (nt.getAction().equals(new GoToX("Groenlandia"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Moscu"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        }
                    }
                    case ("Suecia") -> {
                        if (nt.getAction().equals(new GoToX("Inglaterra"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Moscu"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Egipto"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        }
                    }
                    case ("Moscu") -> {
                        if (nt.getAction().equals(new GoToX("Noruega"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new GoToX("Suecia"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("Siberia"))) {
                            nt.setCost(nt.getParent().getCost() - 4);
                        } else if (nt.getAction().equals(new GoToX("India"))) {
                            nt.setCost(nt.getParent().getCost() - 5);
                        } else if (nt.getAction().equals(new GoToX("Egipto"))) {
                            nt.setCost(nt.getParent().getCost() - 6);
                        }
                    }
                    case ("India") -> {
                        if (nt.getAction().equals(new GoToX("Moscu"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Japon"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("Indonesia"))) {
                            nt.setCost(nt.getParent().getCost() - 4);
                        } else if (nt.getAction().equals(new GoToX("Arabia"))) {
                            nt.setCost(nt.getParent().getCost() - 5);
                        }
                    }
                    case ("Arabia") -> {
                        if (nt.getAction().equals(new GoToX("Boss")) && !((PokeAgentState) nt.getAgentState()).leGanoAlBoss()) {
                            System.out.println("Tuve una idea de mierda");
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new GoToX("India"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("Boss"))) {
                            nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("Siberia") -> {
                        if (nt.getAction().equals(new GoToX("Kamchatka"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("China"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("Moscu"))) {
                            nt.setCost(nt.getParent().getCost() - 4);
                        }
                    }
                    case ("China") -> {
                        if (nt.getAction().equals(new GoToX("Siberia"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Japon"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Japon") -> {
                        if (nt.getAction().equals(new GoToX("China"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("NuevaGuinea"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("India"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("NuevaGuinea") -> {
                        if (nt.getAction().equals(new GoToX("Japon"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("NuevaZelanda"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("NuevaZelanda") -> {
                        if (nt.getAction().equals(new GoToX("NuevaGuinea"))) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Australia"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    case ("Australia") -> {
                        if (nt.getAction().equals(new GoToX("Boss")) && !((PokeAgentState) nt.getAgentState()).leGanoAlBoss()) {
                            System.out.println("Tuve una idea de mierda");
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Indonesia"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        } else if (nt.getAction().equals(new GoToX("NuevaZelanda"))) {
                            nt.setCost(nt.getParent().getCost() - 4);
                        } else if (nt.getAction().equals(new GoToX("Boss"))) {
                            nt.setCost(nt.getParent().getCost() - 5);
                        }
                    }
                    case ("Indonesia") -> {
                        if (nt.getAction().equals(new AtacarColapsado())) {
                            nt.setCost(nt.getParent().getCost() - 1);
                        } else if (nt.getAction().equals(new GoToX("India"))) {
                            nt.setCost(nt.getParent().getCost() - 2);
                        } else if (nt.getAction().equals(new GoToX("Australia"))) {
                            nt.setCost(nt.getParent().getCost() - 3);
                        }
                    }
                    default -> nt.setCost(nt.getParent().getCost() - 1);
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
