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
    TRIED_TO_STORE_F(7, "tried to store date in register F."),
    LABEL_NOTFOUND(8, "no label found."),
    INVALID_OPE(9, "Impossible Comparaison"),
    LOW_HIGH(10, "Lo value is higher than Hi"),
    INVALID_DEST(11, "invalid dest");


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
