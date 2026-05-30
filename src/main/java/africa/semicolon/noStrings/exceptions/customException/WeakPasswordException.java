package africa.semicolon.noStrings.exceptions.customException;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException(String message) {
        super(message);
    }
}
