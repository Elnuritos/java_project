package ba.edu.ibu.demo.core.model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthorTest {
    @Test
    void shouldCreateNewAuthor() {
        Author author = new Author();
        author.setId("id");
        author.setName("el");
        author.setNationality("az");

        Assertions.assertEquals("id", author.getId());
        Assertions.assertEquals("el", author.getName());
        Assertions.assertEquals("az", author.getNationality());
    }

    @Test
    void shouldUpdateAuthorName() {
        Author author = new Author();
        author.setName("elo");
        Assertions.assertEquals("elo", author.getName());
    }
}
