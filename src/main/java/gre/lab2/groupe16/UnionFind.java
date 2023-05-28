package gre.lab2.groupe16;

/**
 * Implémentation d'une structure Union-Find avec représentation par des arbres.
 * Utilise la méthode de <i>path-splitting</i> pour les opérations <i>find</i>.
 * Utilise la méthode d'union par rang pour les opérations <i>union</i>.
 * @author Farouk Ferchichi & Hugo Huart
 */
final class UnionFind {
  private final int size;
  private final int[] parents;
  private final int[] ranks;

  /**
   * Constructeur de la classe UnionFind.
   * @param size Nombre d'éléments.
   */
  public UnionFind(int size) {
    this.size = size;
    parents = new int[size];
    ranks = new int[size];

    for (int i = 0; i < size; ++i) {
      parents[i] = i;
      ranks[i] = 0;
    }
  }

  /**
   * Vérifie l'existence d'un élément v selon la taille totale.
   * @param v Un élément.
   * @throws IllegalArgumentException Si v ne fait pas partie des sous-ensembles.
   */
  private void assertExists(int v) {
    if (v < 0 || v >= size) {
      throw new IllegalArgumentException(String.format("%d is out of bounds [%d, %d]", v, 0, size - 1));
    }
  }

  /**
   * Retourne un entier correspondant au représentant de v, avec <i>path-splitting</i>.
   * @param v Un élément.
   * @return Le représentant de v.
   */
  public int find(int v) {
    assertExists(v);

    while (v != parents[v]) {
      v = parents[v];
      parents[v] = parents[parents[v]];
    }
    return v;
  }

  /**
   * Effectue l'union par rang des sous-ensembles de u et v.
   * @param u Un élément.
   * @param v Un élément.
   * @return Vrai si u et v sont membres de deux ensemble disjoints, faux sinon.
   */
  public boolean union(int u, int v) {
    assertExists(u);
    assertExists(v);

    u = find(u);
    v = find(v);

    if (u == v) {
      // Les deux sommets correspondants font déjà partie du même arbre
      return false;
    }

    // Réassignation afin que u et v correspondent aux arbres de rangs max et min respectivement
    if (ranks[u] < ranks[v]) {
      int tmp = u;
      u = v;
      v = tmp;
    }

    parents[v] = u;

    if (ranks[u] == ranks[v]) {
      ranks[u] += 1;
    }

    return true;
  }
}
