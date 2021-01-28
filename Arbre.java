import java.util.LinkedList;

/**
 * Cette classe est dite générique car le contenu du noeud de l'arbre peut être de tout type
 */
public class Arbre<E> {

  protected E contenu; // contenu du noeud
  protected Arbre<E> filsG, filsD; // les sous-arbres

  /**
   * Constructeur
   *
   * @param g
   *            sous-arbre gauche
   * @param c
   *            contenu du noeud
   * @param d
   *            sous-arbre droit
   */
  public Arbre(Arbre<E> g, E c, Arbre<E> d) {
    this.filsG = g;
    this.contenu = c;
    this.filsD = d;
  }

  /**
   * Constructeur d'arbre vide
   */
  public Arbre() {
    this(null, null, null);
  }

  /**
   * Constructeur de feuille càd un contenu et deux sous-arbres vides
   */
  public Arbre(E contenu) {
    this(new Arbre<E>(), contenu, new Arbre<E>());
  }

  /**
   * Teste si l'arbre courant est vide
   */
  public boolean estVide() {
    return this.filsG == null && this.filsD == null && this.contenu == null;
  }

  /**
   * Retourne la taille de l'arbre courant
   */
  public int taille() {
    if (this.estVide()) return 0; else {
      return 1 + this.filsG.taille() + this.filsD.taille();
    }
  }

  /**
   * Retourne vrai si l'arbre courant est une feuille
   */
  public boolean estFeuille() {
    if (this.estVide()) {
      return false;
    } else {
      return this.filsG.estVide() && this.filsD.estVide();
    }
  }

  /**
   * Retourne le nombre de feuilles de l'arbre courant
   */
  public int nbFeuilles() {
    if (this.estVide()) {
      return 0;
    } else if (this.estFeuille()) {
      return 1;
    } else {
      return this.filsG.nbFeuilles() + this.filsD.nbFeuilles();
    }
  }

  /**
   * Retourne la hauteur de l'arbre courant (0 si feuille)
   */
  public int hauteur() {
    if (this.estVide()) {
      return -1;
    }

    if (this.estFeuille()) {
      return 0;
    } else {
      return 1 + Math.max(this.filsG.hauteur(), this.filsD.hauteur());
    }
  }

  /**
   * Affiche les contenus de l'arbre courant en effectuant un parcours préfixe
   */
  public void parcoursPrefixe() {
    if (!this.estVide()) {
      System.out.println(this.getContenu());
      this.filsG.parcoursPrefixe();
      this.filsD.parcoursPrefixe();
    }
  }

  /**
   * Affiche les contenus de l'arbre courant en effectuant un parcours infixe
   */
  public void parcoursInfixe() {
    if (!estVide()) {
      this.filsG.parcoursInfixe();
      System.out.print(this.contenu + " ");
      this.filsD.parcoursInfixe();
    }
  }

  /**
   * Affiche les contenus de l'arbre courant en effectuant un parcours postfixe
   */
  public void parcoursPostfixe() {
    if (!estVide()) {
      this.filsG.parcoursPostfixe();
      this.filsD.parcoursPostfixe();
      System.out.print(this.contenu + " ");
    }
  }

  /**
   * Affiche les contenus de l'arbre courant en effectuant un parcours en largeur
   */
  public void parcoursLargeur() {
    LinkedList<Arbre<E>> listeArbre = new LinkedList<Arbre<E>>();
    listeArbre.addFirst(this);
    Arbre<E> a1;
    do {
      a1 = listeArbre.removeLast();
      if (!a1.estVide()) {
        System.out.print(a1.contenu + " ");
        listeArbre.addFirst(a1.filsG);
        listeArbre.addFirst(a1.filsD);
      }
    } while (!listeArbre.isEmpty());
  }

  /**************************
   * Getters et utilitaires *
   **************************/

  public Arbre<E> getFilsG() {
    return this.filsG;
  }

  public Arbre<E> getFilsD() {
    return this.filsD;
  }

  public E getContenu() {
    return this.contenu;
  }

  public boolean estIdentiqueA(Arbre<E> b) {
    if (this.estVide() && b.estVide()) return true;
    if (
      !this.estVide() && !b.estVide() && this.contenu.equals(b.contenu)
    ) return (
      this.filsG.estIdentiqueA(b.filsG) && this.filsD.estIdentiqueA(b.filsD)
    ); else return false;
  }

  @Override
  public String toString() {
    if (this.estVide()) return "vide";
    return (
      "[filsG=" + filsG + ", contenu=" + contenu + ", filsD=" + filsD + "]"
    );
  }
}
