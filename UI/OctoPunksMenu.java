package UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OctoPunksMenu extends Application {
    private final String name = "OctoPunks";

    @Override
    public void start(Stage OctoPunks) {
        OctoPunks.setTitle(name);

        //mettre le jeu en plein écran
        OctoPunks.setFullScreen(true);
        OctoPunks.setFullScreenExitHint("");
        OctoPunks.initStyle(StageStyle.UNDECORATED);

        //appel au constructeur du Menu
        Scene menu = new Menu(new AnchorPane(), OctoPunks);


        //lancement du jeu dans le menu principal
        OctoPunks.setScene(menu);
        OctoPunks.show();
    }

    public static void main(String[] args) {
        launch(args); 
    }
}
