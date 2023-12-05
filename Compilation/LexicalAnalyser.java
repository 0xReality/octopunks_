package Compilation;

import java.util.*;

import Fonctions.*;


public class LexicalAnalyser {
    private final ArrayList<Command.Instruction> validCommand;
    private final ArrayList<Register> registers;

    public LexicalAnalyser(ArrayList<Register> registers) {
        validCommand = new ArrayList<Command.Instruction>();
        Collections.addAll(validCommand, Command.Instruction.class.getEnumConstants());
        this.registers = registers;
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

    
    public boolean checkErrors(Command cmd) {
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


    /*
     * Completer les switch cases sur toutes les autres instructions
     */
    public void callInstruction(Command c) {
        Object[] args = new Object[c.getExpectedArgs()];
        for (int i = 0; i < c.getExpectedArgs(); i++) {
            args[i] = processArgument(c.getArgs()[i]);
        }
        Register r  = stringToRegister(c.getArgs()[c.getExpectedArgs()-1]);
        switch (c.getInstruction()) {
            case ADDI:
                new ADDI((int)args[0],(int)args[1] ,r);
                break;
            default:
                break;
        }
        return;
    }

    private Object processArgument(String arg){
        if(arg == null) return null;
        if(isNumber(arg)){
            return Integer.parseInt(arg);
        }else{
            Register r = stringToRegister(arg);
            return r.getValeur();
        }
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

    public boolean isNumber(String arg){
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Register stringToRegister(String nom){
        for (Register register : registers) {
            if(register.getName().equals(nom)) return register;
        }
        return null;
    }

}