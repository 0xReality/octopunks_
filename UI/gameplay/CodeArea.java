package UI.gameplay;

import javafx.scene.ImageCursor;
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
    
    @SuppressWarnings("unused")
    private Label label;


    public CodeArea(String labelText, LevelData data, ExaInfo exaInfo, NewExa instance, SetButtons sb) {
        //super(5); 
        //this.setPadding(new Insets(5));
        label = new Label(labelText);

        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPrefWidth(250);
        textArea.setPrefHeight(150);

        textArea.setStyle("-fx-control-inner-background: #19161c; " +
                          "-fx-text-fill: #515058; " +
                          "-fx-font-family: 'Consolas'; " +
                          "-fx-font-size: 16px; " +
                          "-fx-border-color: transparent; " + 
                          "-fx-focus-color: transparent; " + 
                          "-fx-faint-focus-color: transparent; " + 
                          "-fx-background-color: black; " + 
                          "-fx-shadow-highlight-color: transparent;" +
                          "-fx-border-color: rgba(54,45,51,255); " +
                          "-fx-border-width: 2px;" +
                          "-fx-border-radius: 2;");

        //// deleteButton = new Button("Delete");

        String[] savedCode;
        
        if(instance.getTextAreaContainer().size() <= 0){
            savedCode = data.getSavedCode();
            if (savedCode != null) {
                textArea.setText(String.join("\n", savedCode));
                instance.updateExaInfoSize();
            }
        }else{
            savedCode = data.getSavedCode2();
            if (savedCode != null) {
                textArea.setText(String.join("\n", savedCode));
                instance.updateExaInfoSize();
            }
        }
    

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if(textArea.getText() .isBlank()){
                sb.getBtnRun().setDisable(true);
                sb.getBtnStep().setDisable(true);
            }else{
                sb.getBtnRun().setDisable(false);
                sb.getBtnStep().setDisable(false);
            }

            textArea.setText(newValue.toUpperCase());

            if(instance.getTextAreaContainer().size() <= 1){
                data.setSavedCode(newValue.split("\n"), 1);
            }else{
                data.setSavedCode(newValue.split("\n"), 2);
            }
            
            instance.updateExaInfoSize();
        });






        //VBox header = new VBox(5, label,deleteButton);
        
        Image cursor = new Image("file:resources/cursor/cursor.png");
        this.setCursor(new ImageCursor(cursor, 0, 0));
        // this.setBorder(new Border(new BorderStroke(Color.RED, 
        //     BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
        this.getChildren().addAll(textArea);
    }



    public TextArea getTextArea() {
        return textArea;
    }

    // public Button getDeleteButton() {
    //     return deleteButton;
    // }


    public int getLineNumber(){
        return textArea.getText().split("\n").length;
    }
}
