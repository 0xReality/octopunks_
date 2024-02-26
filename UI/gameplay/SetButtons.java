package UI.gameplay;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class SetButtons extends AnchorPane {

    private Button btnStop;
    private Button btnRun;
    private Button btnStep;
    private Button btnExit;

    public SetButtons() {
        // Create the buttons
        btnStop = new Button("Stop");
        btnRun = new Button("Run");
        btnStep = new Button("Step");
        btnExit = new Button();

        btnStop.setPrefSize(100, 40);
        btnRun.setPrefSize(100, 40);  
        btnStep.setPrefSize(100, 40);  
        

        HBox hbox = new HBox();
        hbox.getChildren().addAll(btnStop, btnRun, btnStep);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER); 
        

        // Load the hover/state image
        Image imageExit = new Image("file:resources/close.png");
        Image hoverImageExit = new Image("file:resources/close_hover.png");

        // création image avec les dimensions désirées (100x145 pour ce cas)
        WritableImage croppedImageExit = new WritableImage(imageExit.getPixelReader(),0, 1, 76, 144);
        WritableImage croppedHoverImageExit = new WritableImage(hoverImageExit.getPixelReader(),0, 1, 76, 144);
        
        // ajout de l'image coupé dans ImageView
        ImageView viewExit = new ImageView(croppedImageExit);
        //ImageView viewExit = new ImageView(imageExit);
        //viewExit.setPreserveRatio(true);
        
        viewExit.setFitWidth(38);
        viewExit.setFitHeight(64);

        AnchorPane.setTopAnchor(hbox, 1080.0 / 2.0);
        AnchorPane.setRightAnchor(hbox,0.0);
        AnchorPane.setBottomAnchor(hbox, 0.0);
        AnchorPane.setLeftAnchor(hbox, 860.0);

        btnExit.setTranslateX(1870);
        btnExit.setTranslateY(53);

        btnExit.setGraphic(viewExit);

        btnExit.setStyle("-fx-focus-color: transparent; " +
                            "-fx-padding: 0;" +
                            "-fx-background-color: transparent;" +
                            "-fx-border-color: transparent;"+
                            "-fx-border-width: 0; " + 
                            "-fx-faint-focus-color: transparent;");
                        
                              
        // Change the image when hovering
        btnExit.setOnMouseEntered(e -> viewExit.setImage(croppedHoverImageExit));
        btnExit.setOnMouseExited(e -> viewExit.setImage(croppedImageExit));

        this.getChildren().addAll(hbox,btnExit);
    }

    public Button getBtnRun() {
        return btnRun;
    }

    public Button getBtnStep() {
        return btnStep;
    }

    public Button getBtnStop() {
        return btnStop;
    }

    public Button getBtnExit() {
        return btnExit;
    }
}
