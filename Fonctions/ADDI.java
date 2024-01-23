package Fonctions ;

import Compilation.Register;
import java.lang.Math; 

public class ADDI {
    private int val1; 
    private int val2; 

    /*Constructeur de la classe ADDI.
     * @param val1, prend la première valeur de la commande ADDI 
     * @param val2, prend la deuxième valeur de la commande ADDI
     * @param R, Registre ou est stocké le résultat de la commande ADDI
     * @throw 
     */
    public ADDI(int val1, int val2, Register R)
    {
        this.val1 = val1; 
        this.val2 = val2; 
        this.Initialiser(R, val1, val2);
    }

    public int getVal1(){
        return val1; 
    }

    public int getVal2(){
        return val2; 
    }

    public static int calcul(int val1, int val2)
    {
        return Math.addExact(val1,val2); 
    }

    public void Initialiser(Register R, int val1, int val2)
    {
        R.setValeur(calcul(val1, val2));
    }
}
