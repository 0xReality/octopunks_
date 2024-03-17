package Fonctions;

import Robot.EXA;
import Robot.ObjetOctoPunk;
import UI.gameplay.InitialisedGame;

public class GRAB{
    
    public GRAB(int label, EXA exa)
    {
        //verifier si il a quelque chose si oui le drop
        
        int x = 3;
        int y = 2;

        ObjetOctoPunk leGrab;

        InitialisedGame game =  exa.getGame();
        
        for (ObjetOctoPunk o : game.getObjetsDansLeJeu()) {
            if(o.getCol() == x && o.getRow() == y){
                leGrab = o; 
                exa.getInventaire().ajouter(leGrab);
                new LINK(game).Link(exa, label);
                break;
            }
        }
    }
}
