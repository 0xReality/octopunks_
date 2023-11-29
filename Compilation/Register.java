package Compilation;

public class Register {
    private int valeur;
    private String name;
    private boolean isGlobal;

    public Register(int valeur, String name){
        this.valeur = valeur;
        this.name = name;
        this.isGlobal = false;
    }

    public int getValeur() {
        return valeur;
    }

    public String getName() {
        return name;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void setGlobal(boolean isGlobal) {
        this.isGlobal = isGlobal;
    }
}
