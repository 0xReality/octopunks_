package Compilation;

/**
 * La classe Register représente un registre utilisé dans le processus de compilation.
 */
public class Register {
    private int valeur;       // La valeur stockée dans le registre
    private String name;      // Le nom du registre
    private boolean isGlobal; // Indicateur si le registre est global ou non

    /**
     * Constructeur de la classe Register.
     * @param valeur La valeur initiale du registre.
     * @param name Le nom du registre.
     */
    public Register(int valeur, String name) {
        this.valeur = valeur;
        this.name = name;
        this.isGlobal = false; // Par défaut, un registre n'est pas global
    }

    /**
     * Obtient la valeur du registre.
     * @return La valeur actuelle du registre.
     */
    public int getValeur() {
        return valeur;
    }

    /**
     * Obtient le nom du registre.
     * @return Le nom du registre.
     */
    public String getName() {
        return name;
    }

    /**
     * retourne si le registre est global ou non
     * @return isGlobal
     */
    public boolean isGlobal(){
        return isGlobal;
    }

    /**
     * Définit la valeur du registre.
     * @param valeur La nouvelle valeur à affecter au registre.
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Définit si le registre est global ou non.
     * @param isGlobal Vrai si le registre doit être global, faux sinon.
     */
    public void setGlobal(boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

}
