package africa.semicolon.noStrings.exceptions.customException;

public class DuplicateEmailFound extends RuntimeException {
    public DuplicateEmailFound(String message) {
        super(message);
    }
}
