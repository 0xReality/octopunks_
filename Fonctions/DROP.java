package Fonctions;

import java.util.ArrayList;

import Compilation.Register;
import Robot.EXA;
import Robot.ObjetOctoPunk;


public class DROP{
    
    public DROP(EXA exa, ArrayList<Register> r)
    {
        ObjetOctoPunk leDrop = (ObjetOctoPunk) exa.getInventaire().getObjet(0);
        int x = exa.getPosition() % 5; 
        int y = exa.getPosition() / 5; 
        exa.removeObjet(leDrop, x, y);
        exa.setHandUsed(false);
    }

}

