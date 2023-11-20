import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class LexicalAnalyser {
    private final ArrayList<Command.Instruction> validCommand;

    public LexicalAnalyser() {
        validCommand = new ArrayList<Command.Instruction>();
        Collections.addAll(validCommand, Command.Instruction.class.getEnumConstants());
    }

    public Command argsToCommand(String[] s) {
        Command cmd;
        switch (s[0].toUpperCase()) {
            case "ADDI":
                return cmd = new Command(Command.Instruction.ADDI, Command.Type.ARITHMETIC, Arrays.copyOfRange(s, 1, s.length));
            case "LINK":
                return cmd = new Command(Command.Instruction.LINK, Command.Type.MOVEMENT, Arrays.copyOfRange(s, 1, s.length));
            default:
                return cmd = new Command(Command.Instruction.INVALID, Arrays.copyOfRange(s, 1, s.length));
        }
    }

    public boolean isCommand(Command c) {
        return validCommand.contains(c.getInstruction()) && !c.getInstruction().equals(Command.Instruction.INVALID);
    }

    public boolean isValidCommand(Command c) {
        if (validCommand.contains(c.getInstruction()) && !c.getInstruction().equals(Command.Instruction.INVALID)) {
            return c.expectedArgs() == c.getArgs().length;
        }
        return false;
    }

    public boolean callInstruction(Command c) {
        if (!isValidCommand(c)) return false;
        switch (c.getInstruction()) {
            case ADDI: {/*APPEL A LA FN ADDI;*/
                break;
            }
            default: break;
        }
        return true;
    }
}