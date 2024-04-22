package ee.tbot.apartmentbot.exception;

public class ServiceException extends Exception {
    ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
