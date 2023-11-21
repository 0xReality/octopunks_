package Compilation;

public class Compilator {
    LexicalAnalyser l = new LexicalAnalyser();
    private Command[] lines;
    private int lineNumber;
    private int currentLine;

    public Compilator(String s, int mode) {
        String[] text = s.split("\n");
        lines = new Command[text.length];
        this.lineNumber = text.length;
        for (int i = 0; i < text.length; i++) {
            lines[i] = l.argsToCommand(text[i].split(" "));
        }
    }

}
