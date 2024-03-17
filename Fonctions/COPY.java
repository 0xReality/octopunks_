package Fonctions;

import Compilation.Register;

public class COPY{

    public COPY(Register R1, Register R2)
    { 
        R2.setValeur(R1.getValeur());
        R2.setGlobal(R1.isGlobal());
    }
}
