import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests des méthodes de la classe AVL. On travaille sur des instances de AVLInt càd des AVL dont les contenus sont des
 * entiers.
 */
public class AVLTest {

  private static AVLInt AVLVide;
  private static AVLInt AVLFeuille1;
  private static AVLInt AVLPasFilsG, AVLPasFilsD;
  private static AVLInt AVLQcq;

  @Before
  public void setUp() throws Exception {
    AVLVide = new AVLInt();
    AVLFeuille1 = new AVLInt(5);
    AVLPasFilsG = new AVLInt(new AVLInt(), 5, new AVLInt(6));
    AVLPasFilsD = new AVLInt(new AVLInt(5), 6, new AVLInt());
    AVLQcq =
      new AVLInt(
        new AVLInt(new AVLInt(), 6, new AVLInt(11)),
        15,
        new AVLInt(
          new AVLInt(16),
          18,
          new AVLInt(new AVLInt(19), 20, new AVLInt())
        )
      );
  }

  @Test
  public void testRotationGauche() {
    AVLVide.rotationGauche();
    assertTrue("RotationGauche arbre vide", AVLVide.estIdentiqueA(AVLVide));
    AVLFeuille1.rotationGauche();
    assertTrue(
      "RotationGauche arbre feuille",
      AVLFeuille1.estIdentiqueA(AVLFeuille1)
    );
    AVLPasFilsD.rotationGauche();
    assertTrue(
      "RotationGauche pas de filsD",
      AVLPasFilsD.estIdentiqueA(AVLPasFilsD)
    );
    AVLPasFilsG.rotationGauche();
    assertTrue(
      "RotationGauche pas de filsG",
      AVLPasFilsD.estIdentiqueA(AVLPasFilsG)
    );
    AVLInt rotG = new AVLInt(
      new AVLInt(
        new AVLInt(new AVLInt(), 6, new AVLInt(11)),
        15,
        new AVLInt(16)
      ),
      18,
      new AVLInt(new AVLInt(19), 20, new AVLInt())
    );
    AVLQcq.rotationGauche();
    assertTrue("RotationGauche qcq", rotG.estIdentiqueA(AVLQcq));
  }

  @Test
  public void testRotationDroite() {
    AVLVide.rotationDroite();
    assertTrue("RotationDroite arbre vide", AVLVide.estIdentiqueA(AVLVide));
    AVLFeuille1.rotationDroite();
    assertTrue(
      "RotationDroite arbre feuille",
      AVLFeuille1.estIdentiqueA(AVLFeuille1)
    );
    AVLPasFilsD.rotationDroite();
    assertTrue(
      "RotationDroite pas de filsD",
      AVLPasFilsG.estIdentiqueA(AVLPasFilsD)
    );
    AVLPasFilsG.rotationDroite();
    assertTrue(
      "RotationDroite pas de filsG",
      AVLPasFilsG.estIdentiqueA(AVLPasFilsG)
    );
    AVLQcq.rotationDroite();
    AVLInt rotD = new AVLInt(
      new AVLInt(),
      6,
      new AVLInt(
        new AVLInt(11),
        15,
        new AVLInt(
          new AVLInt(16),
          18,
          new AVLInt(new AVLInt(19), 20, new AVLInt())
        )
      )
    );
    assertTrue("RotationDroite qcq", rotD.estIdentiqueA(AVLQcq));
  }
}
