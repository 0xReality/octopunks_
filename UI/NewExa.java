package UI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class NewExa extends BorderPane {
    private int exaCount = 0;
    private VBox textAreaContainer;
    private Button createExaButton;

    public NewExa() {
        textAreaContainer = new VBox(10); // VBox for TextAreas
        textAreaContainer.setPadding(new Insets(10));

        this.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
        this.setPadding(new Insets(10));

        createExaButton = new Button("Create new Exa");
        createExaButton.setOnAction(event -> createExa());
        createExa();

        HBox buttonContainer = new HBox(createExaButton);
        buttonContainer.setAlignment(Pos.CENTER); 
        buttonContainer.setPadding(new Insets(10, 0, 10, 0));


        this.setCenter(textAreaContainer);
        this.setBottom(buttonContainer);
    }

    public void createExa() {
        if (exaCount < 2) {
            CodeArea codeArea = new CodeArea("Exa-" + (exaCount+1));
            textAreaContainer.getChildren().add(codeArea);
    
            codeArea.getDeleteButton().setOnAction(event -> {
                textAreaContainer.getChildren().remove(codeArea);
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
}
