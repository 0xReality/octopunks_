package Compilation;

/**
 * L'énumération ErrorCode définit les différents codes d'erreur qui peuvent se produire lors de la compilation.
 */
public enum ErrorCode {
    INVALID_COMMAND(1, "Invalid command."),
    MISSING_ARGUMENTS(2, "Missing arguments."),
    INVALID_ARGUMENTS(3, "Invalid arguments."),
    NUMBER_ERROR(4, "Number Unauthorized."),
    DIVISION_BY_ZERO(5, "Attempted division by zero."),
    INVALID_REGISTER(6, "Invalid register."),
    LABEL_NOTFOUND(7, "no label found");


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
