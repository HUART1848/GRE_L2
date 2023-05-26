package gre.lab2.groupe16;

import gre.lab2.graph.Edge;
import gre.lab2.graph.VertexLabelling;
import gre.lab2.gui.MazeBuilder;
import gre.lab2.gui.MazeGenerator;
import gre.lab2.gui.Progression;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Générateur de labyrinthes avec implémentation itérative
 * de l'algorithme de Kruskal à l'aide d'une structure Union-Find.
 */
public final class KruskalMazeGenerator implements MazeGenerator {

  @Override
  public void generate(MazeBuilder builder, int from) {
    UnionFind scc = new UnionFind(builder.topology().nbVertices());

    ArrayList<Edge> edges = new ArrayList<>(builder.topology().edges());
    Collections.shuffle(edges);

    for (Edge edge : edges) {
      if (scc.union(edge.u(), edge.v())) {
        // Les sommets ne font pas encore partie de la même composante connexe
        builder.removeWall(edge.u(), edge.v());
        builder.progressions().setLabel(edge.u(), Progression.PROCESSED);
        builder.progressions().setLabel(edge.v(), Progression.PROCESSED);
      }
    }
  }
}

// TODO
//  - Implémentation des classes KruskalMazeGenerator;
//  - Documentation abondanste des trois classes comprenant :
//    - la javadoc, avec austeurs et description des implémentations ;
//    - des commentaires susr les différentes parties de vos algorithmes.
