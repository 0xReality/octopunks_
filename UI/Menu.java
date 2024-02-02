package UI;

import UI.gameplay.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Scene {
    
    @SuppressWarnings("unused")
    private Stage OctoPunks;
    private AnchorPane root;


    private Button playButton;
    private Button level1Button;

    private boolean isLevel1Completed = false;

    public Menu(AnchorPane root, Stage OctoPunks) {
        super(root);
        this.root = root;
        this.OctoPunks = OctoPunks;

        VBox layout = new VBox(10); 
        layout.setAlignment(Pos.CENTER);

        // Play Button
        playButton = new Button("Play");
        playButton.setOnAction(event -> showLevels(layout));

        layout.getChildren().add(playButton);

        //ppour centrer le button
        AnchorPane.setTopAnchor(layout, 0.0);
        AnchorPane.setRightAnchor(layout, 0.0);
        AnchorPane.setBottomAnchor(layout, 0.0);
        AnchorPane.setLeftAnchor(layout, 0.0);

        root.getChildren().add(layout);
    }


    private void showLevels(VBox layout) {
        layout.getChildren().clear();

        // Level 1 Button
        level1Button = new Button("Level 1");
        level1Button.setOnAction(event -> {
            AnchorPane game = new Game(1);
            @SuppressWarnings("unused")
            SceneSwitch ss = new SceneSwitch(root, game);
        });
        layout.getChildren().add(level1Button);

        // Level 2 Button
        Button level2Button = new Button("Level 2");
        level2Button.setDisable(!isLevel1Completed); 
        level2Button.setOnAction(event -> {
            
        });
        layout.getChildren().add(level2Button);
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getLevel1Button() {
        return level1Button;
    }
}
