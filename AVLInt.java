/**
 * Cette classe implémente les AVL dont le contenu est un entier. Elle ne contient que des constructeurs, les méthodes
 * générales sur les AVL étant codées dans la clase AVL
 */
public class AVLInt extends AVL<Integer> {

  public AVLInt(AVLInt g, Integer c, AVLInt d) {
    super(g, c, d);
  }

  public AVLInt(Integer c) {
    super(c);
  }

  public AVLInt() {
    super();
  }
}
