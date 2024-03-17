package UI.gameplay;

import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ExaTip extends Tooltip {
    public ExaTip(String path, Button btn){
        Image toolTipBg = new Image(path);
        ImageView ViewToolTip = new ImageView(toolTipBg);

        ViewToolTip.setFitHeight(70);
        ViewToolTip.setFitWidth(400);

        this.setShowDelay(Duration.seconds(0.4));

        this.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0;");

        this.setGraphic(ViewToolTip);

        this.setOnShowing(event -> {
            Bounds bounds = btn.localToScreen(btn.getBoundsInLocal());
            this.setX(bounds.getMinX());
            this.setY(bounds.getMinY() - this.getHeight());
        });

        Tooltip.install(btn, this);

    }
}
