package UI;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SceneSwitch {
    public SceneSwitch(Stage stage, Scene next){
        stage.setScene(next);
    }
}
