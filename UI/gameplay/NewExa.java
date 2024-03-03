package UI.gameplay;

import java.util.ArrayList;
import java.util.List;

import Data.LevelData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;

import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;

import javafx.scene.layout.HBox;
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
        VBox topBox = new VBox(5);

        registers1 = new ShowRegisters(0, 0);
        registers2 = new ShowRegisters(0, 0);

        middleBox = new VBox(5);
        
        createBox = new Button("createBox");
        createBox.setMaxSize(300, 30);

        topBox.setPrefSize(420, 30);

        //middleBox.setMaxWidth(420);
        //this.setMaxSize(420,1080);

        
        topBox.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
        
        
        middleBox.setBorder(new Border(new BorderStroke(Color.BLUE, 
            BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
        createBox.setBorder(new Border(new BorderStroke(Color.GREEN, 
            BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));


        createBox.setOnAction(event -> createExa());

        // zone.getChildren().addAll(textAreaContainer,registersContainer);
        

        // this.setTop(topBox);
        // this.setCenter(middleBox);
        // this.setCenter(createBox);
        this.setPadding(new Insets(5));
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
            
            HBox exaContainer = new HBox(10);

            if (exaCount == 0) {
                registers1 = registers;
            } else if (exaCount == 1) {
                registers2 = registers;
            }
            exaContainer.getChildren().addAll(codeArea, registers);
            /* exaContainer.setBorder(new Border(new BorderStroke(Color.GREEN, 
            BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
            exaContainer.setPadding(new Insets(10)); */
    
            Button deleteButton = new Button("delete");
            VBox exaBox = new VBox(deleteButton, exaContainer);
            exaBox.setAlignment(Pos.CENTER);
            /*exaBox.setPadding(new Insets(10));
            exaBox.setBorder(new Border(new BorderStroke(Color.RED, 
            BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
            exaBox.setPadding(new Insets(10)); */
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

