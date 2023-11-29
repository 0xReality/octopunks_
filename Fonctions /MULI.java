package Fonctions ;

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
    }

    public static int calcul(int val1, int val2)
    {
        return val1*val2; 
    }

    public void Initialiser(Register R, int val1, int val2)
    {
        R.valeur =  calcul(val1, val2); 
    }
}
