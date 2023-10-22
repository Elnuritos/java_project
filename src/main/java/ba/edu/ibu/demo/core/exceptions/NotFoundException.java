package ba.edu.ibu.demo.core.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String id) {
        super("No author found with ID: " + id);
    }
}
