package ba.edu.ibu.demo.core.exceptions.general;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String id) {
        super("No author found with ID: " + id);
    }
}
