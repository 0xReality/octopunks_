package UI.gameplay;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import Data.LevelData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class NewExa extends VBox {
    private int exaCount = 0;
    private VBox mainBox;
    private VBox topVBox;
    private Button createExaButton;
    // private VBox textAreaContainer;
    // private VBox registersContainer;
    private ShowRegisters registers1;    
    private ShowRegisters registers2;
    private LevelData data;

    public NewExa(LevelData data) {

        mainBox = new VBox();
        topVBox = new VBox();

        this.data = data;
        this.setPadding(new Insets(5, 0, 0, 0));
        this.setPrefSize(420,1080);
        //Style of NewExa BOX PRINCIPALE
        this.setStyle("-fx-background-color: transparent");
        topVBox.setBorder(new Border(new BorderStroke(Color.BLACK, 
                                                            BorderStrokeStyle.SOLID,
                                                            null,
                                                            BorderStroke.THIN)));

        createExaButton = new Button("Create new Exa");
        
        createExaButton.setOnAction(event -> createExa(mainBox));


         //A changer
        BackgroundSize backgroundSize = new BackgroundSize(
            400, 50, false, false, false, false
        );
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:resources/list_segment.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER, backgroundSize
        );
        mainBox.setBackground(new Background(backgroundImage));
        mainBox.setMinSize(410, 67);


       
        BackgroundSize bgNewSolutionSize = new BackgroundSize(
            400, 45, false, false, false, false
        );
        BackgroundImage bgNewSolutionImage = new BackgroundImage(
            new Image("file:resources/list_detail_top.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER, bgNewSolutionSize
        );
        topVBox.setBackground(new Background(bgNewSolutionImage));
        topVBox.setMinSize(410, 58);
        topVBox.setPadding(new Insets(0, 0, 0, 10));
        

        
        // mainBox.setStyle("-fx-background-color: #0e0104;"+
        //                     "-fx-background-radius: 15px;");
        
        // HBox buttonContainer = new HBox(createExaButton);
        // buttonContainer.setBorder(new Border(new BorderStroke(Color.RED,
        //                                                         BorderStrokeStyle.SOLID,
        //                                                         null,
        //                                                         null)));
        // buttonContainer.setAlignment(Pos.CENTER); 
        // buttonContainer.setPadding(new Insets(10, 0, 10, 0));

        //createExaButton centré
        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(topVBox,mainBox,createExaButton);
        
        
    }
    
    public BorderPane createNewExaBox(VBox mainBox){

        
        BorderPane mainExaBox = new BorderPane();

        Button deleteButton = new Button();
        Button barButton = new Button();
        AnchorPane panelBox = new AnchorPane();
        

        HBox contentBox = new HBox();

        VBox textAreaContainer = new VBox(5);
        VBox registersContainer = new VBox(5);

        Image barImage = new Image("file:resources/exa_bar_normal.png");
        Image barHoverImage = new Image("file:resources/exa_bar_normal_hover.png");
        // WritableImage croppedBarImage = new WritableImage(barImage.getPixelReader(), 0, 0, 732, 53);
        // WritableImage croppedBarHoverImage = new WritableImage(barHoverImage.getPixelReader(), 0, 0, 732, 53);

        ImageView barView = new ImageView(barImage);
        ImageView barHoverView = new ImageView(barHoverImage);

        barView.setFitWidth(732/2);
        barView.setFitHeight(53/2);
        //732 x 53
        barHoverView.setFitWidth(410);
        barHoverView.setFitHeight(53);
        
        

        barButton.setGraphic(barView);
        AnchorPane.setLeftAnchor(barButton, 0.0);
        AnchorPane.setRightAnchor(deleteButton, 30.0);
        barButton.setStyle(
                        "-fx-padding: 0;" +
                        
                        
                        "-fx-border-width: 0; " 
                        
                        );

        mainExaBox.setBorder(new Border(new BorderStroke(Color.BLACK, 
                                                            BorderStrokeStyle.SOLID,
                                                            null,
                                                            BorderStroke.THIN)));
        //mainExaBox.setPadding(new Insets(15, 5, 5, 5));                                                    
        panelBox.setBorder(new Border(new BorderStroke(Color.RED,
                                                        BorderStrokeStyle.SOLID,
                                                        null,
                                                        BorderStroke.THIN)));
        contentBox.setBorder(new Border(new BorderStroke(Color.BLUE,
                                                        BorderStrokeStyle.SOLID,
                                                        null,
                                                        BorderStroke.THIN)));   
        textAreaContainer.setBorder(new Border(new BorderStroke(Color.GREEN, 
                                                                    BorderStrokeStyle.SOLID,
                                                                    null,
                                                                    BorderStroke.THIN)));
        registersContainer.setBorder(new Border(new BorderStroke(Color.PURPLE, 
                                                                    BorderStrokeStyle.SOLID,
                                                                    null,
                                                                    BorderStroke.THIN)));
        

        
        

        CodeArea codeArea = new CodeArea("Exa-" + (exaCount + 1), data);
        ShowRegisters registers = new ShowRegisters(0, 0); 
        
        textAreaContainer.getChildren().add(codeArea);
        registersContainer.getChildren().add(registers);
        contentBox.getChildren().addAll(textAreaContainer,registersContainer);
        panelBox.getChildren().addAll(barButton,deleteButton);
        mainExaBox.setTop(panelBox);
        mainExaBox.setBottom(contentBox);

       // mainBox.setPrefSize(200, 200);
        
        contentBox.setAlignment(Pos.CENTER);
        textAreaContainer.setAlignment(Pos.CENTER);
        registersContainer.setAlignment(Pos.CENTER);
        
        

        // Image barImage = new Image("file:resources/exa_bar_normal.png");
        // BackgroundSize bgBarSize = new BackgroundSize(
        //     400, 45, false, false, false, false
        // );
        // BackgroundImage bgBarImage = new BackgroundImage(
        //     new Image("file:resources/list_detail_top.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
        //     BackgroundPosition.CENTER, bgBarSize
        // );
        // topVBox.setBackground(new Background(bgBarImage));
        // topVBox.setMinSize(410, 58);
        


        deleteButton.setOnAction(event -> {
            mainBox.getChildren().remove(mainExaBox);
            exaCount--;
            createExaButton.setDisable(false);
        });

        return mainExaBox;

    }
    public void createExa(VBox mainBox) {

        if(exaCount == 0 ){

        }
        if (exaCount < 2) {
            
            BorderPane newExaBox = createNewExaBox(mainBox);
            mainBox.getChildren().add(newExaBox);

            // if (exaCount == 0) {
            //     registers1 = registers;
            // } else if (exaCount == 1) {
            //     registers2 = registers;
            // }
            exaCount++;
        }

        if (exaCount >= 2) {
            createExaButton.setDisable(true);
        }
    }
    

    public void removeExa(VBox targetVBox) {
        if (exaCount > 0) {
            // Removing the last added exaBox (assuming createExaButton is the last child in NewExa VBox)
            int lastExaBoxIndex = targetVBox.getChildren().size() - 2; // -2 to skip the createExaButton at the end
            targetVBox.getChildren().remove(lastExaBoxIndex);
            exaCount--;
            createExaButton.setDisable(false); // Re-enable the create button if it was disabled
        }
    }

    // public VBox getTextAreaContainer() {
    //     return textAreaContainer;
    // }

    public ShowRegisters getRegisters1() {
        return registers1;
    }

    public ShowRegisters getRegisters2() {
        return registers2;
    }

}
