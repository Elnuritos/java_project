package ba.edu.ibu.demo.core.repository;

import ba.edu.ibu.demo.core.model.Author;
import ba.edu.ibu.demo.core.model.AuthorGroup;
import ba.edu.ibu.demo.core.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class AuthorRepositoryTest {
    @MockBean
    AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void shouldFindAuthorsByName() {
        List<Author> authors = authorRepository.findByName("elnur");
        Assertions.assertNotNull(authors);
        Assertions.assertFalse(authors.isEmpty());
        Assertions.assertEquals("elnur", authors.get(0).getName());
    }

    @Test
    public void shouldFindAuthorsByNationality() {
        List<Author> authors = authorRepository.findByNationality("azer");
        Assertions.assertNotNull(authors);
        Assertions.assertFalse(authors.isEmpty());
        Assertions.assertEquals("azer", authors.get(0).getNationality());
    }

}
