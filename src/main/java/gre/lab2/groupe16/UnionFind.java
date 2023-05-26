package gre.lab2.groupe16;

/**
 * Implémentation d'une structure Union-Find avec représentation par des arbres.
 * Utilise la méthode de <i>path-splitting</i> pour les opérations <i>find</i>.
 * Utilise la méthode d'union par rangs pour les opérations <i>union</i>.
 */
final class UnionFind {
  private int[] parents;
  private int[] ranks;

  public UnionFind(int size) {
    parents = new int[size];
    ranks = new int[size];

    for (int i = 0; i < size; ++i) {
      parents[i] = i;
      ranks[i] = 0;
    }
  }

  /**
   *
   * @param v Un sommet
   * @return La racine de l'arbre du sommet .
   */
  public int find(int v) {
    while (v != parents[v]) {
      v = parents[v];
      parents[v] = parents[parents[v]];
    }
    return v;
  }

  public boolean union(int u, int v) {
    u = find(u);
    v = find(v);

    if (u == v) {
      // Les deux sommets font déjà partie de la même composante connexe
      return false;
    }

    // Réassignation afin que u et v correspondent aux arbres de rangs max et min respectivement
    if (ranks[u] < ranks[v]) {
      int temp = u;
      u = v;
      v = temp;
    }

    parents[v] = u;

    if (ranks[u] == ranks[v]) {
      ranks[u] += 1;
    }

    return true;
  }
}
