package UI.gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Compilation.Register;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ShowRegisters extends AnchorPane {
    private ArrayList<Register> registers = new ArrayList<>();
    private Map<String, Label> registerLabels = new HashMap<>();

    public ShowRegisters(double y, double x) {
        // Initialize labels for different registers
        registers = new ArrayList<Register>();

        registerLabels.put("X", createLabel("X: "));
        registerLabels.put("T", createLabel("T: "));
        registerLabels.put("F", createLabel("F: "));
        registerLabels.put("M", createLabel("M: "));

        HBox hbox = new HBox();
        hbox.getChildren().addAll(registerLabels.values());
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        AnchorPane.setTopAnchor(hbox, x); // Half of 1080
        AnchorPane.setLeftAnchor(hbox, y); // Half of 1200

        this.getChildren().add(hbox);
    }

    public Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font("Arial", 16));
        label.setTextFill(Color.BLUE);
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, null, null);
        label.setBackground(new Background(backgroundFill));
        label.setPadding(new Insets(5, 10, 5, 10));
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
            label.setText(register.getName() + ": " + register.getValeur());
        }
    }

    public void setRegisters(ArrayList<Register> newRegisters) {
        this.registers = newRegisters;
        // Optionally update all labels here if needed
    }
}
