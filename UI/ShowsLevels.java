package UI;

import Data.CompletedLevels;
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
    private Button level3Button; 
    private Button ReturnButton; 
    //private Menu mainMenu; 
    private VBox levelslayout; 

    public ShowsLevels(Stage stage){

        //super(new VBox(10));
        //this.stage = stage;

       //this.mainMenu = MainMenu; 
        
        // levelslayout = (VBox) this.getRoot(); 
        levelslayout = new VBox();
        levelslayout.setAlignment(Pos.CENTER);

        level1Button = createLevelButton(1, stage, scene2);

        level2Button = createLevelButton(2, stage, scene2);

        level3Button = createLevelButton(3, stage, scene2);
        
        levelslayout.getChildren().addAll(level1Button,level2Button, level3Button);

        //Return Button 
        ReturnButton = new Button("Return"); 
        ReturnButton.setOnAction(e->{
            Menu ma = new Menu(stage); 
            new SceneSwitch(stage, ma.getMainScene()); 
        });
        
        levelslayout.getChildren().add(ReturnButton); 

        scene2 = new Scene(levelslayout,1920,1080);

    }

    public Button getReturnButton() {
        return ReturnButton;
    }

    public Scene getScene2() {
        return scene2;
    }

    private Button createLevelButton(int level, Stage stage, Scene levelSelectScene) {
        CompletedLevels levels = new CompletedLevels();
        Button levelButton = new Button("Level " + level);
        levelButton.setDisable(!levels.isLevelCompleted(level));
        levelButton.setOnAction(event -> {
            game = new Game(level, stage);
            Scene gameScene = game.getSceneGame();
    
            game.getSetButtons().getBtnExit().setOnAction(e -> new SceneSwitch(stage, levelSelectScene));
            new SceneSwitch(stage, gameScene);
        });
        return levelButton;
    }
    
}
