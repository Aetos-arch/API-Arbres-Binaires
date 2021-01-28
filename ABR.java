/**
 * Classe générique (comme la classe Arbre): le contenu des noeuds est de type quelconque E à condition qu'il permette
 * les comparaisons
 */
public class ABR<E extends Comparable<E>> extends Arbre<E> {

  /**
   * Contructeur avec un contenu et deux sous-ABR
   */
  public ABR(ABR<E> g, E c, ABR<E> d) {
    super(g, c, d);
  }

  /**
   * Constructeur d'ABR vide
   */
  public ABR() {
    this(null, null, null);
  }

  /**
   * Constructeur d'ABR feuille
   */
  public ABR(E c) {
    this(new ABR<>(), c, new ABR<>());
  }

  /**
   * Retourne le contenu minimum de l'ABR courant s'il est non vide. Lance une erreur sinon.
   */
  public E min() {
    if (estVide()) {
      throw new Error("Arbre vide: pas de min");
    }
    if (this.filsG.estVide()) return this.contenu; else return (
      (ABR<E>) this.filsG
    ).min();
  }

  /**
   * Retourne le contenu maximum de l'ABR courant s'il est non vide. Lance une erreur sinon.
   */
  public E max() {
    if (this.estVide()) throw new Error();
    if (this.filsD.estVide()) return this.contenu;

    return ((ABR<E>) this.filsD).max();
  }

  /**
   * Retourne vrai si val est dans l'ABR courant
   */
  public boolean recherche(E val) {
    if (this.estVide()) return false;
    if (this.contenu.equals(val)) return true; else if (
      this.contenu.compareTo(val) < 0
    ) return ((ABR<E>) this.filsD).recherche(val); else return (
      (ABR<E>) this.filsG
    ).recherche(val);
  }

  /**
   * Insère val dans l'ABR courant
   */
  public void insertion(E val) {
    if (estVide()) {
      this.contenu = val;
      this.filsG = new ABR<>();
      this.filsD = new ABR<>();
    } else {
      if (val.compareTo(this.contenu) <= 0) {
        ((ABR<E>) this.filsG).insertion(val);
      } else ((ABR<E>) this.filsD).insertion(val);
    }
  }

  /**
   * Supprime le contenu val de l'ABR courant s'il est présent
   */
  public void supprime(E val) {
    if (!estVide()) {
      if (val.equals(this.contenu)) this.supprimeRacine(); else if (
        val.compareTo(this.contenu) < 0
      ) ((ABR<E>) this.filsG).supprime(val); else (
        (ABR<E>) this.filsD
      ).supprime(val);
    }
  }

  /**
   * Supprime le contenu de la racine de l'ABR courant
   */
  private void supprimeRacine() {
    if (!estVide()) {
      if (this.filsG.estVide()) {
        this.contenu = this.filsD.contenu;
        this.filsG = this.filsD.filsG;
        this.filsD = this.filsD.filsD;
      }
    } else {
      this.contenu = ((ABR<E>) this.filsG).max();
      ((ABR<E>) this.filsG).supprime(this.contenu);
    }
  }
}
