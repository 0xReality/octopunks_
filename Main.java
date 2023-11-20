import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getOutput() {
        System.out.print("> ");
        return scanner.nextLine(); // Do not close the scanner here
    }



    public static void main(String[] args) {
        LexicalAnalyser l = new LexicalAnalyser();
        while(true){        
            String out = getOutput();
            String[] StringCmd = out.split(" ");
            Command cmd = l.argsToCommand(StringCmd);

            if(!l.isCommand(cmd)){
                System.out.println("Commande " + "<" + StringCmd[0] + ">" + " Inconnue");
                continue;
            }
            System.out.println("Commande Valide");
            if(!l.isValidCommand(cmd)){
                System.out.println("ERREUR ARGUMENTS : Commande " + "<" + StringCmd[0] + ">" + " De Type " + cmd.getType() + " Doit recevoir " + cmd.expectedArgs() + " Arguments ");
                System.out.println("Utilisation : " + StringCmd[0] + " a(R/N)" + " b(R/N)" + " dest(R)" );
                System.out.println("INFORMATION: \nAjoute a + b et sauvegarde le résultat dans dest. a, b et dest peuvent être le même registre local. Le résultat sera limité entre -9999 et 9999.");
                continue;
            }
            // Ajout des Tests de registre et des variables
            System.out.println("Arguments Correcte");
        }   

    }
}