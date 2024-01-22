package Fonctions ;

import Compilation.Register;

public class MODI {
    private int val1; 
    private int val2; 
    private Register registre; 

    /*Constructeur de la classe MODI.
     * @param val1, prend la première valeur de la commande MODI
     * @param val2, prend la deuxième valeur de la commande MODI
     * @param R, Registre ou est stocké le résultat de la commande MODI
     */
    public MODI(int val1, int val2, Register R)
    {
        this.val1 = val1; 
        this.val2 = val2; 
        this.registre = R;
    }

    public int getVal1() {
        return val1;
    }

    public int getVal2() {
        return val2;
    }

    public String getRegister(){
        return registre.getName(); 
    }

    public static int calcul(int val1, int val2)
    {
        return Math.floorMod(val1, val2); 
    }

    public void Initialiser(Register R, int val1, int val2)
    {
        R.setValeur(calcul(val1, val2));
    }
}
