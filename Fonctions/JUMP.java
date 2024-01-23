package Fonctions;

import Compilation.Compilator;

public class JUMP {
    private int index; 


    
    public JUMP(int index, Compilator k)
    {
        k.setLine(index);
    }
}
