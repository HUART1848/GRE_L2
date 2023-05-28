package gre.lab2.groupe16;

import gre.lab2.graph.Graph;
import gre.lab2.graph.VertexLabelling;
import gre.lab2.gui.MazeSolver;

import java.util.*;

/**
 * Solver de labyrinthe avec implémentation itérative d'un parcours en largeur (BFS).
 * @author Farouk Ferchichi & Hugo Huart
 */
public final class BfsSolver implements MazeSolver {

  @Override
  public List<Integer> solve(Graph graph, int source, int destination, VertexLabelling<Integer> treatments) {
    // Cas trivial
    if (source == destination) {
      treatments.setLabel(source, 2);
      return Arrays.asList(source, source);
    }

    LinkedList<Integer> queue = new LinkedList<>();
    int[] predecessors = new int[graph.nbVertices()];

    queue.add(source);
    treatments.setLabel(source, 1);

    boolean searching = true;
    while (searching && !queue.isEmpty()) {
      int u = queue.pop();

      for (int v : graph.neighbors(u)) {
        if (treatments.getLabel(v) == 0) {
          predecessors[v] = u;

          // Arrêt anticipé de l'algorithme si la destination est découverte
          if (v == destination) {
            treatments.setLabel(v, 2);
            searching = false;
            break;
          }

          treatments.setLabel(v, 1);
          queue.add(v);
        }
      }

      treatments.setLabel(u, 2);
    }

    // Reconstruction du plus court chemin à l'aide des prédécesseurs
    LinkedList<Integer> path = new LinkedList<>();
    path.add(destination);
    while (destination != source) {
      destination = predecessors[destination];
      path.addFirst(destination);
    }

    return path;
  }
}
