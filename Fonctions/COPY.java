package Fonctions;

import Compilation.Register;

public class COPY implements Cloneable {

    private Register registre1; 
    private Register registre2; 

    public COPY(Register R1, Register R2)
    {
        this.registre2 = R2; 
        this.registre1 = (Register) R2.clone();
    }

    @Override
    protected COPY clone() throws CloneNotSupportedException {
        if(!(this instanceof Cloneable))
        {
            throw new CloneNotSupportedException(); 
        }

        COPY copie = (COPY) super.clone(); 
        copie.registre1 = copie.registre2; 
        return R2Clone;
    }
}
