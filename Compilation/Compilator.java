package Compilation;

import java.util.ArrayList;

/**
 * La classe Compilator s'occupe du processus de compilation des instructions.
 */
public class Compilator {
    LexicalAnalyser l;
    private Command[] lines;
    private int lineNumber;
    private int currentLine;
    private boolean endingCompilation;
    private ArrayList<Register> registers;

    /**
     * Constructeur de Compilator. Compile toutes les lignes d'un texte donné.
     * @param s Le texte à compiler.
     *  TODO: crée un nouveau constructeur pour gerer la compilation par pas
     */
    public Compilator(String s) {
        System.out.println("Compiling...");
        this.endingCompilation = false;
        String[] text = s.split("\n");
        lines = new Command[text.length];
        this.lineNumber = text.length;
        this.registers = new ArrayList<Register>();
        initRegisters();
        l = new LexicalAnalyser(registers);

        for (int i = 0; i < lineNumber; i++) {
            this.currentLine = i + 1;
            lines[i] = l.argsToCommand(text[i].split(" "), currentLine);
        }


        // Vérifie s'il y a des erreurs dans chaque ligne
        for (int i = 0; i < lineNumber; i++) {
            currentLine = i;
            if(!l.checkErrors(lines[i])) endingCompilation = true;
        }
        if(endingCompilation){
            reset();
            System.out.println("error: compilation aborted");
            return;
        }
        
        // Exécute les instructions de chaque ligne
        for (currentLine = 0; currentLine < lineNumber; currentLine++) {
            l.callInstruction(lines[currentLine], this);
        }

 
        // Réinitialise les variables du compilateur après l'exécution
        reset();
        System.out.println("Compilation Done");
    }

    /**
     * Initialise les registres utilisés dans le compilateur.
     */
    private void initRegisters(){
        Register X = new Register(0, "X");
        this.registers.add(X);
        Register T = new Register(0, "T");
        this.registers.add(T);
        Register F = new Register(0, "F");
        this.registers.add(F);
        Register M = new Register(0, "M");
        this.registers.add(M);
    }

    /**
     * Réinitialise le compilateur pour être prêt à une nouvelle compilation.
     */
    private void reset() {
        lines = null;
        lineNumber = 0;
        currentLine = 0;
        initRegisters();
    }

    public void setLine(int line){
        currentLine = line;
    }

}
