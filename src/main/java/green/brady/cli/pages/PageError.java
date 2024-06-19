package green.brady.cli.pages;

public class PageError extends RuntimeException {

    public PageError(String message) {
        super(message);
    }

    public PageError(String message, Throwable cause) {
        super(message, cause);
    }
}
