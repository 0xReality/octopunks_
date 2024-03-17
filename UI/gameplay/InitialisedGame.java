package UI.gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import Fonctions.MetaFichier.File;
import Robot.ObjetOctoPunk;
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

    private List<ObjetOctoPunk> objetsDansLeJeu;


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
        objetsDansLeJeu = new ArrayList<>();
       grille = new GridPane(); 

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                Rectangle rect = new Rectangle(259, 132); // taille de la cellulle de jeu
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
        argenImageView.setFitWidth(50);
        argenImageView.setPreserveRatio(true);

        ObjetOctoPunk OMoney = new ObjetOctoPunk("argnet", 1, argenImageView, 2, 3);

        ajouterObjetALaGrille(OMoney, OMoney.getCol(), OMoney.getRow());


        //Ajout du file
        Image File = new Image("file:resources/file.png"); 
        ImageView fileImageView = new ImageView(File);
        fileImageView.setFitWidth(100);
        fileImageView.setPreserveRatio(true);

        //ObjetOctoPunk OFile = new ObjetOctoPunk("argnet", 1, argenImageView, 4, 4);

        //ajouterObjetALaGrille(OFile, OFile.getCol(), OFile.getRow());

        addLabelPos("400", 4, 4);

        // File f1 = new File(400, 100);   

        //Ajout du Robot
        Image robotImage = new Image("file:resources/icon.png"); 
        robotView = new ImageView(robotImage);
        robotView.setFitWidth(80); // taille du robot
        robotView.setPreserveRatio(true);

        //numero de case pour savoir ou le robot est situé 
        int robotColumn = 0; 
        int robotRow = 0; 

        grille.add(robotView,robotColumn,robotRow);
        
        //allignement centrale du robot sur la case
        GridPane.setHalignment(robotView, HPos.CENTER);
        GridPane.setValignment(robotView, VPos.CENTER);

        AnchorPane.setTopAnchor(grille, 74.5);
        AnchorPane.setLeftAnchor(grille, 553.0);

        Scene scene = new Scene(grille); 
        stage.setScene(scene);
        stage.setTitle("Terrain de jeu Octopunks");
        stage.show();
    }

    //position de la grille de jeu dans la scene
    // public void positionGameGrille(){
    //     AnchorPane.setTopAnchor(grille, 75.0);
    //     AnchorPane.setRightAnchor(grille, 60.0);
    
    // }
    

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

    public void ajouterObjetALaGrille(ObjetOctoPunk objet, int colonne, int ligne) {
    if (objet != null && objet.getImage() != null) {
        grille.add(objet.getImage(), colonne, ligne);
        GridPane.setHalignment(objet.getImage(), HPos.CENTER);
        GridPane.setValignment(objet.getImage(), VPos.CENTER);
        objetsDansLeJeu.add(objet);
        }
    }
       
    public void supprimerObjetDeLaGrille(ObjetOctoPunk objet) {
        if (objet != null && objet.getImage() != null) {
            grille.getChildren().remove(objet.getImage());
            objetsDansLeJeu.remove(objet);
        }
    }

    public List<ObjetOctoPunk> getObjetsDansLeJeu() {
        return objetsDansLeJeu;
    }


}

