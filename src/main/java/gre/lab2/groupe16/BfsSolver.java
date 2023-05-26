package gre.lab2.groupe16;

import gre.lab2.graph.Graph;
import gre.lab2.graph.VertexLabelling;
import gre.lab2.gui.MazeSolver;

import java.util.*;

/**
 * Solver de labyrinthe avec implémentation itérative d'un parcours en largeur.
 */
public final class BfsSolver implements MazeSolver {

  @Override
  public List<Integer> solve(Graph graph, int source, int destination, VertexLabelling<Integer> treatments) {
    LinkedList<Integer> queue = new LinkedList<>(graph.neighbors(source));
    ArrayList<Integer> path = new ArrayList<>(graph.nbVertices());

    while (!queue.isEmpty()) {
      int u = queue.pop();

      for (int v : graph.neighbors(u)) {
        // Le sommet non-découvert
        if (treatments.getLabel(v) == 0) {
          // Le sommet est maintenant découvert
          treatments.setLabel(v, 1);
          queue.add(v);
        }
      }

      // Le sommet est traité
      treatments.setLabel(u, 2);
    }

    return path;
  }
}
