import java.util.Map;
import java.util.Set;

public class ArbreCodage
  extends Arbre<Character>
  implements Comparable<ArbreCodage> {

  private int poids;

  ArbreCodage(ArbreCodage a, ArbreCodage b) {}

  ArbreCodage(char c, int i) {
    this.contenu = c;
    this.poids = i;
  }

  public Map<Character, String> codage(Set<Character> a) {
    return null;
  }

  public int getPoids() {
    return this.poids;
  }

  @Override
  public int compareTo(ArbreCodage o2) {
    return this.getPoids() - o2.getPoids();
  }

  @Override
  public String toString() {
    return "ArbreCodage{}";
  }
}
