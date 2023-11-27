package Compilation;

/*
 * Signals Documentation
 * 
 * 
 * 
 */
public class Exceptions {

    public Exceptions() { }
 
    public void sendError(Command cmd, int signal) {
        ErrorCode errorCode = findErrorCode(signal);
        String errorMessage;

        if (errorCode != null) {
            errorMessage = String.format("Line %d: %s [%s]", cmd.getLine(), errorCode.getMessage(), errorCode.getCode());

        } else {
            errorMessage = "<" + cmd.getInstruction() + "> - Unknown error signal: " + signal;
        }
        System.err.println(errorMessage);
    }

    private ErrorCode findErrorCode(int signal) {
        for (ErrorCode code : ErrorCode.values()) {
            if (code.getCode() == signal) {
                return code;
            }
        }
        return null;
    }
}
