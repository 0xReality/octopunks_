package Compilation;

public class Register {
    private int valeur;
    private boolean isGlobal;

    public Register(int valeur){
        this.valeur = valeur;
        this.isGlobal = false;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void setGlobal(boolean isGlobal) {
        this.isGlobal = isGlobal;
    }
}
