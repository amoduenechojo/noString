package africa.semicolon.noStrings.exceptions.customException;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
