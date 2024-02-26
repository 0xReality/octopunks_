package UI.gameplay;

import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
        
        textArea.setPrefSize(440, 160);
        // Add the TextArea to the VBox
        this.getChildren().add(textArea);

        AnchorPane.setBottomAnchor(this,70.0);
        AnchorPane.setRightAnchor(this, 58.0);
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
