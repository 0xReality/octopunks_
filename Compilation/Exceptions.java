package Compilation;

import UI.Terminal;

/**
 * La classe Exceptions sert à gérer les erreurs et les exceptions pendant la compilation.
 */
public class Exceptions {

    private Terminal terminal;

    public Exceptions(Terminal terminal) {
        this.terminal = terminal;
     }

    /**
     * Envoie un message d'erreur basé sur le code d'erreur et la commande concernée.
     * @param cmd La commande qui a généré l'erreur.
     * @param signal Le code d'erreur associé à l'exception.
     */
    public void sendError(Command cmd, int signal) {
        ErrorCode errorCode = findErrorCode(signal);
        String errorMessage;
        String correctSyntax;

        // Formate et affiche le message d'erreur
        if (errorCode != null) {
            errorMessage = String.format("error: Line %d: %s [%s]", cmd.getLine(), errorCode.getMessage(), errorCode.getCode());
            correctSyntax = cmd.getCorrectSyntax();
        } else {
            // Message d'erreur pour un signal inconnu
            errorMessage = "<" + cmd.getInstruction() + "> - Unknown error signal: " + signal;
            correctSyntax = " ";
        }
        terminal.print(errorMessage, "red");
        terminal.print(correctSyntax, "green");
    }

    /**
     * Trouve l'ErrorCode correspondant à un signal donné.
     * @param signal Le code d'erreur à rechercher.
     * @return L'ErrorCode correspondant, ou null si aucun n'est trouvé.
     */
    private ErrorCode findErrorCode(int signal) {
        for (ErrorCode code : ErrorCode.values()) {
            if (code.getCode() == signal) {
                return code;
            }
        }
        return null;
    }
}
