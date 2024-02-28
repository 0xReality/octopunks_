package UI.gameplay;

import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import Data.LevelData;
import javafx.geometry.Insets;

public class CodeArea extends VBox {
    private TextArea textArea;
    private Label label;
    private Button deleteButton;

    public CodeArea(String labelText, LevelData data) {
        super(5); 
        this.setPadding(new Insets(5));
        label = new Label(labelText);

        textArea = new TextArea();
        textArea.setPrefWidth(260);
        textArea.setPrefHeight(140);

        textArea.setStyle("-fx-control-inner-background: #19161c; " +
                          "-fx-text-fill: #515058; " +
                          "-fx-font-family: 'Consolas'; " +
                          "-fx-font-size: 16px; " +
                          "-fx-border-color: transparent; " + 
                          "-fx-focus-color: transparent; " + 
                          "-fx-faint-focus-color: transparent; " + 
                          "-fx-background-color: black; " + 
                          "-fx-background-insets: 0; " +
                          "-fx-shadow-highlight-color: transparent;" +
                          "-fx-border-color: #54454b; " +
                          "-fx-border-width: 2px;");

        deleteButton = new Button("Delete");

        String[] savedCode = data.getSavedCode();
        if (savedCode != null) {
            textArea.setText(String.join("\n", savedCode));
        }

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            data.setSavedCode(newValue.split("\n"));
        });



        VBox header = new VBox(5, label, deleteButton);
        header.setBorder(new Border(new BorderStroke(Color.RED,             
            BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
        
        // Image cursor = new Image("file:resources/cursor/cursor.png");
        // this.setCursor(new ImageCursor(cursor, 0, 0));

        this.getChildren().addAll(header, textArea);
    }



    public TextArea getTextArea() {
        return textArea;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

}
