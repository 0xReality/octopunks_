package UI.gameplay;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import Data.LevelData;
import javafx.geometry.Insets;

public class NewExa extends VBox {
    private int exaCount = 0;
    // private HBox newExaBox;
    private Button createExaButton;
    // private VBox textAreaContainer;
    // private VBox registersContainer;
    private ShowRegisters registers1;    
    private ShowRegisters registers2;
    private LevelData data;

    public NewExa(LevelData data) {

        
        this.data = data;
        this.setBorder(new Border(new BorderStroke(Color.YELLOW, 
                                                    BorderStrokeStyle.SOLID,
                                                    null,
                                                    BorderStroke.THIN)));
        this.setPadding(new Insets(10));
    
        createExaButton = new Button("Create new Exa");
        createExaButton.setOnAction(event -> createExa());
    
        
        // HBox buttonContainer = new HBox(createExaButton);
        // buttonContainer.setBorder(new Border(new BorderStroke(Color.RED,
        //                                                         BorderStrokeStyle.SOLID,
        //                                                         null,
        //                                                         null)));
        // buttonContainer.setAlignment(Pos.CENTER); 
        // buttonContainer.setPadding(new Insets(10, 0, 10, 0));
        
    this.getChildren().add(createExaButton);
        
    
    }
    
    public HBox createNewExaBox(){

        HBox newExaBox = new HBox();
        VBox textAreaContainer = new VBox(5);
        textAreaContainer.setBorder(new Border(new BorderStroke(Color.BLUE, 
                                                                    BorderStrokeStyle.SOLID,
                                                                    null,
                                                                    BorderStroke.THIN)));
        VBox registersContainer = new VBox(5);
        registersContainer.setBorder(new Border(new BorderStroke(Color.GREEN, 
                                                                    BorderStrokeStyle.SOLID,
                                                                    null,
                                                                    BorderStroke.THIN)));
        textAreaContainer.setPadding(new Insets(20, -10, 10, 20));
        registersContainer.setPadding(new Insets(80, 20, 10, 20));

        //registersContainer.setSpacing(80);

        newExaBox.getChildren().addAll(textAreaContainer, registersContainer);

        newExaBox.setBorder(new Border(new BorderStroke(Color.BLACK, 
                                                    BorderStrokeStyle.SOLID,
                                                    null,
                                                    BorderStroke.THIN)));
        newExaBox.setPadding(new Insets(5));

        CodeArea codeArea = new CodeArea("Exa-" + (exaCount + 1), data);
        ShowRegisters registers = new ShowRegisters(0, 0); 
        
        textAreaContainer.getChildren().add(codeArea);
        registersContainer.getChildren().add(registers);

        codeArea.getDeleteButton().setOnAction(event -> {
            this.getChildren().remove(newExaBox);
            exaCount--;
            createExaButton.setDisable(false);
        });
        return newExaBox;

    }
    public void createExa() {


        if (exaCount < 2) {

            HBox newExaBox = createNewExaBox();
            this.getChildren().add(newExaBox);

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
    

    public void removeExa() {
        if (exaCount > 0) {
            // Removing the last added exaBox (assuming createExaButton is the last child in NewExa VBox)
            int lastExaBoxIndex = this.getChildren().size() - 2; // -2 to skip the createExaButton at the end
            this.getChildren().remove(lastExaBoxIndex);
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
