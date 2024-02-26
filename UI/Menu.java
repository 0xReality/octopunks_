package UI;

import UI.gameplay.Game;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Scene {
    
    @SuppressWarnings("unused")
    private Stage OctoPunks;
    private AnchorPane root;
    private Scene mainMenuScene; 

    private Button playButton;
    private Button level1Button;
    private Button logoutButton; 
    private Button SettingsButton; 
    private Button ReturnButton;
    

    private boolean isLevel1Completed = false;



    public  Menu(AnchorPane root, Stage OctoPunks) {
        super(root);
        this.root = root;
        this.OctoPunks = OctoPunks;
        loadCursor();
        this.mainMenuScene = this; 

        VBox layout = new VBox(10); 
        layout.setAlignment(Pos.CENTER);



        // Play Button
        playButton = new Button("Play");
        playButton.setOnAction(event -> showLevels(layout));

        layout.getChildren().add(playButton);


        //  Settings Button 
        SettingsButton = new Button("Settings"); 
        layout.getChildren().add(SettingsButton); 

        //  Logout Button 
        logoutButton = new Button("Exit"); 
        logoutButton.setOnAction(event -> {
            OctoPunks.close();
        });

        layout.getChildren().add(logoutButton); 

        

        //ppour centrer le button
        AnchorPane.setTopAnchor(layout, 0.0);
        AnchorPane.setRightAnchor(layout, 0.0);
        AnchorPane.setBottomAnchor(layout, 0.0);
        AnchorPane.setLeftAnchor(layout, 0.0);

        root.getChildren().add(layout);
    }


    private void loadCursor(){
        Image cursor = new Image("file:resources/cursor/cursor.png");
        this.setCursor(new ImageCursor(cursor, 0,0));
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

        // Return Button (ne marche pas pour l'instant)
        ReturnButton = new Button("Return"); 
        ReturnButton.setOnAction(event -> 
            OctoPunks.setScene(this)
        );
        layout.getChildren().add(ReturnButton); 
        
    }
 
    public Button getPlayButton() {
        return playButton;
    }


    public Button getLevel1Button() {
        return level1Button;
    }

    public Scene getMainMenuScene(){
        return mainMenuScene; 
    }
}
