package Fonctions;

import Robot.EXA;
import UI.gameplay.*;

/*Permet au Robot de se déplacer dans le jeu */
public class LINK {
    
    InitialisedGame game; 

    public LINK(InitialisedGame ga){
        this.game = ga; 
    }
    
    public void Link(EXA exa, Integer label){

       /*  Integer pos = game.getPosForLabel(label);
        if(pos == null){
            System.out.println("Erreur ce label n'existe pas");
        } */

        exa.setPositon(label);

        int newColumn = label % 5; 
        int newRow = label / 5; 

        game.setImageView(newColumn, newRow);
        exa.setPositon(label);
                 
    }
    
}
