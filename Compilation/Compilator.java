package Compilation;

import java.util.ArrayList;

public class Compilator {
    LexicalAnalyser l;
    private Command[] lines;
    private int lineNumber;
    private int currentLine;
    private boolean endingCompilation;
    private ArrayList<Register> registers;

    //compiles toutes les lignes doit etre reset a la fin
    public Compilator(String s, int mode) {
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

        
        for (int i = 0; i < text.length; i++) {
            if(!l.checkErrors(lines[i])) endingCompilation = true;
        }
        if(endingCompilation){
            reset();
            System.out.println("error: compilation aborted");
            return;
        }
        
        /*
         * Appel a la methode callInstruction qui appel l'instruction
         * cmd.getInstruction() a sa propre methode
        */
        for (int i = 0; i < text.length; i++) {
            l.callInstruction(lines[i]);
        }
        

        /*
         * Appel reset qui réinitialise les variables de Compilator
         * Apres l'execution
         */
        reset();
        System.out.println("Compilation Done");
    }

    /*
    * Constructeur, compile ligne par ligne doit attendre un 
    * second appel de compileNextLine()
    * pour compiler les lignes d'apres 
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


    //reset le compilateur pour etre pret a une nouvelle compilation
    private void reset() {
        lines = null; 
        lineNumber = 0; 
        currentLine = 0;
        initRegisters();
    }

}
