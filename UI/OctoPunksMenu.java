package UI;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OctoPunksMenu extends Application {
    
    private Stage stage;
    private Menu mainScene;
    @Override
    public void start(Stage OctoPunks) {
       
        stage = OctoPunks;
        stage.setTitle("OctoPunks");
        //load l'icone du jeu
        stage.getIcons().add(new Image("file:resources/icon.png"));
        //mettre le jeu en plein écran

        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.initStyle(StageStyle.UNDECORATED);

        mainScene = new Menu(stage);

        stage.setScene(mainScene.getMainScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); 
    }

    public Menu getSceneMain(){
        return mainScene; 
    }
}
