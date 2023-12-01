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
        int arg0;
        int arg1;
        /*
         * Verification que le registre pour enregistrer les valuers est bien un registre
         */
        if (!isRegister(cmd.getArgs()[2])) {
            exp.sendError(cmd, 6); 
            return false;
        }

        try {
            arg0 = Integer.parseInt(cmd.getArgs()[0]); 
            try {
                arg1 = Integer.parseInt(cmd.getArgs()[1]);
                // Verification pour la division
                if (cmd.getInstruction().equals(Command.Instruction.DIVI) && arg1 == 0) {
                    exp.sendError(cmd, 5); 
                    return false;
                }

                // Verification que les 2 chiffres sont entre -9999 et 9999
                if (numberVerification(arg0) || numberVerification(arg1)) {
                    exp.sendError(cmd, 4); 
                    return false;
                }
            //si on arrive a ce catch cela signifie que arg1 est un chiffre et arg2 un registre
            } catch (NumberFormatException e) {
                if (!isRegister(cmd.getArgs()[1])) {
                    exp.sendError(cmd, 6); 
                    return false;
                }
                Register regArg1 = stringToRegister(cmd.getArgs()[1], registers);
                // Verification que les 2 chiffres sont entre -9999 et 9999
                if (numberVerification(arg0) || numberVerification(regArg1.getValeur())) {
                    exp.sendError(cmd, 4); 
                    return false;
                }
            }
            /*
             * si on arrive a ce catch cela signifie que arg0 est un registre
             * on doit verifier si arg1 est un registre ou pas
             */
        } catch (NumberFormatException e) {
            if (!isRegister(cmd.getArgs()[0])) {
                exp.sendError(cmd, 6); 
                return false;
            }
            Register regArg0 = stringToRegister(cmd.getArgs()[0], registers);
            try {
                arg1 = Integer.parseInt(cmd.getArgs()[1]);
                // Verificattion pour la division
                if (cmd.getInstruction().equals(Command.Instruction.DIVI) && arg1 == 0) {
                    exp.sendError(cmd, 5); 
                    return false;
                }

                // Verification que les 2 chiffres sont entre -9999 et 9999
                if (numberVerification(regArg0.getValeur()) || numberVerification(arg1)) {
                    exp.sendError(cmd, 4); 
                    return false;
                }
            //si on arrive a ce catch cela signifie que arg1 est un registre et arg2 un registre
            } catch (NumberFormatException n) {
                if (!isAllRegistersValid(cmd)) {
                    exp.sendError(cmd, 6); 
                    return false;
                }
                Register regArg1 = stringToRegister(cmd.getArgs()[1], registers);
                // Verification que les 2 chiffres sont entre -9999 et 9999
                if (numberVerification(regArg0.getValeur()) || numberVerification(regArg1.getValeur())) {
                    exp.sendError(cmd, 4); 
                    return false;
                }
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

    public Register stringToRegister(String nom, ArrayList<Register> registers){
        for (Register register : registers) {
            if(register.getName().equals(nom)) return register;
        }
        return null;
    }

}
