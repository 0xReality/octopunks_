package UI.gameplay;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import Data.LevelData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class NewExa extends BorderPane {
    private int exaCount = 0;
    private VBox textAreaContainer;
    private Button createExaButton;
    private VBox registersContainer;
    private ShowRegisters registers1;    
    private ShowRegisters registers2;
    private LevelData data;

    public NewExa(LevelData data) {
        HBox hbox = new HBox();
        this.data = data;
    
        textAreaContainer = new VBox(5); 
        registersContainer = new VBox(5); 
        textAreaContainer.setPadding(new Insets(20, -10, 10, 20));
        registersContainer.setPadding(new Insets(80, 20, 10, 20));

        registersContainer.setSpacing(80);

    
        hbox.getChildren().addAll(textAreaContainer, registersContainer);
        this.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
        this.setPadding(new Insets(10));
    
        createExaButton = new Button("Create new Exa");
        createExaButton.setOnAction(event -> createExa());
    
        HBox buttonContainer = new HBox(createExaButton);
        buttonContainer.setBorder(new Border(new BorderStroke(Color.RED,BorderStrokeStyle.SOLID,null,null)));
        buttonContainer.setAlignment(Pos.CENTER); 
        buttonContainer.setPadding(new Insets(10, 0, 10, 0));
        
    
        this.setCenter(hbox);
        this.setBottom(buttonContainer);

        createExa();
    }
    

    public void createExa() {
        if (exaCount < 2) {
            CodeArea codeArea = new CodeArea("Exa-" + (exaCount + 1), data);


            ShowRegisters registers = new ShowRegisters(0, 0); 

            if (exaCount == 0) {
                registers1 = registers;
            } else if (exaCount == 1) {
                registers2 = registers;
            }

            textAreaContainer.getChildren().add(codeArea);
            registersContainer.getChildren().add(registers); 
            codeArea.getDeleteButton().setOnAction(event -> {
                textAreaContainer.getChildren().remove(codeArea);
                registersContainer.getChildren().remove(registers);
                exaCount--;
                createExaButton.setDisable(false);
            });

            exaCount++;
        }

        if (exaCount >= 2) {
            createExaButton.setDisable(true);
        }
    }
    

    public void removeExa() {
        if (exaCount > 0) {
            textAreaContainer.getChildren().remove(textAreaContainer.getChildren().size() - 1);
            exaCount--;
            createExaButton.setDisable(false);
        }
    }

    public VBox getTextAreaContainer() {
        return textAreaContainer;
    }

    public ShowRegisters getRegisters1() {
        return registers1;
    }

    public ShowRegisters getRegisters2() {
        return registers2;
    }

}
