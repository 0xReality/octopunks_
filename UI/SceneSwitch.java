package UI;


import javafx.scene.Scene;

import javafx.stage.Stage;


public class SceneSwitch {
    public SceneSwitch(Stage stage, Scene next){
        stage.setScene(next);
        stage.setFullScreen(true);
    }

    // public static void transitionToScene(Stage stage, Scene newScene){
    //     FadeTransition ft = new FadeTransition(Duration.millis(1000), stage.getScene().getRoot()); 
    //     ft.setFromValue(1.0);
    //     ft.setToValue(0.0);
    //     ft.setOnFinished(event->stage.setScene(newScene));
    //     ft.play();
    // }
}
