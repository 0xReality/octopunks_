package Fonctions;

import Robot.EXA; 

public class HALT {
    private EXA exa; 
    public HALT(EXA ex){
        this.exa = ex; 
        exa.setActive(false); 
    }
}
