package march19.exceptions.unchecked;

public class BusinessValidationException extends RuntimeException{

    public BusinessValidationException(String message) {
        super(message);
    }
}
