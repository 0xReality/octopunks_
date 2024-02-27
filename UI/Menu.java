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
    
    private Stage OctoPunks;
    private AnchorPane root;

    private Button playButton;
    private Button level1Button;
    private Button logoutButton; 
    private Button SettingsButton; 
    private Button ReturnButton;
    

    private boolean isLevel1Completed = false;
    private VBox layout; 


    public  Menu(AnchorPane root, Stage OctoPunks) {
        super(root);
        this.root = root;
        this.OctoPunks = OctoPunks;
        loadCursor();

        VBox layout = new VBox(10); 
        layout.setAlignment(Pos.CENTER);



        // Play Button
        playButton = new Button("Play");
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

        playButton.setOnAction(event->{
            OctoPunks.setScene(new ShowsLevels(this));
            OctoPunks.setFullScreen(true);
        });

        //ppour centrer le button
        AnchorPane.setTopAnchor(layout, 0.0);
        AnchorPane.setRightAnchor(layout, 0.0);
        AnchorPane.setBottomAnchor(layout, 0.0);
        AnchorPane.setLeftAnchor(layout, 0.0);

        root.getChildren().add(layout);
    }

    public Stage getStage(){
        return OctoPunks; 
    }

    private void loadCursor(){
        Image cursor = new Image("file:resources/cursor/cursor.png");
        this.setCursor(new ImageCursor(cursor, 0,0));
    }

    public void returnToMenu(){
        root.getChildren().setAll(layout);
    }
 
    public Button getPlayButton() {
        return playButton;
    }


    public Button getLevel1Button() {
        return level1Button;
    }

    public Scene getMainMenuScene(){
        return this; 
    }
}
