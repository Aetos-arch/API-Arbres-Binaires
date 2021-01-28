/**
 * Cette classe implémente les arbres binaires dont le contenu est un entier. Elle ne contient que des constructeurs,
 * les méthodes générales sur les arbres binaires étant codées dans la clase Arbre
 */
public class ArbreInt extends Arbre<Integer> {

  public ArbreInt(ArbreInt g, Integer c, ArbreInt d) {
    super(g, c, d);
  }

  public ArbreInt() {
    super();
  }

  public ArbreInt(Integer c) {
    super(c);
  }
}
