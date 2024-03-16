package Fonctions;

import Robot.EXA;
import Robot.ObjetOctopunk;

public class GRAB <O>{
    
    public GRAB(O nfile, EXA exa)
    {
        exa.addObjet((ObjetOctopunk)nfile);
    }
}
