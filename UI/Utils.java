package UI;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Utils {
    public static void setCustomCursor(Node node) {
        Image image = new Image("file:resources/cursor/cursor.png");
        Cursor cursor = new ImageCursor(image, 76.5, 76.5);
        node.setCursor(cursor);
    }

    public static void setCustomCursor(Scene scene) {
        Image image = new Image("file:resources/cursor/cursor.png");
        Cursor cursor = new ImageCursor(image, 76.5, 76.5);
        scene.setCursor(cursor);
    }
}
