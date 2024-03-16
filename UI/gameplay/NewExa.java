package UI.gameplay;

import java.util.ArrayList;
import java.util.List;

import Data.LevelData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;

import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NewExa extends VBox{
    private int exaCount = 0;
    private VBox middleBox;


    private LevelData data;
    private ExaInfo exaInfo;
    private SetButtons sb;

    private ShowRegisters registers1;    
    private ShowRegisters registers2;

    
    private Button createBox;

    public NewExa (LevelData data, ExaInfo exaInfo, SetButtons sb){
        this.sb = sb;
        this.data = data;
        this.exaInfo = exaInfo;
        VBox topBox = new VBox();
        topBox.setPadding(new Insets(11,10,9,10));
        topBox.setAlignment(Pos.CENTER);

        registers1 = new ShowRegisters(0, 0);
        registers2 = new ShowRegisters(0, 0);

        middleBox = new VBox();
        VBox.setMargin(middleBox, new Insets(0,14,10,14));//haut,droite,bas,gauche
        middleBox.setSpacing(10);
        middleBox.setPadding(new Insets(12,0,10,0));
        middleBox.setStyle("-fx-background-color: rgba(14,1,4,255);"
        
                        + "-fx-background-radius: 5;"
                        );
        createBox = new Button("createBox");
        createBox.setMaxSize(300, 30);

        topBox.setPrefSize(420, 30);

        //middleBox.setMaxWidth(420);
        this.setMaxSize(420,1080);

        
        // topBox.setBorder(new Border(new BorderStroke(Color.BLACK, 
        //     BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
        
        
        // middleBox.setBorder(new Border(new BorderStroke(Color.BLUE, 
        //     BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
        createBox.setBorder(new Border(new BorderStroke(Color.GREEN, 
            BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));

        Image topImage = new Image("file:resources/list_detail_top.png",400,45,false,false);
        ImageView topImageView = new ImageView(topImage);


        topBox.getChildren().add(topImageView);

        createBox.setOnAction(event -> createExa());

        // zone.getChildren().addAll(textAreaContainer,registersContainer);
        

        // this.setTop(topBox);
        // this.setCenter(middleBox);
        // this.setCenter(createBox);
        this.setPadding(new Insets(0));
        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(topBox,middleBox,createBox);
        createExa();
        
    }


    public void updateExaInfoSize() {
        int totalLines = 0;

        // Iterate through each exaBox in middleBox
        for (Node node : middleBox.getChildren()) {
            if (node instanceof VBox) {
                VBox exaBox = (VBox) node;
                // Assuming the exaContainer is the second child of exaBox
                if (exaBox.getChildren().size() > 1 && exaBox.getChildren().get(1) instanceof HBox) {
                    HBox exaContainer = (HBox) exaBox.getChildren().get(1);
                    for (Node exaChild : exaContainer.getChildren()) {
                        if (exaChild instanceof CodeArea) {
                            CodeArea codeArea = (CodeArea) exaChild;
                            int lineCount = codeArea.getLineNumber();
                            totalLines += lineCount;
                        }
                    }
                }
            }
        }
        exaInfo.updateValues(totalLines, null, null);
    }








    
    public void createExa() {

        
        if (exaCount < 2) {
            CodeArea codeArea = new CodeArea("Exa-" + (exaCount + 1), data, exaInfo, this, sb);
            ShowRegisters registers = new ShowRegisters(0, 0);
            
            HBox exaContainer = new HBox();
            //HBox.setMargin(exaContainer, new Insets(2,2,2,2));
            //exaContainer.setAlignment(Pos.CENTER);

            if (exaCount == 0) {
                registers1 = registers;
            } else if (exaCount == 1) {
                registers2 = registers;
            }
            exaContainer.getChildren().addAll(codeArea, registers);
            // exaContainer.setBorder(new Border(new BorderStroke(Color.YELLOW, 
            // BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
            exaContainer.setPadding(new Insets(2,2,2,2));
            exaContainer.setSpacing(2);
            exaContainer.setStyle("-fx-background-color: rgba(28,24,31,255);" +

                                    "-fx-faint-focus-color: transparent; " + 
                                    "-fx-border-color: rgba(49,35,39,255); " +
                                    "-fx-border-width: 2px;" +
                                    "-fx-border-radius: 0 0 3 3;"+
                                    "-fx-background-radius: 0 0 3 3;");
    
            Button deleteButton = new Button();
            deleteButton.setTranslateX(156);
            deleteButton.setTranslateY(-4);
            deleteButton.setStyle("-fx-focus-color: transparent; " +
                                    "-fx-padding: 0;" +
                                    "-fx-background-color: transparent;" +
                                    "-fx-border-color: transparent;" +
                                    "-fx-border-width: 0; " + 
                                    "-fx-faint-focus-color: transparent;"
                                    );
            Image delete_Image = new Image("file:resources/button_delete.png");
            Image delete_Hover_Image = new Image("file:resources/button_delete_hover.png");
            ImageView dImageView = new ImageView(delete_Image);
            //59 x 38
            dImageView.setPreserveRatio(true);
            dImageView.setFitWidth(29);
            StackPane graphicDelete = new StackPane();
            graphicDelete.getChildren().addAll(dImageView);
            deleteButton.setGraphic(graphicDelete);
            deleteButton.setOnMouseEntered(e ->dImageView.setImage(delete_Hover_Image));
            deleteButton.setOnMouseExited(e -> dImageView.setImage(delete_Image));

            StackPane topExaBox = new StackPane();
            Label numExa = new Label("EXA-" + (exaCount + 1));
            numExa.setStyle(
                "-fx-text-fill: rgba(17,4,7,255); "+
                "-fx-font-family: 'Montserrat Semibold'; "
            );
            

            Image file_Image = new Image("file:resources/file_bar_normal_popout.png");
            ImageView fImageView = new ImageView(file_Image);
            fImageView.setPreserveRatio(true);
            fImageView.setFitWidth(366);

            topExaBox.getChildren().addAll(fImageView,deleteButton,numExa);

            // topExaBox.setBorder(new Border(new BorderStroke(Color.GREEN, 
            // BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));

            VBox exaBox = new VBox(topExaBox, exaContainer);
            exaBox.setSpacing(3);
            //exaBox.setAlignment(Pos.CENTER);
            // exaBox.setPadding(new Insets(10));
            // exaBox.setBorder(new Border(new BorderStroke(Color.GREEN, 
            // BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
            //exaBox.setPadding(new Insets(3,3,3,3));
            VBox.setMargin(exaBox, new Insets(0,13,0,13));//haut,droite,bas,gauche
            
            deleteButton.setOnAction(event -> {
                // Remove the entire exaBox from middleBox
                exaContainer.getChildren().remove(codeArea);
                exaContainer.getChildren().remove(registers);
                exaBox.getChildren().remove(exaContainer);
                middleBox.getChildren().remove(exaBox);
                exaCount--;
                createBox.setDisable(false);
            });
    
            middleBox.getChildren().add(exaBox);
            exaCount++;
        }
        if (exaCount >= 2) {
            createBox.setDisable(true);
        }
        
    }


    public ShowRegisters getRegisters1() {
        return registers1;
    }

    public ShowRegisters getRegisters2() {
        return registers2;
    }

    public List<CodeArea> getTextAreaContainer()  {
    List<CodeArea> codeAreas = new ArrayList<>();
    
    // Iterate through each exaBox in middleBox
    for (Node node : middleBox.getChildren()) {
        if (node instanceof VBox) {
            VBox exaBox = (VBox) node;
            // Assuming the exaContainer is the second child of exaBox
            if (exaBox.getChildren().size() > 1 && exaBox.getChildren().get(1) instanceof HBox) {
                HBox exaContainer = (HBox) exaBox.getChildren().get(1);
                for (Node exaChild : exaContainer.getChildren()) {
                    if (exaChild instanceof CodeArea) {
                        codeAreas.add((CodeArea) exaChild);
                    }
                }
            }
        }
    }
    return codeAreas;

    }
}

