package Fonctions;

import Compilation.Register;

public class COPY{

    private Register registre1; 
    private Register registre2; 

    public COPY(Register R1, Register R2)
    {
        if(R1.equals(R2))
        {
            throw new IllegalArgumentException("Les deux registres sont les mêmes !")
        }
        this.registre1 = R1; 
        this.registre2 = R2; 
        registre2.setValeur(registre1.getValeur());
        registre2.setGlobal(registre1.isGlobal());
    }
}
