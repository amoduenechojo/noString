package africa.semicolon.noStrings.exceptions.customException;

public class DuplicateUsernameFound extends RuntimeException {
    public DuplicateUsernameFound(String message) {
        super(message);
    }
}
