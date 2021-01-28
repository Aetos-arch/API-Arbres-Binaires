import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ArbreApplication extends Application {

  private Dessin<Integer> dessin;

  @Override
  public void init() {
    /*
     * Construire ici un arbre ainsi qu'une instance de la classe
     * Dessin à laquelle on passe l'arbre.
     * Par exemple:
     */
    ArbreInt gche = new ArbreInt(new ArbreInt(), 2, new ArbreInt(3));
    ArbreInt drt = new ArbreInt(
      new ArbreInt(5),
      4,
      new ArbreInt(new ArbreInt(7), 6, new ArbreInt())
    );
    ArbreInt a = new ArbreInt(gche, 1, drt);
    dessin = new Dessin<Integer>(a);
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      Scene scene = new Scene(root, 800, 500);
      root.setCenter(dessin);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Arbre");
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
