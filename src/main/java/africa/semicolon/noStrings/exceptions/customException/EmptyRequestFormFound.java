package africa.semicolon.noStrings.exceptions.customException;

public class EmptyRequestFormFound extends RuntimeException {
    public EmptyRequestFormFound(String message) {
        super(message);
    }
}
