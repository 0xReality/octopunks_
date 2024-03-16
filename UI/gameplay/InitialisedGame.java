package UI.gameplay;

import java.util.HashMap;
import java.util.Map;

import Fonctions.MetaFichier.File;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class InitialisedGame {

    // grille de jeu 
    private GridPane grille; 

    private ImageView robotView; 
    private Stage stage; 

    // pour pouvoir gérer les positions du robot sur la grille (Commande LINK)
    private Map<String,Integer> labelPos; 

    // pour pouvoir gérer les positions 
    private Map<Integer, ImageView> objImage ; 
    private Map<Integer, Integer[]> obj;


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

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                Rectangle rect = new Rectangle(260, 135); // taille de la cellulle de jeu
                rect.setStroke(javafx.scene.paint.Color.BLACK);   // couleur du contour
                rect.setFill(javafx.scene.paint.Color.BROWN);
                //ici on doit ajouter tout les gestion du robot 

                
                grille.add(rect, i,j); 
            }
        }

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
        argenImageView.setFitWidth(100);
        argenImageView.setFitWidth(100);

        int ArgentColumn = 2; 
        int ArgentRow = 3; 

        grille.add(argenImageView, ArgentColumn, ArgentRow);
        GridPane.setHalignment(argenImageView, HPos.CENTER);
        GridPane.setValignment(argenImageView, VPos.CENTER);

        //Ajout du file
        Image File = new Image("file:resources/file.png"); 
        ImageView fileImageView = new ImageView(File);
        fileImageView.setFitWidth(100);
        fileImageView.setFitWidth(100);
        int fileColumn = 4; 
        int fileRow = 4; 

        grille.add(fileImageView, fileColumn, fileRow);
        GridPane.setHalignment(fileImageView, HPos.CENTER);
        GridPane.setValignment(fileImageView, VPos.CENTER);

        addLabelPos("400", 4, 4);

        File f1 = new File(400, 100);   

        //Ajout du Robot
        Image robotImage = new Image("file:resources/icon.png"); 
        robotView = new ImageView(robotImage);
        robotView.setFitWidth(100); // taille du robot
        robotView.setFitHeight(100);

        //numero de case pour savoir ou le robot est situé 
        int robotColumn = 0; 
        int robotRow = 0; 

        grille.add(robotView,robotColumn,robotRow);
        
        //allignement centrale du robot sur la case
        GridPane.setHalignment(robotView, HPos.CENTER);
        GridPane.setValignment(robotView, VPos.CENTER);


        Scene scene = new Scene(grille); 
        stage.setScene(scene);
        stage.setTitle("Terrain de jeu Octopunks");
        stage.show();
    }

    //position de la grille de jeu dans la scene
    public void positionGameGrille(){
        AnchorPane.setTopAnchor(grille, 75.0);
        AnchorPane.setRightAnchor(grille, 60.0);

    }

    public ImageView getImageView(){
        return robotView; 
    }

    public void setImageView(int column, int row){
        grille.getChildren().remove(getImageView()); 
        grille.add(getImageView(), column, row);

        GridPane.setHalignment(robotView, HPos.CENTER);
        GridPane.setValignment(robotView, VPos.CENTER);
    }

    public GridPane getGrille(){
        return grille; 
    }

}

