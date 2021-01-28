/**
 * Cette classe implémente les ABR dont le contenu est un entier. Elle ne contient que des constructeurs, les méthodes
 * générales sur les ABR étant codées dans la clase ABR
 */
public class ABRInt extends ABR<Integer> {

  public ABRInt(ABRInt g, Integer c, ABRInt d) {
    super(g, c, d);
  }

  public ABRInt() {
    super();
  }

  public ABRInt(Integer c) {
    super(c);
  }
}
