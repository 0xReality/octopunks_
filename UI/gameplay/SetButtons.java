package UI.gameplay;

import javafx.geometry.Bounds;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.PopupWindow.AnchorLocation;
import javafx.stage.Window;
import javafx.util.Duration;

public class SetButtons extends AnchorPane {

    private Button btnStop;
    private Button btnRun;
    private Button btnStep;
    private Button btnExit;

    private void applyButtonStyle(Button button) {
        button.setStyle("-fx-focus-color: transparent; " +
                        "-fx-padding: 0;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-width: 0; " + 
                        "-fx-faint-focus-color: transparent;"
                        );
    }

    private ImageView creatImageView(Image image){
        ImageView imageView = new ImageView(image);
        //imageView.setPreserveRatio(true);
        //imageView.setFitWidth(100);
        imageView.setFitWidth(50);
        imageView.setFitHeight(65);
        return imageView;
    }
    // private StackPane createButtonGraphic(Image baseImage, Image overlayImage) {

    //     ImageView baseImageView = new ImageView(baseImage);
    //     ImageView overlayImageView = new ImageView(overlayImage);

    //     baseImageView.setFitWidth(38); // Set your desired width
    //     baseImageView.setFitHeight(64); // Set your desired height
    //     overlayImageView.setFitWidth(38); // Set your desired width
    //     overlayImageView.setFitHeight(64); // Set your desired height

    //     StackPane stackPane = new StackPane();
    //     stackPane.getChildren().addAll(baseImageView,overlayImageView);
    //     return stackPane;
    // }
    

