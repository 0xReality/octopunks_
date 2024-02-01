package Compilation;

import java.util.*;

import Fonctions.*;
import UI.gameplay.Terminal;



/**
 * La classe LexicalAnalyser effectue l'analyse lexicale des instructions et les transforme en commandes puis apelle l'interpretteur CommandHandler.
 */
public class LexicalAnalyser {
    private final ArrayList<Command.Instruction> validCommand;
    private final ArrayList<Register> registers;

    /**
     * Constructeur de LexicalAnalyser.
     * @param registers La liste des registres utilisés dans les commandes.
     */
    public LexicalAnalyser(ArrayList<Register> registers) {
        validCommand = new ArrayList<Command.Instruction>();
        Collections.addAll(validCommand, Command.Instruction.class.getEnumConstants());
        this.registers = registers;
    }

    /**
     * Convertit le code du joueur en objet Command
     * @param s Le tableau de chaînes de caractères représentant les arguments de la commande.
     * @param line Le numéro de la ligne où la commande se trouve.
     * @return Un objet Command représentant la commande analysée.
     */
    public Command argsToCommand(String[] s, int line) {
        switch (s[0].toUpperCase()) {
            case "ADDI":
                return new Command(Command.Instruction.ADDI, Arrays.copyOfRange(s, 1, s.length), line);
            case "LINK":
                return new Command(Command.Instruction.LINK, Arrays.copyOfRange(s, 1, s.length), line);
            case "COPY":
                return new Command(Command.Instruction.COPY, Arrays.copyOfRange(s, 1, s.length), line);
            case "MULI":
                return new Command(Command.Instruction.MULI, Arrays.copyOfRange(s, 1, s.length), line);
            case "SUBI":
                return new Command(Command.Instruction.SUBI, Arrays.copyOfRange(s, 1, s.length), line);
            case "DIVI":
                return new Command(Command.Instruction.DIVI, Arrays.copyOfRange(s, 1, s.length), line);
            case "JUMP":
                return new Command(Command.Instruction.JUMP, Arrays.copyOfRange(s, 1, s.length), line);
            case "FJMP":
                return new Command(Command.Instruction.FJMP, Arrays.copyOfRange(s, 1, s.length), line);
            default:
                return new Command(Command.Instruction.INVALID, Arrays.copyOfRange(s, 1, s.length), line);
        }
    }

    /**
     * Vérifie s'il y a des erreurs dans la commande.
     * @param cmd La commande à vérifier.
     * @return Vrai si aucune erreur n'est trouvée, faux sinon.
     */
    public boolean checkErrors(Command cmd, Terminal terminal) {
        Exceptions exp = new Exceptions(terminal);
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


    /**
     * Appelle l'instruction spécifique en fonction de la commande.
     * @param c La commande dont l'instruction doit être exécutée.
     * TODO: Completer les switch cases sur toutes les autres instructions
     */
    public void callInstruction(Command c, Compilator k) {
        Object[] args = new Object[c.getExpectedArgs()];
        for (int i = 0; i < c.getExpectedArgs(); i++) {
            args[i] = processArgument(c.getArgs()[i]);
        }
        Register r  = stringToRegister(c.getArgs()[c.getExpectedArgs()-1]);
        switch (c.getInstruction()) {
            case ADDI:
                new ADDI((int)args[0],(int)args[1] ,r);
                break;
            case SUBI:
                new SUBI((int)args[0],(int)args[1] ,r);
                break;
            case MULI:
                new MULI((int)args[0],(int)args[1] ,r);
                break;
            case MODI:
                new MODI((int)args[0],(int)args[1] ,r);
                break;
            case DIVI:
                new DIVI((int)args[0],(int)args[1] ,r);
                break;
            case JUMP:
                new JUMP((int)args[0], k);
                break;
            case FJMP:
                System.out.println("FJMP not implemented");
                assert(false);
                break;
            case LINK:
                System.out.println("LINK not implemented");
                assert(false);
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