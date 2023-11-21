package Compilation;

public class Command {
    public enum Instruction{
        ADDI, LINK, INVALID
    }

    public enum Type{
        ARITHMETIC, MOVEMENT, UKNOWN
    }

    private Instruction instruction;
    private Type type;
    private String[] args;
    private int expectedArgs;


    public Command(Instruction instruction, String[] args){
        this.instruction = instruction;
        this.type = Type.UKNOWN;
        this.args = args;
    }

    public Command(Instruction instruction,Type type, String[] args){
        this.instruction = instruction;
        this.type = type;
        this.args = args;
        this.expectedArgs = expectedArgs();
    }

    public int expectedArgs(){
        switch (this.type) {
            case ARITHMETIC: return 3;
            case MOVEMENT:
            default: return 1;
        }
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

    public Type getType() {
        return type;
    }



}
