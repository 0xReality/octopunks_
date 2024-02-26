package Fonctions;

import Robot.EXA;

/*Permet au Robot de se déplacer dans le jeu */
public class LINK {
    
    public LINK(EXA exa, int position)
    {
        exa.setPositon(exa.getPosition()+position);
    }
    
}
