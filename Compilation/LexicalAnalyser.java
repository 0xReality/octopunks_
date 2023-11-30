package Compilation;

import java.util.*;

import Fonctions.*;


public class LexicalAnalyser {
    private final ArrayList<Command.Instruction> validCommand;

    public LexicalAnalyser() {
        validCommand = new ArrayList<Command.Instruction>();
        Collections.addAll(validCommand, Command.Instruction.class.getEnumConstants());
    }

    public Command argsToCommand(String[] s, int line) {
        Command cmd;
        switch (s[0].toUpperCase()) {
            case "ADDI":
                return cmd = new Command(Command.Instruction.ADDI, Arrays.copyOfRange(s, 1, s.length), line);
            case "LINK":
                return cmd = new Command(Command.Instruction.LINK, Arrays.copyOfRange(s, 1, s.length), line);
            case "COPY":
                return cmd = new Command(Command.Instruction.COPY, Arrays.copyOfRange(s, 1, s.length), line);
            case "MULI":
                return cmd = new Command(Command.Instruction.MULI, Arrays.copyOfRange(s, 1, s.length), line);
            case "SUBI":
                return cmd = new Command(Command.Instruction.SUBI, Arrays.copyOfRange(s, 1, s.length), line);
            case "DIVI":
                return cmd = new Command(Command.Instruction.DIVI, Arrays.copyOfRange(s, 1, s.length), line);
            case "JUMP":
                return cmd = new Command(Command.Instruction.JUMP, Arrays.copyOfRange(s, 1, s.length), line);
            case "FJMP":
                return cmd = new Command(Command.Instruction.FJMP, Arrays.copyOfRange(s, 1, s.length), line);
            default:
                return cmd = new Command(Command.Instruction.INVALID, Arrays.copyOfRange(s, 1, s.length), line);
        }
    }

    
    public boolean checkErrors(Command cmd, ArrayList<Register> registers) {
        Exceptions exp = new Exceptions();
        if (!isCommand(cmd)) {
            exp.sendError(cmd, 1);
            return false;
        }
        if(isMissingCommand(cmd)){
            exp.sendError(cmd, 2);
            return false;
        }
        if(isInvalidCommand(cmd)){
            exp.sendError(cmd, 3);
            return false;
        } 
        CommandHandler handler = new CommandHandler(registers, exp);
        
        return handler.handleCommand(cmd);
    }



    //COMPLETEMENT A REFAIRE
    public void callInstruction(Command c, Register registre) {
        switch (c.getInstruction()) {
            case ADDI: {/*APPEL A LA FN ADDI;*/
                int x = Integer.parseInt(c.getArgs()[0]);            
                int y = Integer.parseInt(c.getArgs()[1]);

                ADDI addi = new ADDI(x, y, registre);
                break;
            }
            default: break;
        }
        return;
    }

    public boolean isCommand(Command c) {
        return validCommand.contains(c.getInstruction()) && !c.getInstruction().equals(Command.Instruction.INVALID);
    }

    public boolean isMissingCommand(Command c) {
        return c.getExpectedArgs() > c.getArgs().length;
    }

    public boolean isInvalidCommand(Command c) {
        return c.getExpectedArgs() < c.getArgs().length;
    }



    public Register stringToRegister(String nom, ArrayList<Register> registers){
        for (Register register : registers) {
            if(register.getName().equals(nom)) return register;
        }
        return null;
    }

}