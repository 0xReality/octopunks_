package UI.gameplay;

import Data.LevelData;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class Terminal extends VBox {

    private TextArea textArea;
    @SuppressWarnings("unused")
    private LevelData data;

    public Terminal(int x, int y, LevelData data) {
        textArea = new TextArea();
        textArea.setWrapText(true);
        this.data = data;
        textArea.setEditable(false); 

        // Set dark theme styles and remove all borders and focus indicators
        textArea.setStyle("-fx-control-inner-background: black; " +
                          "-fx-text-fill: limegreen; " +
                          "-fx-font-family: s'Consolas'; " +
                          "-fx-font-size: 16px; " +
                          "-fx-border-color: transparent; " + 
                          "-fx-focus-color: transparent; " + 
                          "-fx-faint-focus-color: transparent; " + 
                          "-fx-background-color: black; " + 
                          "-fx-background-insets: 0; " +
                          "-fx-shadow-highlight-color: transparent;" + 
                          "-fx--background-radius: 250;");
        
        textArea.setPrefSize(x, y);
        if(data != null) print(data.getMissionInfo(), "white");
        this.getChildren().add(textArea);
    }

    public void print(String text, String color) {
        textArea.setStyle("-fx-control-inner-background: black; " +
                          "-fx-text-fill: " + color + "; " +
                          "-fx-font-family: 'Consolas'; " +
                          "-fx-font-size: 18px; " +
                          "-fx-border-color: transparent; " +
                          "-fx-focus-color: transparent; " + 
                          "-fx-faint-focus-color: transparent; " + 
                          "-fx-background-color: black; " + 
                          "-fx-background-insets: 0; " + 
                          "-fx-shadow-highlight-color: transparent;" +
                          "-fx--background-radius: 10;"); 
        textArea.appendText(text + "\n");
    }

    public void print(String[] text, String color) {
        textArea.setStyle("-fx-control-inner-background: black; " +
                          "-fx-text-fill: " + color + "; " +
                          "-fx-font-family: 'Consolas'; " +
                          "-fx-font-size: 18px; " +
                          "-fx-border-color: transparent; " +
                          "-fx-focus-color: transparent; " + 
                          "-fx-faint-focus-color: transparent; " + 
                          "-fx-background-color: black; " + 
                          "-fx-background-insets: 0; " + 
                          "-fx-shadow-highlight-color: transparent;" +
                          "-fx--background-radius: 10;"); 
        for (String string : text) {
            textArea.appendText(string+ "\n");
        }
        
    }


    public void remove() {
        textArea.clear();
    }
}
