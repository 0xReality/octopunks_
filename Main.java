import java.util.ArrayList;
import java.util.Scanner;

import Compilation.Command;
import Compilation.LexicalAnalyser;
import Compilation.Register;
import UI.OctoPunks;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getOutput() {
        System.out.print("> ");
        return scanner.nextLine();
    }


    //la version terminal ne possede aucun registre 
    //TODO ajouter les 4 registres au arraylist "r"

    public static void main(String[] args) {
        if(args.length > 0 && "1".equals(args[0])){ //FAITES java Main 1 si vous voulez utiliser les commandes terminales sinon  java Main
            ArrayList<Register> r= new ArrayList<Register>();
            LexicalAnalyser l = new LexicalAnalyser(r);
            while(true){        
                String out = getOutput();
                String[] StringCmd = out.split(" ");
                Command cmd = l.argsToCommand(StringCmd, 0);

                if (StringCmd[0].toUpperCase().equals("EXIT")) break;
                
                if(!l.isCommand(cmd)){
                    System.out.println("Commande " + "<" + StringCmd[0] + ">" + " Inconnue");
                    continue;
                }

                System.out.println("Commande Valide");
                if(!l.isInvalidCommand(cmd)){
                    System.out.println("ERREUR ARGUMENTS : Commande " + "<" + StringCmd[0] + ">" + " Doit recevoir " + cmd.getExpectedArgs() + " Arguments ");
                    System.out.println("Utilisation : " + StringCmd[0] + " a(R/N)" + " b(R/N)" + " dest(R)" );
                    System.out.println("INFORMATION: \nAjoute a + b et sauvegarde le résultat dans dest. a, b et dest peuvent être le même registre local. Le résultat sera limité entre -9999 et 9999.");
                    continue;
                }
                // Ajout des Tests de registre et des variables
                System.out.println("Arguments Correcte");
            }
        }
        OctoPunks window = new OctoPunks();

    }
}