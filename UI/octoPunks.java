package UI;

import Compilation.Compilator;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class octoPunks extends Application{

    private int width = 1980;
    private int height = 1080;
    private TextArea textArea;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("OctoPunks");

        BorderPane borderPane = new BorderPane();

        Image gameBg = new Image("file:resources/editor/bg.png");
        Image bgPanel = new Image("file:resources/editor/bg_panel.png");
        ImageView bgPanelView = new ImageView(bgPanel);
        bgPanelView.setFitWidth(1380);
        bgPanelView.setFitHeight(235);

        BackgroundImage bgImage = new BackgroundImage(
            gameBg, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.CENTER, 
            new BackgroundSize(100, 100, true, true, false, true)
        );
        BorderPane.setAlignment(bgPanelView, Pos.BOTTOM_RIGHT);
        borderPane.setBottom(bgPanelView);

        borderPane.setBackground(new Background(bgImage));

        Scene scene = new Scene(borderPane, width, height);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        stage.setHeight(1920);
        stage.setWidth(1080);
    }
    
}
