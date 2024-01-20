package Compilation;

/**
 * La classe Exceptions sert à gérer les erreurs et les exceptions pendant la compilation.
 */
public class Exceptions {


    public Exceptions() { }

    /**
     * Envoie un message d'erreur basé sur le code d'erreur et la commande concernée.
     * @param cmd La commande qui a généré l'erreur.
     * @param signal Le code d'erreur associé à l'exception.
     */
    public void sendError(Command cmd, int signal) {
        ErrorCode errorCode = findErrorCode(signal);
        String errorMessage;

        // Formate et affiche le message d'erreur
        if (errorCode != null) {
            errorMessage = String.format("Line %d: %s [%s]", cmd.getLine(), errorCode.getMessage(), errorCode.getCode());
        } else {
            // Message d'erreur pour un signal inconnu
            errorMessage = "<" + cmd.getInstruction() + "> - Unknown error signal: " + signal;
        }
        System.err.println(errorMessage);
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
