package UI.gameplay;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExaInfo extends VBox{
    //size == nombres de lignes
    private int size;
    private final static int maxSize = 50;
    private Label sizeLabel;

    //le total des instruction éffectué
    private int cycles;
    private Label cyclesLabel;

    //le nb de fois LINK et KILL sont utilisé
    private int activity;
    private Label activityLabel;

    //les labels pour le nom
    private Label sizeLabelText = new Label("  SIZE  ");
    private Label cyclesLabelText = new Label("  CYCLES  ");
    private Label activityLabelText = new Label("  ACTIVITY");

    public ExaInfo(){

        HBox hbox = new HBox();
        sizeLabel = new Label("    "+ size +" / "+maxSize);
        cyclesLabel = new Label("    " + cycles + "    ");
        activityLabel = new Label("    "+ activity);

        String labelStyle = "-fx-font-size: 20px; -fx-text-fill: #be0027;";
        sizeLabel.setStyle(labelStyle);
        cyclesLabel.setStyle(labelStyle);
        activityLabel.setStyle(labelStyle);

        hbox.setSpacing(100.0);

        hbox.getChildren().addAll(cyclesLabel, sizeLabel, activityLabel);

        HBox hbox2 = new HBox();

        String labelStyle2 = "-fx-font-size: 15px; -fx-text-fill: #193532;";

        hbox2.getChildren().addAll(cyclesLabelText, sizeLabelText, activityLabelText);
        
        sizeLabelText.setStyle(labelStyle2);
        cyclesLabelText.setStyle(labelStyle2);
        activityLabelText.setStyle(labelStyle2);

        hbox2.setSpacing(100.0);

        this.setSpacing(3.5);

        this.getChildren().addAll(hbox, hbox2);
    }

    public void updateValues(Integer size, Integer cycles, Integer activity) {
        if (size != null) {
            this.size = size;
            sizeLabel.setText("    "+ size +" / "+maxSize);
        }
        if (cycles != null) {
            this.cycles = cycles;
            cyclesLabel.setText("    " + cycles + "    ");
        }
        if (activity != null) {
            this.activity = activity;
            activityLabel.setText("    "+ activity);
        }
    }
    
    public void setSize(int size) {
        this.size = size;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }
    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public int getActivity() {
        return activity;
    }

    public int getCycles() {
        return cycles;
    }

    public int getSize() {
        return size;
    }

    public static int getMaxsize() {
        return maxSize;
    }
}
