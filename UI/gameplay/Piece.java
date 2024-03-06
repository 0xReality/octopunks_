/* package UI.gameplay;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Piece extends Application{
    @Override
    public void start(Stage stage){
        GridPane grille = new GridPane(); 

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                Rectangle rect = new Rectangle(50, 50); // taille de la cellulle de jeu
                rect.setStroke(javafx.scene.paint.Color.BLACK);   // couleur du contour
                rect.setFill(javafx.scene.paint.Color.GREEN);
                //ici on doit ajouter tout les gestion du robot 

                grille.add(rect, i,j); 
            }
        }

        Scene scene = new Scene(grille); 
        stage.setScene(scene);
        stage.setTitle("Terrain de jeu Octopunks");
        stage.show();
    }
}
 */