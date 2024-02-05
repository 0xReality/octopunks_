package Compilation;

import java.util.ArrayList;

import UI.gameplay.ShowRegisters;
import UI.gameplay.Terminal;

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
    private Terminal terminal;
    private ShowRegisters sr;
    private String[] text; //Sert à stocker le texte à compiler

    /**
     * Constructeur de Compilator. Compile toutes les lignes d'un texte donné.
     * @param s Le texte à compiler.
     */
    public Compilator(String s, Terminal terminal, ShowRegisters sr) {
        this.terminal = terminal;
        this.sr = sr;
        this.endingCompilation = false;
        this.text = s.split("\n");
        lines = new Command[text.length];
        this.lineNumber = text.length;
        this.registers = new ArrayList<Register>();
        initRegisters();
        sr.setRegisters(registers);
        sr.updateRegisters(registers);
        l = new LexicalAnalyser(registers);
    }

    /*Compile toutes les lignes d'un coup */
    public void compileAll(){
        terminal.remove();
        terminal.print("Compiling...", "white");

        for(int i=0; i < lineNumber; i++){
            this.currentLine = i + 1;
            lines[i] = l.argsToCommand(text[i].split(" "), currentLine);
            if(!l.checkErrors(lines[i], terminal)){
                endingCompilation = true;
                break;
            }
        }

        if(endingCompilation){
            reset();
            terminal.print("error: compilation aborted", "red");
            return;
        }

        for(int i = 0; i < lines.length; i++){
            l.callInstruction(lines[i], this);
            currentLine++;
            sr.updateRegisters(registers);
        }

        terminal.print("Compilation Done", "green");
    }

    /*Compile une ligne à la fois.
     *Cette méthode peut être appelée plusieurs fois pour progresser à travers le texte.
    */
    public void compileLineByLine(){
        if(currentLine < lineNumber){
            lines[currentLine] = l.argsToCommand(text[currentLine].split(" "), currentLine + 1);
            if(!l.checkErrors(lines[currentLine], terminal)){
                endingCompilation = true;
                terminal.print("error: compilation aborted at line " + (currentLine + 1), "red");
                return;
            }

            l.callInstruction(lines[currentLine], this);
            sr.updateRegisters(registers);
            currentLine++;
        }   else{
            terminal.print("All lines have been compiled", "green");
        }
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

    public int getCurrentLine()
    {
        return currentLine;
    }
}
