package UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Loader extends AnchorPane{

    public Loader (String p1, String p2){
        Image img1;
        Image img2;
        ImageView imgView1;
        ImageView imgView2;
        if (p1 == null) return;
        else {
            img1 = new Image(p1,1920,1080,false,false);
            imgView1 = new ImageView(img1);
        }

        if (p2 == null) return;
        else {

            img2 = new Image(p2,1378,310,false,false);
            imgView2 = new ImageView(img2); 
            AnchorPane.setBottomAnchor(imgView2,0.0);
            AnchorPane.setRightAnchor(imgView2,13.0);
        }

        this.getChildren().addAll(imgView1, imgView2);

    }

    public Loader(String p1){
        Image img1;
        
        ImageView imgView1;

        if (p1 == null) return;
        else {
            img1 = new Image(p1,1920,1080,false,false);
            imgView1 = new ImageView(img1);
        }

        this.getChildren().addAll(imgView1);
    }
}
