package Compilation;

public enum ErrorCode {
    INVALID_COMMAND(1, "Invalid command."),
    MISSING_ARGUMENTS(2, "Missing arguments."),
    INVALID_ARGUMENTS(3, "Invalid arguments."),
    NUMBER_ERROR(4, "Number Unauthorized."),
    DIVISION_BY_ZERO(5, "Attempted division by zero.");


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
