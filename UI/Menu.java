package UI;


import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu{

    private Stage stage;
    private Scene mainScene;
    private Scene scene2;
    //private StackPane stackPane1;
    private VBox vBox;

    private Button playButton;
    private Button logoutButton; 
    private Button settingsButton;

    public Menu(Stage stage){
        this.stage = stage;
        //Scene Principale
        mainScene = createSceneOne();
        scene2 = createSceneTwo();
        
        
        
        
    }

    public Scene createSceneOne(){
        StackPane stack = new StackPane(); 
        vBox = new VBox(5);
        // vBox.setStyle("-fx-padding: 5;" +
        //             "-fx-border-style: solid inside;" + 
        //             "-fx-border-width: 2;" +
        //             "-fx-border-insets: 5;" + 
        //             "-fx-border-radius: 5;" + 
        //             "-fx-border-color: blue;");
 
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        
        vBox.setMaxSize(200, 200);
        Loader menu= new Loader("file:resources/menu/MenuLeVrai.png");

        playButton = new Button("Play");
        settingsButton = new Button("Settings");
        logoutButton = new Button("Exit");

        logoutButton.setOnAction(event -> {
            stage.close();
        });

        playButton.setOnAction(event -> {
                new SceneSwitch(stage,scene2);
        });

        vBox.setStyle("-fx-background-color: rgba(218,44,167,0.9);" +
                        "-fx-border-color: rgba(251,203,210,255); " +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 10;"+
                        "-fx-border-insets: 10;"+
                        "-fx-background-radius:15;");
        vBox.getChildren().addAll(playButton,settingsButton,logoutButton);
        stack.getChildren().addAll(menu, vBox); 
        // stackPane1 = new StackPane();
        // StackPane.setAlignment(vBox,Pos.CENTER);
        // stackPane1.getChildren().addAll(vBox);
        // mainScene = new Scene(stackPane1,0,0);
        Utils.setCustomCursor(stack);
        mainScene = new Scene(stack,1920,1080);
        // Utils.setCustomCursor(mainScene);
        return mainScene;
    }

    public Scene createSceneTwo(){
        
        ShowsLevels sl = new ShowsLevels(stage);
        sl.getReturnButton().setOnAction(event->{
            new SceneSwitch(stage,mainScene);

        });
        
        return sl.getScene2();
        
    }

    // public void switchScenes(Scene scene){
    //     stage.setScene(scene);
    // }
    // public Stage getStage(){
    //     return stage;
    // }
    public Scene getMainScene(){
        return mainScene;
    }
    public Scene getScene2(){
        return scene2;
    }
    public Button getLogoutButton() {
        return logoutButton;
    }
    public Button getPlayButton() {
        return playButton;
    }

}