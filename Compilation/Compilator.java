package Compilation;

public class Compilator {
    LexicalAnalyser l = new LexicalAnalyser();
    private Command[] lines;
    private int lineNumber;
    private int currentLine;

    //compiles toutes les lignes doit etre reset a la fin
    public Compilator(String s, int mode) {
        String[] text = s.split("\n");
        lines = new Command[text.length];
        this.lineNumber = text.length;
        for (int i = 0; i < text.length; i++) {
            lines[i] = l.argsToCommand(text[i].split(" "));
        }
        /*
        * appel a une nouvelle classe arugmentChecker qui doit verifier 
        * si les arguments sont correctes
        */
        
        /*
         * Appel a la methode sendSignal(Command cmd) qui appel l'instruction
         * cmd.getInstruction() a sa propre methode
        */

        /*
         * Appel a une nouvelle methode qui reset les variables de Compilator
         */
    }

    /*
    * Constructio, compile ligne par ligne doit attendre un 
    * second appel de compileNextLine()
    * pour compiler les lignes d'apres 
    */



}
