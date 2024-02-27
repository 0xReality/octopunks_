package Compilation;

import java.util.ArrayList;

import UI.gameplay.ExaInfo;
import UI.gameplay.ShowRegisters;
import UI.gameplay.Terminal;

/**
 * La classe Compilator s'occupe du processus de compilation des instructions.
 */
public class Compilator {   
    LexicalAnalyser l;
    private Command[] lines;
    private int lineNumber;
    protected int currentLine;
    protected boolean endingCompilation;
    protected ArrayList<Register> registers;
    protected Terminal terminal;
    private ShowRegisters sr;
    private ExaInfo exaInfo;
    protected String[] text; //Sert à stocker le texte à compiler

    /**
     * Constructeur de Compilator. Compile toutes les lignes d'un texte donné.
     * @param s Le texte à compiler.
     */
    public Compilator(String s, Terminal terminal, ShowRegisters sr, ExaInfo exaInfo) {
        this.terminal = terminal;
        this.sr = sr;
        this.exaInfo = exaInfo;
        this.endingCompilation = false;
        this.text = s.split("\n");
        lines = new Command[text.length];
        this.lineNumber = text.length;
        this.registers = new ArrayList<Register>();
        initRegisters();
        sr.setRegisters(registers);
        sr.updateRegisters(registers);
        l = new LexicalAnalyser(registers);
        this.currentLine = 0;
    }

    /*Compile toutes les lignes d'un coup */
    public void compileAll() {
        preCompilation();
        while (currentLine < lineNumber) {
            if (!compileLine(currentLine)) {
                break;
            }
            currentLine++;
        }
        postCompilation();
    }

    /*Compile une ligne à la fois.
     *Cette méthode peut être appelée plusieurs fois pour progresser à travers le texte.
    */
    public int compileNextLine() {
        preCompilation();
        if (currentLine < lineNumber) {
            if (!compileLine(currentLine)) {
                postCompilation();
                return 0;
            }
            currentLine++;
        }
        if(currentLine >= lineNumber){
            postCompilation();
            return 1;
        }
        return 0;
    }


    private void preCompilation() {
        if(currentLine == 0){
            exaInfo.updateValues(null, 0, 0);
            terminal.remove();
            terminal.print("Compiling...", "white");
        }
    }

    protected boolean compileLine(int lineIndex) {
        lines[lineIndex] = l.argsToCommand(text[lineIndex].split(" "), lineIndex + 1);
        if(!l.checkErrors(lines[lineIndex], terminal)){
            endingCompilation = true;
            return false;
        }
        l.callInstruction(lines[lineIndex], this, exaInfo);
        sr.updateRegisters(registers);
        return true;
    }

    protected void postCompilation() {
        if(endingCompilation){
            reset();
            terminal.print("error: compilation aborted", "red");
        } else if(lineNumber == currentLine) {
            terminal.print("Compilation Done", "green");
            reset();
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
        endingCompilation = false;
    }

    public void setLine(int line){
        currentLine = line;
    }

    public int getCurrentLine()
    {
        return currentLine;
    }

    public ArrayList<Register> getRegisters() {
        return registers;
    }

    public boolean isCompilationComplete(){
        return currentLine == lineNumber;
    }

    public ShowRegisters getSr() {
        return sr;
    }
}
