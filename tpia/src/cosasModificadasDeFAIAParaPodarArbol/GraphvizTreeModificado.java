package cosasModificadasDeFAIAParaPodarArbol;

import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.util.GraphvizTree;
import java.io.File;

public class GraphvizTreeModificado extends GraphvizTree {
        private static int fileIdx = 0;
        private static String enc = "digraph g {\nnode [shape = Mrecord];\n";
        private static String pie = "\n}";
        private static final String searchTreesDir = "searchGVTrees/";

        public GraphvizTreeModificado() {
        }

        public static void printFile(NTreeConPoda tree) {
            PrintOut print = null;

            try {
                File f = new File("searchGVTrees/");
                if (!f.exists()) {
                    f.mkdir();
                }

                print = new PrintOut("searchGVTrees/" + fileIdx + ".dot", false);
                ++fileIdx;
                print.write(enc);
                print.write(tree.toGraphviz());
                print.write(pie);
                print.close();
            } catch (Exception var3) {
                System.out.println(var3.getMessage());
            }

        }
}
