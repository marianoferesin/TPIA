package modificacionesFaia.Search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class PokeStepCostFunction implements IStepCostFunction {
    @Override
    public double calculateCost(NTree nTree) {
        return nTree.getAction().getCost();
    }
}
