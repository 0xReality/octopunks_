package Compilation;

/**
 * La classe Command représente une commande dans le processus de Compilation.
 * Elle contient une instruction, ses arguments et le numéro de la ligne où elle se trouve.
 */
public class Command {
    /**
     * L'énumération Instruction représente différents types 
     * d'instructions.
     * Chaque instruction est associée à un nombre d'arguments que elle 
     * doit recevoir.
     */
    public enum Instruction {
        ADDI(3),
        LINK(1), 
        INVALID(0), 
        COPY(2), 
        MULI(3),
        SUBI(3), 
        DIVI(3),
        JUMP(1),
        FJMP(1);

        private final int argCount; // Nombre d'arguments pour l'instruction

        /**
         * Constructeur de l'énumération Instruction.
         * @param argCount Le nombre d'arguments requis pour l'instruction.
         */
        Instruction(int argCount) {
            this.argCount = argCount;
        }

        /**
         * @return le nombre d'arguments requis pour l'instruction.
         */
        public int getArgCount() {
            return argCount;
        }
    }

    private final Instruction instruction; 
    private final String[] args; 
    private final int expectedArgs; 
    private final int line;

    /**
     * Constructeur de la classe Command.
     * @param instruction L'instruction de la commande.
     * @param args Les arguments pour l'instruction.
     * @param line Le numéro de la ligne où se trouve la commande.
     */
    public Command(Instruction instruction, String[] args, int line){
        this.instruction = instruction;
        this.args = args;
        this.expectedArgs = instruction.getArgCount();
        this.line = line;          
    }

    // Getters 

    public Instruction getInstruction() {
        return instruction;
    }

    public String[] getArgs() {
        return args;
    }

    public int getExpectedArgs() {
        return expectedArgs;
    }

    public int getLine() {
        return line;
    }

    public String getCorrectSyntax(){
        String correctSyntax;

        switch (instruction) {
            case ADDI:
            case MULI:
            case SUBI:
            case DIVI:
                correctSyntax = String.format("usage: %s a(R/N) b(R/N) dest(R)", instruction.toString());
                break;
            case JUMP:
            case FJMP:
                correctSyntax = String.format("usage: %s dest(R)", instruction.toString());
                break;
            case LINK:
                correctSyntax = String.format("usage: %s dest(R/N)", instruction.toString());
                break;
            case COPY:
                correctSyntax = String.format("usage: %s source(R/N) dest(R)", instruction.toString());  
                break;
            default:
                correctSyntax = "Unkown error";
                break;
        }

        return correctSyntax;
    }

}
