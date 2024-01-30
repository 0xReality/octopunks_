package UI;

import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Terminal extends VBox {

    private TextArea textArea;

    public Terminal() {
        textArea = new TextArea();
        textArea.setEditable(false); 

        // Set dark theme styles
        textArea.setStyle("-fx-control-inner-background: black; " +
                          "-fx-text-fill: limegreen; " +
                          "-fx-font-family: 'Consolas'; " +
                          "-fx-font-size: 16px;");
        
        textArea.setPrefSize(700, 250);
        // Add the TextArea to the VBox
        this.getChildren().add(textArea);

        AnchorPane.setBottomAnchor(this,-265.0);
        AnchorPane.setRightAnchor(this, -400.0);
    }

    public void print(String text, String color) {
        textArea.setStyle("-fx-control-inner-background: black; " +
                          "-fx-text-fill: " + color + "; " +
                          "-fx-font-family: 'Consolas'; " +
                          "-fx-font-size: 18px;");
        textArea.appendText(text + "\n");
    }

    public void remove() {
        textArea.clear();
    }
}
