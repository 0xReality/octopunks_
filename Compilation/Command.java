package Compilation;

public class Command {
    /*
     * Restructuration de l'enum Instruction
     * 
     */

    public enum Instruction {
        ADDI(3), LINK(1), INVALID(0);

        private final int argCount;

        Instruction(int argCount) {
            this.argCount = argCount;
        }

        public int getArgCount() {
            return argCount;
        }
    }

    private final Instruction instruction; //final car les variables ne doivent pas changer
    private final String[] args;
    private final int expectedArgs;
    private final int line;

    public Command(Instruction instruction, String[] args, int line){
        this.instruction = instruction;
        this.args = args;
        this.expectedArgs = instruction.getArgCount();
        this.line = line;
    }

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

}
