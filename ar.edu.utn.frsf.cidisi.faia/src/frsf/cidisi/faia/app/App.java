package frsf.cidisi.faia.app;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;

public class App {
    public static void main(String[] args) {
        Agent agent = new SearchBasedAgent() {
            @Override
            public void see(Perception p) {

            }

            @Override
            public Action selectAction() {
                return null;
            }
        };
        System.out.println(agent.toString());
    }

}
