package Fonctions;

import java.util.ArrayList;

import Compilation.Register;
import Robot.EXA;
import Robot.ObjetOctoPunk;
import UI.gameplay.InitialisedGame;

public class GRAB{
    
    public GRAB(int label, EXA exa, ArrayList<Register> r)
    {

        ObjetOctoPunk leGrab;

        InitialisedGame game =  exa.getGame();
        
        Integer dest = game.getPosForLabel(Integer.toString(label));
        int x = dest % 5;
        int y = dest / 5;

        for (ObjetOctoPunk o : game.getObjetsDansLeJeu()) {
            if(o.getCol() == x && o.getRow() == y){
                new LINK(game).Link(exa, dest); 
                leGrab = o; 
                exa.addObjet(leGrab);
                exa.setHandUsed(true);
                if(leGrab.getContenu() != null){
                    r.get(2).setValeur(leGrab.getContenu());
                }
                break;
            }
        }

    }
}
