package Compilation;

import java.util.ArrayList;

public class CommandHandler {
    private ArrayList<Register> registers;
    private Exceptions exp;

    public CommandHandler(ArrayList<Register> registers, Exceptions exp) {
        this.registers = registers;
        this.exp = exp;
    }

    /*
     * a modifier car le joueur doit impérativement entrer un int int registre 
     * ou registre registre registre pour les fonctions arithmetiques
     */
    public boolean handleCommand(Command cmd) {
        switch (cmd.getInstruction()) {
            case ADDI:
            case MULI:
            case DIVI:
            case SUBI:
                return handleArithmeticCommands(cmd);
            case LINK:
            case JUMP:
            case FJMP:
                return handleJumpCommands(cmd);
            case COPY:
                return handleCopyCommand(cmd);
            default:
                exp.sendError(cmd, 1);  
                return false;
        }
    }

    private boolean handleArithmeticCommands(Command cmd) {
        try {
            int arg0 = Integer.parseInt(cmd.getArgs()[0]);
            int arg1 = Integer.parseInt(cmd.getArgs()[1]);
    
            if (cmd.getInstruction().equals(Command.Instruction.DIVI) && arg1 == 0) {
                exp.sendError(cmd, 5); 
                return false;
            }
    
            if (numberVerification(arg0) || numberVerification(arg1)) {
                exp.sendError(cmd, 4); 
                return false;
            }
    
            if (!isRegister(cmd.getArgs()[2])) {
                exp.sendError(cmd, 6); 
                return false;
            }
        } catch (NumberFormatException e) {
            if (!isAllRegistersValid(cmd)) {
                exp.sendError(cmd, 6);
                return false;
            }
        }
        return true;
    }
    
    private boolean handleJumpCommands(Command cmd) {
        try {
            int arg0 = Integer.parseInt(cmd.getArgs()[0]);
    
            if (numberVerification(arg0)) {
                exp.sendError(cmd, 4);
                return false;
            }
    
            if (!isRegister(cmd.getArgs()[1])) {
                exp.sendError(cmd, 6);
                return false;
            }
        } catch (NumberFormatException e) {
            if (!isAllRegistersValid(cmd)) {
                exp.sendError(cmd, 6);
                return false;
            }
        }
        return true;
    }
    

    private boolean handleCopyCommand(Command cmd) {
        if (!isAllRegistersValid(cmd)) {
            exp.sendError(cmd, 6); 
            return false;
        }
        return true;
    }
    


    private boolean isRegister(String nom){
        for (Register register : this.registers) {
            if(register.getName().equals(nom)) return true;
        }
        return false;
    }


    private boolean numberVerification(int n){
        return (n > 9999 || n < -9999);
    }

    private boolean isAllRegistersValid(Command cmd){
        for (int i = 0; i < cmd.getArgs().length; i++) {
            if(!(cmd.getArgs()[i].equals(registers.get(i).getName()))) return false;
        }
        return true;
    }

}
