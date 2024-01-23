package Fonctions;

import Compilation.Register;

public class COPY{

    private Register registre1; 
    private Register registre2; 

    public COPY(Register R1, Register R2)
    {
        this.registre1 = R1; 
        this.registre2 = R2; 
        registre2.setValeur(registre1.getValeur());
        registre2.setGlobal(registre1.isGlobal());
    }
}
