package UI.gameplay;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class SetButtons extends AnchorPane {

    private Button btnStop;
    private Button btnRun;

    public SetButtons() {
        // Create the buttons
        btnStop = new Button("Stop");
        btnRun = new Button("Run");

        btnStop.setPrefSize(100, 40);
        btnRun.setPrefSize(100, 40);  

        HBox hbox = new HBox();
        hbox.getChildren().addAll(btnStop, btnRun);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER); 
        

        AnchorPane.setTopAnchor(hbox, 1080.0 / 2.0);
        AnchorPane.setRightAnchor(hbox,0.0);
        AnchorPane.setBottomAnchor(hbox, 0.0);
        AnchorPane.setLeftAnchor(hbox, 860.0);

        this.getChildren().add(hbox);
    }

    public Button getBtnRun() {
        return btnRun;
    }
}
