package core.premier.league.exception;

public class SummaryNotReadyException extends Exception {
    public SummaryNotReadyException(String message, Exception exception) {
        super(message, exception);
    }

    public SummaryNotReadyException(String message) {
        super(message);
    }
}
