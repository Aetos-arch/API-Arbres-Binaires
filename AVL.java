/**
 * Classe générique (comme les classes Arbre et ABR): le contenu des noeuds est de type quelconque E à condition qu'il
 * permette les comparaisons
 */
public class AVL<E extends Comparable<E>> extends ABR<E> implements Cloneable {

  public AVL(AVL<E> g, E c, AVL<E> d) {
    super(g, c, d);
  }

  public AVL(E c) {
    this(new AVL<>(), c, new AVL<>());
  }

  public AVL() {
    this(null, null, null);
  }

  /**
   * Effectue une rotation gauche autour de la racine de l'AVL courant si cela est possible
   */
  public void rotationGauche() {
    if (!this.estVide() && !this.filsD.estVide()) {
      AVL<E> tmp = new AVL<E>(
        (AVL<E>) this.filsG,
        this.contenu,
        (AVL<E>) this.filsD.filsG
      );
      this.contenu = this.filsD.contenu;
      this.filsD = this.filsD.filsD;
      this.filsG = tmp;
    }
  }

  /**
   * Effectue une rotation droite autour de la racine de l'AVL courant si cela est possible
   */
  public void rotationDroite() {
    if (!this.estVide() && !this.filsG.estVide()) {
      AVL<E> tmp = new AVL<E>(
        (AVL<E>) this.filsG.filsD,
        this.contenu,
        (AVL<E>) this.filsD
      );
      this.contenu = this.filsG.contenu;
      this.filsG = this.filsG.filsG;
      this.filsD = tmp;
    }
  }

  /**
   * Equilibre l'AVL courant avec une (double) rotation suite à une opération d'insertion ou de suppression de valeur
   */
  public void equilibrage() {
    if (!this.estVide()) {
      if (this.filsG.hauteur() - this.filsD.hauteur() == 2) {
        if (this.filsG.filsD.hauteur() > this.filsG.filsG.hauteur()) {
          ((AVL<E>) this.filsG).rotationGauche();
        }
        this.rotationDroite();
      } else if (this.filsD.hauteur() - this.filsG.hauteur() == 2) {
        if (this.filsD.filsG.hauteur() > this.filsD.filsD.hauteur()) {
          ((AVL<E>) this.filsD).rotationDroite();
        }
        this.rotationGauche();
      }
    }
  }

  /**
   * Insère val dans l'AVL courant et équilibre
   */
  public void insertion(E val) {
    if (estVide()) {
      this.contenu = val;
      this.filsG = new AVL<>();
      this.filsD = new AVL<>();
    } else {
      if (val.compareTo(this.contenu) <= 0) {
        ((AVL<E>) this.filsG).insertion(val);
      } else {
        ((AVL<E>) this.filsD).insertion(val);
      }
      this.equilibrage();
    }
  }

  /**
   * Supprime val de l'AVL courant et équilibre
   */
  public void supprime(E val) {
    if (!estVide()) {
      if (val.equals(this.contenu)) this.supprimeRacine(); else if (
        val.compareTo(this.contenu) < 0
      ) ((ABR<E>) this.filsG).supprime(val); else (
        (ABR<E>) this.filsD
      ).supprime(val);
    }
    this.equilibrage();
  }

  /**
   * Supprime le contenu de la racine de l'AVL courant
   */
  public void supprimeRacine() {
    //TODO
  }
}
