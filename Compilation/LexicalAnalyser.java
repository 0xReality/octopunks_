package Compilation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Compilation.Command.Instruction;
import Fonctions.*;
import Robot.EXA;
import UI.gameplay.ExaInfo;
import UI.gameplay.InitialisedGame;
import UI.gameplay.Terminal;



/**
 * La classe LexicalAnalyser effectue l'analyse lexicale des instructions et les transforme en commandes puis apelle l'interpretteur CommandHandler.
 */
        
public class LexicalAnalyser {
    private final ArrayList<Command.Instruction> validCommand;
    private final ArrayList<Register> registers;
    private InitialisedGame game; 
    private EXA exa;  

    /**
     * Constructeur de LexicalAnalyser.
     * @param registers La liste des registres utilisés dans les commandes.
     */
    public LexicalAnalyser(ArrayList<Register> registers, InitialisedGame ga, EXA ex) {
        validCommand = new ArrayList<Command.Instruction>();
        Collections.addAll(validCommand, Command.Instruction.class.getEnumConstants());
        this.registers = registers;
        this.game = ga; 
        this.exa = ex;

    }

    /**
     * Convertit le code du joueur en objet Command
     * @param s Le tableau de chaînes de caractères représentant les arguments de la commande.
     * @param line Le numéro de la ligne où la commande se trouve.
     * @return Un objet Command représentant la commande analysée.
     */
    public Command argsToCommand(String[] s, int line) {
        if(s[0].isBlank()) return null;
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
            case "SWIZ":
                return new Command(Command.Instruction.SWIZ, Arrays.copyOfRange(s, 1, s.length), line);
            case "MODI":
                return new Command(Command.Instruction.MODI, Arrays.copyOfRange(s, 1, s.length), line);
            case "JUMP":
                return new Command(Command.Instruction.JUMP, Arrays.copyOfRange(s, 1, s.length), line);
            case "FJMP":
                return new Command(Command.Instruction.FJMP, Arrays.copyOfRange(s, 1, s.length), line);
            case "Kill":
                return new Command(Command.Instruction.KILL, Arrays.copyOfRange(s, 1, s.length), line);
            case "HALT":
                return new Command(Command.Instruction.HALT, Arrays.copyOfRange(s, 1, s.length), line);            
            case "GRAB":
                return new Command(Command.Instruction.GRAB, Arrays.copyOfRange(s, 1, s.length), line);
            case "DROP":
                return new Command(Command.Instruction.DROP, Arrays.copyOfRange(s, 1, s.length), line);
            case "RAND":
                return new Command(Command.Instruction.RAND, Arrays.copyOfRange(s, 1, s.length), line);
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
        if(isNote(cmd)) return true;
        if(cmd.getInstruction().toString().equals("DROP")){
            return new CommandHandler(registers, exp,game,exa).handleCommand(cmd);
        }
        if(isMissingCommand(cmd)){
            exp.sendError(cmd, 2);
            return false;
        }
        if(isInvalidCommand(cmd)){
            exp.sendError(cmd, 3);
            return false;
        } 
        CommandHandler handler = new CommandHandler(registers, exp,game,exa);
        
        return handler.handleCommand(cmd);
    }


    /**
     * Appelle l'instruction spécifique en fonction de la commande.
     * @param c La commande dont l'instruction doit être exécutée.
     */
    public void callInstruction(Command c, Compilator k, ExaInfo exaInfo) {
        if(c.getArgs() != null){
            Register[] args = new Register[c.getExpectedArgs()];
            for (int i = 0; i < c.getExpectedArgs(); i++) {
                args[i] = processArgument(c.getArgs()[i]);
            }
            

            // si la commande attend au moins un argument, on obtient le dernier registre
            Register r  = null;  
            if(c.getExpectedArgs()>0){
            r = stringToRegister(c.getArgs()[c.getExpectedArgs()-1]);
            }

            int cycles = exaInfo.getCycles() + 1;;
            int activity = exaInfo.getActivity();
            boolean isActivityInstruction = false;
            switch (c.getInstruction()) {
                case ADDI:
                    new ADDI(args[0].getValeur(),args[1].getValeur() ,r);
                    break;
                case SUBI:
                    new SUBI(args[0].getValeur(),args[1].getValeur() ,r);
                    break;
                case MULI:
                    new MULI(args[0].getValeur(),args[1].getValeur() ,r);
                    break;
                case MODI:
                    new MODI(args[0].getValeur(),args[1].getValeur() ,r);
                    break;
                case DIVI:
                    new DIVI(args[0].getValeur(),args[1].getValeur() ,r);
                    break;
                case SWIZ:
                    new SWIZ(args[0].getValeur(),args[1].getValeur() ,r);
                    break;
                case RAND: 
                    new RAND(args[0].getValeur(),args[1].getValeur() ,r);
                    break;
                case JUMP:
                    new JUMP(args[0].getValeur(), k);
                    break;
                case FJMP:
                    new FJMP(args[0].getValeur(), k);
                    break;
                case LINK:
                    isActivityInstruction = true;
                    String label = c.getArgs()[0]; 
                    Integer newPosition = game.getPosForLabel(label);
                    if(newPosition != null){
                        new LINK(game).Link(exa, newPosition);
                        return; 
                    }
                    break;
                case KILL:
                    isActivityInstruction = true;
                    new HALT(exa);
                    break;
                case COPY:
                    new COPY(args[0], args[1]);
                    break;
                case HALT:
                    new HALT(exa); 
                    break; 
                case GRAB:
                    new GRAB(args[0].getValeur(), exa, k.getRegisters()); 
                    break;
                case DROP:
                    new DROP(exa, k.getRegisters());
                    break;
                default:
                    break;
            }

            if (isActivityInstruction) activity++;
            exaInfo.updateValues(null, cycles, activity);
        }else{
            System.out.println("y a un problémes avec les arguments de tes fonctions dans lexical analyseur chef");
        }
        return;
    }

    private Register processArgument(String arg){
        if(arg == null) return null;
        if(isNumber(arg)){
            int val =  Integer.parseInt(arg);
            return new Register(val, null);
        }else{
            Register r = stringToRegister(arg);
            return r;
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

    public boolean hasUsedFileRegister(Command c){
        return c.getArgs()[c.getExpectedArgs()-1].equals("F");
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
    

    public boolean checkRegisterF(Command cmd){
        if(cmd.getExpectedArgs() < 1) return false;
        if(cmd.getArgs()[cmd.getExpectedArgs() - 1].equals("F")){
            return true;
        }
        return false;
    }

    public boolean isNote(Command cmd){
        return cmd.getInstruction() == Instruction.NOTE;
    }

}