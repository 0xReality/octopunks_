package UI.gameplay;

import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class CodeArea extends VBox {
    private TextArea textArea;
    private Label label;
    private Button deleteButton;

    public CodeArea(String labelText) {
        super(5); 
        this.setPadding(new Insets(5));
        label = new Label(labelText);

        textArea = new TextArea();
        textArea.setPrefWidth(400);
        textArea.setMaxWidth(400);

        deleteButton = new Button("Delete");

        VBox header = new VBox(5, label, deleteButton);
        
        Image cursor = new Image("file:resources/cursor/cursor.png");
        this.setCursor(new ImageCursor(cursor, 0, 0));

        this.getChildren().addAll(header, textArea);
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

}
