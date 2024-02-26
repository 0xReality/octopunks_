package Fonctions;

/* Permet de vérifier si un code rentrer dans le jeu est correct afin de rentrer dans une piéce */
public class TEST {

    public boolean testCode(int code, int vrai_code)
    {
        if(code == vrai_code)
        {
            return true;
        }
        return false; 
    }
}
