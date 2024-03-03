package UI.gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Compilation.Register;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ShowRegisters extends AnchorPane {
    private ArrayList<Register> registers = new ArrayList<>();
    private Map<String, Label> registerLabels = new HashMap<>();

    public ShowRegisters(double y, double x) {
        registers = new ArrayList<Register>();

        registerLabels.put("X", createLabel("X "));
        registerLabels.put("T", createLabel("T "));
        registerLabels.put("F", createLabel("F "));
        registerLabels.put("M", createLabel("M "));

        updateRegisters(registers);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(registerLabels.values());
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        AnchorPane.setBottomAnchor(vbox, x+10);
        AnchorPane.setLeftAnchor(vbox, y);
        this.setBorder(new Border(new BorderStroke(Color.RED, 
            BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
        this.getChildren().add(vbox);
    }

    public Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font("Arial", 14.2));
        label.setTextFill(Color.GRAY);
        
        Image image = new Image("file:resources/exa_cell.png");
        double aspectRatio = image.getWidth() / image.getHeight();
    
    
        double newWidth = 211 * 0.62;
        double newHeight = newWidth / aspectRatio ;
    
        BackgroundSize backgroundSize = new BackgroundSize(
            newWidth, newHeight, false, false, false, false
        );
    
        BackgroundImage backgroundImage = new BackgroundImage(
            image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, backgroundSize
        );
    
        label.setBackground(new Background(backgroundImage));
        label.setPadding(new Insets(5, 10, 5, 10));
        label.setMinSize(newWidth, newHeight);
        label.setMaxSize(newWidth, newHeight);
    
        return label;
    }
    
    public void updateRegisters(ArrayList<Register> updatedRegisters) {
        for (Register updatedRegister : updatedRegisters) {
            for (Register register : registers) {
                if (register.getName().equals(updatedRegister.getName())) {
                    register.setValeur(updatedRegister.getValeur());
                    register.setGlobal(updatedRegister.isGlobal());
                    updateLabel(register);
                }
            }
        }
    }

    private void updateLabel(Register register) {
        Label label = registerLabels.get(register.getName());
        if (label != null) {
            label.setText(register.getName() + "        " + register.getValeur());
        }
    }

    public void setRegisters(ArrayList<Register> newRegisters) {
        this.registers = newRegisters;
    }
}
