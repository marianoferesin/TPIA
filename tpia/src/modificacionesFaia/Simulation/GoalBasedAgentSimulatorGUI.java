package modificacionesFaia.Simulation;

import GUI.PokeFrame;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.GoalBasedAgent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.simulator.Simulator;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public abstract class GoalBasedAgentSimulatorGUI extends Simulator {
    private ArrayList<GoalBasedAgent> agentes = new ArrayList<>();
    private ArrayList<Action> acciones = new ArrayList<>();
    public GoalBasedAgentSimulatorGUI(Environment environment, Vector<Agent> agents) {
        super(environment, agents);
    }

    public GoalBasedAgentSimulatorGUI(Environment environment, Agent agent) {
        Vector<Agent> ags = new Vector();
        ags.add(agent);
        this.environment = environment;
        this.agents = ags;
    }

    public void start() {
        System.out.println("----------------------------------------------------");
        System.out.println("--- " + this.getSimulatorName() + " ---");
        System.out.println("----------------------------------------------------");
        System.out.println();
        GoalBasedAgent agent = (GoalBasedAgent) this.getAgents().firstElement();

        Action action;
        do {
            System.out.println("------------------------------------");
            System.out.println("Sending perception to agent...");
            Perception perception = this.getPercept();
            agent.see(perception);
            System.out.println("Perception: " + perception);
            System.out.println("Agent State: " + agent.getAgentState());
            System.out.println("Environment: " + this.environment);
            System.out.println("Asking the agent for an action...");
            action = agent.selectAction();
            if (action == null) {
                break;
            }

            System.out.println("Action returned: " + action);
            System.out.println();

            //TODO almacenamiento de iteraciones
            agentes.add(agent);
            acciones.add(action);

            this.actionReturned(agent, action);
        } while (!this.agentSucceeded(action) && !this.agentFailed(action));
        if (this.agentSucceeded(action)) {
            System.out.println("Agent has reached the goal!");
        } else {
            System.out.println("ERROR: The simulation has finished, but the agent has not reached his goal.");
        }
        System.out.println();
        this.environment.close();
        SimulatorEventNotifier.runEventHandlers(EventType.SimulationFinished, (Object[])null);
    }

    protected void updateState(Action action) {
        this.getEnvironment().updateState(((GoalBasedAgent)this.agents.elementAt(0)).getAgentState(), action);
    }

    public abstract boolean agentSucceeded(Action var1);

    public abstract boolean agentFailed(Action var1);

    public abstract void actionReturned(Agent var1, Action var2);

    public abstract String getSimulatorName();
}
