package Fonctions;

import Compilation.Compilator;

public class FJMP {

    public FJMP(int index, Compilator k){
        if(k.getRegisters().get(1).getValeur() == 0){
            k.setLine(index + k.getCurrentLine());
        }
       
    }
}
