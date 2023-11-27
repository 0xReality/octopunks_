package Compilation;

public class Compilator {
    LexicalAnalyser l = new LexicalAnalyser();
    private Command[] lines;
    private int lineNumber;
    private int currentLine;
    private boolean endingCompilation;

    //compiles toutes les lignes doit etre reset a la fin
    public Compilator(String s, int mode) {
        System.out.println("Compiling...");
        this.endingCompilation = false;
        String[] text = s.split("\n");
        lines = new Command[text.length];
        this.lineNumber = text.length;
        for (int i = 0; i < lineNumber; i++) {
            this.currentLine = i + 1;
            lines[i] = l.argsToCommand(text[i].split(" "), currentLine);
        }

        
        for (int i = 0; i < text.length; i++) {
            if(!l.CheckErrors(lines[i])) endingCompilation = true;
        }
        if(endingCompilation){
            reset();
            System.out.println("error: compilation aborted");
            return;
        }
        
        /*
         * Appel a la methode sendSignal(Command cmd) qui appel l'instruction
         * cmd.getInstruction() a sa propre methode
        */

        /*
         * Appel a une nouvelle methode qui reset les variables de Compilator
         */

        reset();
        System.out.println("Compilation Done");
    }

    /*
    * Constructeur, compile ligne par ligne doit attendre un 
    * second appel de compileNextLine()
    * pour compiler les lignes d'apres 
    */


    //reset le compilateur pour etre pret a une nouvelle compilation
    private void reset() {
        lines = null; 
        lineNumber = 0; 
        currentLine = 0;
    }

}
