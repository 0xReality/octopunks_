package Compilation;

import java.util.ArrayList;

import Compilation.Command.Instruction;


/**
 * La classe CommandHandler gère les commandes et interprète les instructions pour manipuler les registres.
 */
public class CommandHandler {
    private ArrayList<Register> registers;
    private Exceptions exp;

    /**
     * Constructeur de CommandHandler.
     * @param registers La liste des registres utilisés dans le programme.
     * @param exp Gestionnaire d'exceptions pour traiter les erreurs.
     */
    public CommandHandler(ArrayList<Register> registers, Exceptions exp) {
        this.registers = registers;
        this.exp = exp;
    }

    /**
     * Gère une commande en exécutant l'instruction correspondante.
     * @param cmd La commande à traiter.
     * @return Vrai si la commande est traitée avec succès, faux sinon.
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
                return handleJumpLinkCommands(cmd);
            case COPY:
                return handleCopyCommand(cmd);
            default:
                exp.sendError(cmd, 1);  
                return false;
        }
    }

    /**
     * Gère les commandes arithmétiques.
     * @param cmd La commande arithmétique à traiter.
     * @return Vrai si la commande est traitée avec succès, faux sinon.
     */
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
                if (!isRegister(cmd.getArgs()[0])) {
                    exp.sendError(cmd, 6); 
                    return false;
                }
                if (!isRegister(cmd.getArgs()[1])) {
                    exp.sendError(cmd, 6); 
                    return false;
                }
                if (!isRegister(cmd.getArgs()[2])) {
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
    
    /**
     * Gère les commandes de saut et de mouvement.
     * @param cmd La commande de saut à traiter.
     * @return Vrai si la commande est traitée avec succès, faux sinon.
     */
    private boolean handleJumpLinkCommands(Command cmd) {
        try {
            int arg0 = Integer.parseInt(cmd.getArgs()[0]);
    
            if (numberVerification(arg0)) {
                exp.sendError(cmd, 4);
                return false;
            }
            if(cmd.getInstruction() == Instruction.JUMP ||
            cmd.getInstruction() == Instruction.FJMP){
                //On doit verifier que le label est contenue dans le codeArea
            }
    
        } catch (NumberFormatException e) {
            exp.sendError(cmd, 7);
            return false;
        }
        return true;
    }
    
    /**
     * Gère la commande de copie.
     * @param cmd La commande COPY à traiter.
     * @return Vrai si la commande est traitée avec succès, faux sinon.
     */
    private boolean handleCopyCommand(Command cmd) {
        try {
            int arg0 = Integer.parseInt(cmd.getArgs()[0]);
            if (numberVerification(arg0)) {
                exp.sendError(cmd, 4);
                return false;
            }
            if(!isRegister(cmd.getArgs()[1])){
                exp.sendError(cmd, 6); 
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            if(!isRegister(cmd.getArgs()[0])){
                exp.sendError(cmd, 6); 
                return false;
            }
            if(!isRegister(cmd.getArgs()[1])){
                exp.sendError(cmd, 6); 
                return false;
            }
            return true;
        }
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

    //petit soucis a fix incconu
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
