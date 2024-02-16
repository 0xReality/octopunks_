package Missions; 
import java.util.Scanner;
import java.util.ArrayList;

public class TestUserSolution {
    public static void main(String[] args) {
        // Créer une instance de Solution
        Solution solution = new Solution(1); // Pour la mission 1 par exemple

        // Lire la saisie de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> userInputs = new ArrayList<>();
        System.out.println("Entrez votre solution, ligne par ligne (tapez 'FIN' pour terminer) :");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("FIN")) {
                break;
            }
            userInputs.add(line);
        }
        scanner.close();

        // Convertir en tableau de chaînes de caractères
        String[] userInputArray = userInputs.toArray(new String[0]);

        // Comparer avec le fichier de solution
        boolean isSolutionCorrect = solution.CompareTo(userInputArray);
        System.out.println("La solution est-elle correcte ? " + isSolutionCorrect);
    }
}