    public SetButtons() {

        

        // Create the buttons
        btnStop = new Button();
        btnRun = new Button();
        btnStep = new Button();
        btnExit = new Button();

        ExaTip runTip = new ExaTip("file:resources/tooltip_frame.png", btnRun);

        HBox hbox = new HBox();
        // hbox.setStyle("-fx-padding: 0;" + "-fx-border-style: solid inside;"
        // + "-fx-border-width: 1;" + "-fx-border-insets: 0;"
        //  + "-fx-border-color: blue;");
        hbox.setAlignment(Pos.CENTER_LEFT); 
        

        // Load the hover/state image
        Image exitImage = new Image("file:resources/close.png");
        Image hoverExitImage = new Image("file:resources/close_hover.png");
        // création image avec les dimensions désirées (100x145 pour ce cas)
        WritableImage croppedExitImage = new WritableImage(exitImage.getPixelReader(),0, 1, 76, 144);
        WritableImage croppedHoverExitImage = new WritableImage(hoverExitImage.getPixelReader(),0, 1, 76, 144);

        
        Image normalImage = new Image("file:resources/playback_normal.png");
        Image hoverNormalImage = new Image("file:resources/playback_hover.png");
        //size (113 x 161) a modifier
        WritableImage croppedNormalImage = new WritableImage(normalImage.getPixelReader(), 4, 13, 100, 142);
        WritableImage croppedHoverNormalImage = new WritableImage(hoverNormalImage.getPixelReader(), 4, 13, 100, 142);

        Image stopImage = new Image("file:resources/playback_pause.png");
        Image hoverStopImage = new Image("file:resources/playback_pause_hover.png");
        //size (113 x 161) changer si necessaire
        WritableImage croppedStopImage = new WritableImage(stopImage.getPixelReader(), 4, 13, 100, 142);
        WritableImage croppedHoverStopImage = new WritableImage(hoverStopImage.getPixelReader(), 4, 13, 100, 142);

        Image stepImage = new Image("file:resources/playback_step.png");
        Image hoverStepImage = new Image("file:resources/playback_step_hover.png");
        //size (113 x 161) changer si necessaire
        WritableImage croppedStepImage = new WritableImage(stepImage.getPixelReader(), 4, 13, 100, 142);
        WritableImage croppedHoverStepImage = new WritableImage(hoverStepImage.getPixelReader(), 4, 13, 100, 142);

        Image runImage = new Image("file:resources/playback_play.png");
        Image hoverRunImage = new Image("file:resources/playback_play_hover.png");
        //size (113 x 161) changer si necessaire
        WritableImage croppedRunImage = new WritableImage(runImage.getPixelReader(), 4, 13, 100, 142);
        WritableImage croppedHoverRunImage = new WritableImage(hoverRunImage.getPixelReader(), 4, 13, 100, 142);
        
        
        //creation de l'image coupé dans ImageView
    
        ImageView stopNormalView = creatImageView(croppedNormalImage);
        ImageView stopView = creatImageView(croppedStopImage);

        ImageView stepNormalView = creatImageView(croppedNormalImage);
        ImageView stepView = creatImageView(croppedStepImage);

        ImageView runNormalView = creatImageView(croppedNormalImage);
        ImageView runView = creatImageView(croppedRunImage);
        
        ImageView exitView = new ImageView(croppedExitImage);
        //ImageView exitView = new ImageView(exitImage);
        //exitView.setPreserveRatio(true);
        exitView.setFitWidth(38);
        exitView.setFitHeight(64);

        //position bouton Exit
        btnExit.setTranslateX(1870);
        btnExit.setTranslateY(53);

        // //need to change Fit !!
        // normalStepView.setFitWidth(38);
        // normalStepView.setFitHeight(64);
        // normalStopView.setFitWidth(38);
        // normalStopView.setFitHeight(64);

        // stopView.setFitWidth(38);
        // stopView.setFitHeight(64);

        // stepView.setFitWidth(38);
        // stepView.setFitHeight(64);

        AnchorPane.setTopAnchor(hbox, 780.3);
        //AnchorPane.setRightAnchor(hbox,0.0);
        // AnchorPane.setBottomAnchor(hbox, -100.0);
        AnchorPane.setLeftAnchor(hbox, 592.5);

       
        //creaction des stack pour les images
        StackPane graphicStop = new StackPane();
        StackPane graphicStep = new StackPane();
        StackPane graphicRun = new StackPane();

        // StackPane graphicStep = new StackPane();
        graphicStop.getChildren().addAll(stopNormalView,stopView);
        graphicStep.getChildren().addAll(stepNormalView,stepView);
        graphicRun.getChildren().addAll(runNormalView,runView);
        
        btnStop.setGraphic(graphicStop);
        btnStep.setGraphic(graphicStep);
        btnRun.setGraphic(graphicRun);

        btnExit.setGraphic(exitView);
        //Hovering Exit
        btnExit.setOnMouseEntered(e -> exitView.setImage(croppedHoverExitImage));
        btnExit.setOnMouseExited(e -> exitView.setImage(croppedExitImage));

        // Change the image when hovering "Stop"
        btnStop.setOnMouseEntered(e -> {
            stopNormalView.setImage(croppedHoverNormalImage);
            stopView.setImage(croppedHoverStopImage);
        });
        
        btnStop.setOnMouseExited(e -> {
            stopNormalView.setImage(croppedNormalImage);
            stopView.setImage(croppedStopImage);
        });
        
        // Change the image when hovering "Step"
        btnStep.setOnMouseEntered(e -> {
            stepNormalView.setImage(croppedHoverNormalImage);
            stepView.setImage(croppedHoverStepImage);
        });
        
        btnStep.setOnMouseExited(e -> {
            stepNormalView.setImage(croppedNormalImage);
            stepView.setImage(croppedStepImage);
        });
        // Change the image when hovering "Run"
        btnRun.setOnMouseEntered(e -> {
            runNormalView.setImage(croppedHoverNormalImage);
            runView.setImage(croppedHoverRunImage);
        });
        
        btnRun.setOnMouseExited(e -> {
            runNormalView.setImage(croppedNormalImage);
            runView.setImage(croppedRunImage);
        });
        
        applyButtonStyle(btnStop);
        applyButtonStyle(btnRun);
        applyButtonStyle(btnStep);
        applyButtonStyle(btnExit);

        hbox.getChildren().addAll(btnStop, btnStep, btnRun);
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
