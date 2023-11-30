package Fonctions ;

import Compilation.Register;

public class MULI {
    private int val1; 
    private int val2; 
    private Register registre; 

    /*Constructeur de la classe MULI.
     * @param val1, prend la première valeur de la commande MULI
     * @param val2, prend la deuxième valeur de la commande MULI
     * @param R, Registre ou est stocké le résultat de la commande MULI
     */
    public MULI(int val1, int val2, Register R)
    {
        this.val1 = val1; 
        this.val2 = val2; 
        this.registre = R;
        this.Initialiser(R, val1, val2);
    }


    public MULI(Register R1, Register R2, Register R)
    {
        this.registre = R; 
        this.Initialiser(R, R1.getValeur(), R2.getValeur());
    }

    public static int calcul(int val1, int val2)
    {
        return val1*val2; 
    }

    public void Initialiser(Register R, int val1, int val2)
    {
        R.setValeur(calcul(val1, val2));
    }
}
