package africa.semicolon.noStrings.exceptions.customException;

public class DuplicatePhoneNumberFound extends RuntimeException {
    public DuplicatePhoneNumberFound(String message) {
        super(message);
    }
}
