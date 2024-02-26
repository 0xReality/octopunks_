package Compilation;

import java.util.Random;

import UI.gameplay.ShowRegisters;
import UI.gameplay.Terminal;

public class DoubleCompilator {
    private Compilator compilator1;
    private Compilator compilator2;
    private Random random;
    protected Terminal terminal;
    

    /**
     * Constructeur pour DoubleCompilator. Compile toutes les lignes de deux textes donnés.
     * @param s1 Le premier texte à compiler.
     * @param s2 Le deuxième texte à compiler.
     * @param terminal L'interface du terminal.
     * @param sr1 L'interface pour afficher les registres (première instance).
     * @param sr2 L'interface pour afficher les registres (deuxième instance).
     */
    public DoubleCompilator(
        String s1, String s2, Terminal terminal,
        ShowRegisters sr1, ShowRegisters sr2) { 
        this.terminal = terminal;
        this.compilator1 = new Compilator(s1, terminal, sr1);
        this.compilator2 = new Compilator(s2, terminal, sr2);

        //permet d'assigner le meme registre aux 2 valeurs
        Register sharedM = compilator1.getRegisters().get(3);
        compilator2.getRegisters().set(3, sharedM);


        this.random = new Random();
    }


    /**
     * Compile toutes les lignes des deux codes, dans un ordre aléatoire.
     */
    public void compileAll() {
        while (!compilator1.isCompilationComplete() || !compilator2.isCompilationComplete()) {
            if (random.nextBoolean()) {
                if (!compilator1.isCompilationComplete()) {
                    compilator1.compileNextLine();
                    compilator2.getSr().updateRegisters(compilator2.registers);
                }
            } else {
                if (!compilator2.isCompilationComplete()) {
                    compilator2.compileNextLine();
                    compilator1.getSr().updateRegisters(compilator1.registers);
                }
            }
        }
    }

    /**
     * Compile la prochaine ligne de l'un des deux codes, choisie aléatoirement.
     * @return 0 si la compilation est toujours en cours, 1 si la compilation est terminée.
     */
    public int compileNextLine() {
        if (compilator1.isCompilationComplete() && compilator2.isCompilationComplete()) {
            return 1;
        }
        

        if (random.nextBoolean()) {
            if (!compilator1.isCompilationComplete()) {
                compilator1.compileNextLine();
            }
        } else {
            if (!compilator2.isCompilationComplete()) {
                compilator2.compileNextLine();
            }
        }

        return (compilator1.isCompilationComplete() && compilator2.isCompilationComplete()) ? 1 : 0;
    }

}
