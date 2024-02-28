package UI;

import UI.gameplay.Game;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ShowsLevels extends Scene{
    
    private Button level1Button; 
    private Button level2Button; 
    private Button ReturnButton; 
    private Menu mainMenu; 
    private boolean isLevel1Completed = false; 
    private VBox levelslayout; 

    public ShowsLevels(Menu MainMenu){

        super(new VBox(10)); 

        this.mainMenu = MainMenu; 
        
        levelslayout = (VBox) this.getRoot(); 
        levelslayout.setAlignment(Pos.CENTER);

        //Level 1 Button
        level1Button = new Button("Level 1"); 
        level1Button.setOnAction(event -> {
            Scene gamScene = new Game(1, mainMenu); 
            
            mainMenu.getStage().setFullScreen(true);
            
            FadeTransition fadeOut= new FadeTransition(Duration.millis(1000), mainMenu.getStage().getScene().getRoot()); 
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), mainMenu.getStage().getScene().getRoot()); 
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);

            fadeOut.setOnFinished(e ->{
            fadeIn.play();
            new SceneSwitch(mainMenu.getStage(), gamScene); 
            mainMenu.getStage().setScene(gamScene);
            mainMenu.getStage().setFullScreen(true); 
            

            });
            fadeOut.play();
    
        });
        levelslayout.getChildren().add(level1Button); 

        //Level 2 Button
        level2Button = new Button("Level 2"); 
        level2Button.setDisable(!isLevel1Completed);
        level2Button.setOnAction(event->{

        });
        levelslayout.getChildren().add(level2Button); 

        //Return Button 
        ReturnButton = new Button("Return"); 
        ReturnButton.setOnAction(event->{
            mainMenu.getStage().setScene(mainMenu.getMainMenuScene());
            mainMenu.getStage().setFullScreen(true);
        });
        levelslayout.getChildren().add(ReturnButton); 

    }
    
}
