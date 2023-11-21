package ba.edu.ibu.demo.core.exceptions.general;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
