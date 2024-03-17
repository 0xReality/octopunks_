package UI;

import Data.CompletedLevels;
import UI.gameplay.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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
        StackPane panel = new StackPane();
        // levelslayout = (VBox) this.getRoot();
        Image levelBackgroundImage = new Image("file:resources/menu/ShowLevel.png"); 
        ImageView showMenuImageView = new ImageView(levelBackgroundImage);
        showMenuImageView.setFitWidth(1920);
        showMenuImageView.setPreserveRatio(true);

        levelslayout = new VBox(10);
        levelslayout.setMaxSize(300, 300);
        levelslayout.setStyle("-fx-background-color: rgba(71,170,245,0.8);" +
                                    "-fx-border-color: white; " +
                                    "-fx-border-width: 2px;" +
                                    "-fx-border-radius: 10;"+
                                     "-fx-border-insets: 5;"+
                                    "-fx-background-radius:10;");
        levelslayout.setAlignment(Pos.CENTER);
        //Return Button 
        ReturnButton = new Button("Return"); 
        ReturnButton.setOnAction(e->{
            Menu ma = new Menu(stage); 
            new SceneSwitch(stage, ma.getMainScene()); 
        });

        level1Button = createLevelButton(1, stage, scene2);

        level2Button = createLevelButton(2, stage, scene2);

        level3Button = createLevelButton(3, stage, scene2);
        
        levelslayout.getChildren().addAll(level1Button,level2Button, level3Button,ReturnButton);
        
        
        

        panel.getChildren().addAll(showMenuImageView,levelslayout);

        Utils.setCustomCursor(panel);

        scene2 = new Scene(panel,1920,1080);
        Utils.setCustomCursor(scene2);
        

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
            new SceneSwitch(stage, gameScene);
        });
        return levelButton;
    }
    
}
