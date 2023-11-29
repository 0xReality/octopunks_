package Compilation;

import java.util.*;

import Fonctions.ADDI;


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
            case "JUMP":
                return cmd = new Command(Command.Instruction.JUMP, Arrays.copyOfRange(s, 1, s.length), line);
            case "FJMP":
                return cmd = new Command(Command.Instruction.FJMP, Arrays.copyOfRange(s, 1, s.length), line);
            default:
                return cmd = new Command(Command.Instruction.INVALID, Arrays.copyOfRange(s, 1, s.length), line);
        }
    }

    public boolean CheckErrors(Command cmd, ArrayList<Register> registres){
        Exceptions exp = new Exceptions();
        if(!isCommand(cmd)){
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
        try {
            if (cmd.getInstruction().equals(Command.Instruction.ADDI) || 
                cmd.getInstruction().equals(Command.Instruction.MULI) || 
                cmd.getInstruction().equals(Command.Instruction.SUBI)) {
        
                int arg0 = Integer.parseInt(cmd.getArgs()[0]);
                int arg1 = Integer.parseInt(cmd.getArgs()[1]);
        
                if (numberVerification(arg0) || numberVerification(arg1)) {
                    exp.sendError(cmd, 4);
                    return false;
                }
                if(!(isRegister(cmd.getArgs()[2], registres))){
                    exp.sendError(cmd, 6);
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            if(!(isRegister(cmd.getArgs()[2], registres))|| 
               !(isRegister(cmd.getArgs()[1], registres))||
               !(isRegister(cmd.getArgs()[0], registres))){
                    exp.sendError(cmd, 6);
                    return false;
                }
        }

        //TODO: Ajouter les verifications sur les registres / sur les chiffres
        return true;
    }

    public boolean isCommand(Command c) {
        return validCommand.contains(c.getInstruction()) && !c.getInstruction().equals(Command.Instruction.INVALID);
    }
    public boolean isRegister(String nom, ArrayList<Register> registers){
        for (Register register : registers) {
            if(register.getName().equals(nom)) return true;
        }
        return false;
    }
    public boolean isMissingCommand(Command c) {
        return c.getExpectedArgs() > c.getArgs().length;
    }

    public boolean isInvalidCommand(Command c) {
        return c.getExpectedArgs() < c.getArgs().length;
    }

    public boolean numberVerification(int n){
        return (n > 9999 || n < -9999);
    }

    public boolean callInstruction(Command c, Register registre) {
        switch (c.getInstruction()) {
            case ADDI: {/*APPEL A LA FN ADDI;*/
                int x = Integer.parseInt(c.getArgs()[0]);            
                int y = Integer.parseInt(c.getArgs()[1]);

                ADDI addi = new ADDI(x, y, registre);
                
                System.out.println(registre.getValeur());
                break;
            }
            default: break;
        }
        return true;
    }
}