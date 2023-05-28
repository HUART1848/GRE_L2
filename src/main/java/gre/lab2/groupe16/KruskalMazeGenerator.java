package gre.lab2.groupe16;

import gre.lab2.graph.Edge;
import gre.lab2.gui.MazeBuilder;
import gre.lab2.gui.MazeGenerator;
import gre.lab2.gui.Progression;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Générateur de labyrinthes avec implémentation itérative
 * de l'algorithme de Kruskal à l'aide d'une structure Union-Find.
 *
 * @author Farouk Ferchichi & Hugo Huart
 */
public final class KruskalMazeGenerator implements MazeGenerator {

  @Override
  public void generate(MazeBuilder builder, int from) {
    // Structure Union-Find pour le calcul des composantes fortement connexes
    UnionFind scc = new UnionFind(builder.topology().nbVertices());

    ArrayList<Edge> edges = new ArrayList<>(builder.topology().edges());
    Collections.shuffle(edges);

    for (Edge edge : edges) {
      if (scc.union(edge.u(), edge.v())) {
        // Les sommets ne font pas encore partie de la même composante connexe
        builder.progressions().setLabel(edge.u(), Progression.PROCESSED);
        builder.progressions().setLabel(edge.v(), Progression.PROCESSED);
        builder.removeWall(edge.u(), edge.v());
      }
    }
  }
}
