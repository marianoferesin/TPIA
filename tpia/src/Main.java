import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
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