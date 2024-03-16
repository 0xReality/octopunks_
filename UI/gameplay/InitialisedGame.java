package UI.gameplay;

import java.util.HashMap;
import java.util.Map;

import Fonctions.MetaFichier.File;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class InitialisedGame {

    // grille de jeu 
    private GridPane grille; 
    private StackPane stack;

    private ImageView robotView; 
    private Stage stage; 

    // pour pouvoir gérer les positions du robot sur la grille (Commande LINK)
    private Map<String,Integer> labelPos; 


    public void addLabelPos(String label, int column, int row){
        int position = row * 5 + column; 
        labelPos.put(label,position); 
    }

    public Integer getPosForLabel(String label){
        return labelPos.get(label); 
    }

    public InitialisedGame(Stage sta){
        this.stage = sta; 
        labelPos = new HashMap<>(); 
    }

    public InitialisedGame(){
        labelPos = new HashMap<>(); 
    }

      //initialise la grille de jeu
    public void InitializeGameGrille(){

        
        grille = new GridPane();
        stack = new StackPane();
        
        // stack.setPadding(new Insets(5));
        
        Image backG = new Image("file:resources/background.png");
        ImageView backImageView = new ImageView(backG);
        backImageView.setFitWidth(1300);
        backImageView.setPreserveRatio(true);

        
        stack.setStyle("-fx-background-color: black; " );
                            stack.setBorder(new Border(new BorderStroke(Color.YELLOW, 
                                BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
                            
                            grille.setBorder(new Border(new BorderStroke(Color.GREEN, 
                                BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
        stack.setMaxSize(1300, 660);
        grille.setMaxSize(1000, 400);
        grille.setAlignment(Pos.BOTTOM_LEFT);
        grille.setTranslateX(-120);
        grille.setTranslateY(50);
        
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 3; j++){
                 // taille de la cellulle de jeu
                 Rectangle rect;
                   // couleur du contour

                if(i!=3 &&i!=7){
                    rect = new Rectangle(50,50);
                    rect.setStroke(Color.BLACK);    
                    if(i<3)
                        rect.setFill(Color.rgb(51,47,52));
                    else
                        rect.setFill(Color.BROWN);
                }
                else{
                    rect = new Rectangle(50,0);
                    if(j==1){
                        rect.setFill(Color.BLACK);
                        rect.setStroke(Color.BLACK);
                    }
                    else{
                        rect.setStroke(Color.TRANSPARENT);
                        rect.setFill(Color.TRANSPARENT);
                    }
                }
                //ici on doit ajouter tout les gestion du robot 
                grille.add(rect, i,j); 
            }
        }
    
        /*     i 0 1 2 3 4
         *  j   ._._._._._. 
         *  0   |0|1|2| |4|5|6| |8|9|10|          |0 |1 |2 |3 |4 |
         *  1   |0|1|2|3|4|          |5 |6 |7 |8 |9 |
         *  2   |0|1|2|3|4|          |10|11|12|13|14|
         *  3   |0|1|2|3|4|          |15|16|17|18|19|
         *  4   |0|1|2|3|4|          |20|21|22|23|24|
        */

        //Ajout du nombre 200 pour pouvoir se déplacer dans la première case du jeu 
        Label label200 = new Label("200"); 
        GridPane.setHalignment(label200, HPos.CENTER);
        GridPane.setValignment(label200, VPos.CENTER);
        grille.add(label200, 0, 0);
        addLabelPos("200", 0, 0);

        //Ajout de nombre pour pouvoir se déplacer dans la piéce 
        Label labelLink = new Label("800");
        
        GridPane.setHalignment(labelLink, HPos.CENTER);
        GridPane.setValignment(labelLink, VPos.CENTER);
        grille.add(labelLink, 2, 2);
        addLabelPos("800", 2, 2);

        
        //Ajout objet dans la piéce 
        Image Argent = new Image("file:resources/monnaie_vrai.png"); 
        ImageView argenImageView = new ImageView(Argent);
        argenImageView.setFitWidth(40);
        argenImageView.setPreserveRatio(true);

        int ArgentColumn = 2; 
        int ArgentRow = 3; 

        grille.add(argenImageView, ArgentColumn, ArgentRow);
        GridPane.setHalignment(argenImageView, HPos.CENTER);
        GridPane.setValignment(argenImageView, VPos.CENTER);

        //Ajout du file
        Image File = new Image("file:resources/file.png"); 
        ImageView fileImageView = new ImageView(File);
        fileImageView.setFitWidth(20);
        fileImageView.setPreserveRatio(true);
        // int fileColumn = 4; 
        // int fileRow = 4; 

        grille.add(fileImageView, 4, 4);
        GridPane.setHalignment(fileImageView, HPos.CENTER);
        GridPane.setValignment(fileImageView, VPos.CENTER);

        addLabelPos("400", 4, 4);

        File f1 = new File(400, 100);   

        //Ajout du Robot
        Image robotImage = new Image("file:resources/icon.png"); 
        robotView = new ImageView(robotImage);
        robotView.setFitWidth(40); // taille du robot
        robotView.setPreserveRatio(true);
        //allignement centrale du robot sur la case
        GridPane.setHalignment(robotView, HPos.CENTER);
        GridPane.setValignment(robotView, VPos.CENTER);
        //numero de case pour savoir ou le robot est situé 
        // int robotColumn = 0; 
        // int robotRow = 0; 
        grille.add(robotView,0,0);
        
        

        
       /*  AnchorPane.setTopAnchor(stack, 74.5);
        AnchorPane.setRightAnchor(stack, 66.0); */
        AnchorPane.setTopAnchor(stack, 74.5);
        AnchorPane.setLeftAnchor(stack, 553.0);
        

        stack.getChildren().addAll(backImageView,grille);
        // StackPane.setAlignment(stack, Pos.CENTER);
        
        Scene scene = new Scene(stack);
        scene.setFill(Color.rgb(209,63,73));
        stage.setScene(scene);
        // stage.setTitle("Terrain de jeu Octopunks");
        stage.show();
    }

    //position de la grille de jeu dans la scene
    /* public void positionGameGrille(){
        AnchorPane.setTopAnchor(grille, 75.0);
        AnchorPane.setRightAnchor(grille, 60.0);

    } */

    public ImageView getImageView(){
        return robotView; 
    }

    public void setImageView(int column, int row){
        grille.getChildren().remove(getImageView()); 
        grille.add(getImageView(), column, row);

        GridPane.setHalignment(robotView, HPos.CENTER);
        GridPane.setValignment(robotView, VPos.CENTER);
    }

    public StackPane getStackPane(){
        return stack; 
    }

}

