package UI;

import UI.gameplay.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ShowsLevels{

    //private Stage stage;
    private Scene scene2;
    private Game game;
    private Button level1Button; 
    private Button level2Button; 
    private Button ReturnButton; 
    //private Menu mainMenu; 
    private boolean isLevel1Completed = false; 
    private VBox levelslayout; 

    public ShowsLevels(Stage stage){

        //super(new VBox(10));
        //this.stage = stage;

       //this.mainMenu = MainMenu; 
        
        // levelslayout = (VBox) this.getRoot(); 
        levelslayout = new VBox();
        levelslayout.setAlignment(Pos.CENTER);

        //Level 1 Button
        level1Button = new Button("Level 1"); 
         level1Button.setOnAction(event -> {

            game = new Game(1, stage);
            Scene gamScene = game.getSceneGame() ;

            game.getSetButtons().getBtnExit().setOnAction(e ->{
                new SceneSwitch(stage,scene2); 
            });

            new SceneSwitch(stage, gamScene);
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
        
        levelslayout.getChildren().add(ReturnButton); 

        scene2 = new Scene(levelslayout,1920,1080);

    }

    public Button getReturnButton() {
        return ReturnButton;
    }

    public Scene getScene2() {
        return scene2;
    }
}
