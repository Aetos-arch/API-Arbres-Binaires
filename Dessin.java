import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Classe réalisant un dessin de l'arbre donné en paramètre de son constructeur. A placer dans la scene définie dans la
 * classe de lancement qui étend Application.
 */
public class Dessin<E> extends ScrollPane {

  private Arbre<E> arbre;
  private Map<Arbre<E>, Point> coord; // coordonnées correspondantes aux noeuds
  private Group g;
  private final int LARG_BOITE = 20;
  private final int HAUT_BOITE = 20;
  private final int dx = 17; // écart horizontal
  private final int dy = 60; // écart vertical
  private final int mx = dx / 2;
  private final int my = dy / 2;
  private final Color couleurNoeud = Color.LIGHTSTEELBLUE;

  public Dessin(Arbre<E> arbre) {
    super();
    setId("dessin");
    this.setPadding(new Insets(5));
    setStyle("-fx-background: rgb(255,240,220)");
    coord = new HashMap<Arbre<E>, Point>();
    g = new Group();
    init(arbre);
  }

  public void init(Arbre<E> arbre) {
    this.arbre = arbre;
    coord.clear();
    g.getChildren().clear();
    if (!arbre.estVide()) dessiner(arbre);
    setContent(g);
  }

  private void dessiner(Arbre<E> a) {
    calculerCoordonnees(arbre, 1, 0); // * LARG_BOITE * 0.9), (int) (Arbre.hauteur(arbre) * (HAUT_BOITE + dy) * 0.9)));
    int x1 = (int) (dx * coord.get(a).getX() + mx);
    int y1 = (int) (dy * coord.get(a).getY() + my);

    if (!a.getFilsG().estVide()) {
      Arbre<E> gche = a.getFilsG();
      int x2 = (int) (dx * coord.get(gche).getX() + mx);
      int y2 = (int) (dy * coord.get(gche).getY() + my);
      Line l = new Line(
        x1 - LARG_BOITE / 2,
        y1,
        x2 - LARG_BOITE / 2,
        y2 - HAUT_BOITE
      );
      l.setEffect(new DropShadow());
      g.getChildren().add(l);
      dessiner(gche);
    }
    if (!a.getFilsD().estVide()) {
      Arbre<E> drt = a.getFilsD();
      int x2 = (int) (dx * coord.get(drt).getX() + mx);
      int y2 = (int) (dy * coord.get(drt).getY() + my);
      Line l = new Line(
        x1 - LARG_BOITE / 2,
        y1,
        x2 - LARG_BOITE / 2,
        y2 - HAUT_BOITE
      );
      l.setEffect(new DropShadow());
      g.getChildren().add(l);
      dessiner(drt);
    }

    Rectangle r = new Rectangle(
      x1 - LARG_BOITE,
      y1 - HAUT_BOITE,
      LARG_BOITE,
      HAUT_BOITE
    );
    r.setEffect(new DropShadow());
    r.setArcHeight(7);
    r.setArcWidth(7);
    r.setFill(couleurNoeud);
    g.getChildren().add(r);

    String valeur = "" + a.getContenu().toString();
    Text t = new Text(
      x1 - LARG_BOITE / 2 - (valeur.length() * 4),
      y1 - HAUT_BOITE / 2 + 5,
      valeur
    );
    g.getChildren().add(t);
  }

  public int calculerCoordonnees(Arbre<E> a, int xCourant, int yCourant) {
    /* on augmente xCourant afin d'accueillir toute la largeur du fils gauche */
    if (!a.getFilsG().estVide()) xCourant =
      calculerCoordonnees(a.getFilsG(), xCourant, yCourant + 1);

    /* la valeur de xCourant est maintenant l'abscisse du noeud */
    this.coord.put(a, new Point(xCourant, yCourant));
    xCourant++;

    /* on deplace xCourant afin d'accueillir toute la largeur du fils droit */
    if (!a.getFilsD().estVide()) xCourant =
      calculerCoordonnees(a.getFilsD(), xCourant, yCourant + 1);

    /* la valeur de xCourant comprend maintenant la largeur de l'arbre */
    return xCourant;
  }
}
