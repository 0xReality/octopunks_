package UI;

import javafx.scene.layout.AnchorPane;

public class SceneSwitch {
    public SceneSwitch(AnchorPane current, AnchorPane next){
        current.getChildren().removeAll();
        current.getChildren().setAll(next);
    }
}
