package ba.edu.ibu.demo.core.service;
import ba.edu.ibu.demo.core.model.Author;
import ba.edu.ibu.demo.core.repository.AuthorRepository;
import ba.edu.ibu.demo.rest.dto.AuthorDTO;
import ba.edu.ibu.demo.rest.dto.AuthorRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class AuthorServiceTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Test
    public void shouldReturnAuthorWhenCreated() {
        Author author = new Author();
        author.setName("elnur");
        author.setNationality("azer");

        Mockito.when(authorRepository.save(ArgumentMatchers.any(Author.class))).thenReturn(author);

        AuthorRequestDTO requestDTO = new AuthorRequestDTO();
        requestDTO.setName("elnur");
        requestDTO.setNationality("azer");

        AuthorDTO savedAuthor = authorService.save(requestDTO);
        Assertions.assertThat(savedAuthor.getName()).isEqualTo(author.getName());
    }

    @Test
    public void shouldReturnAuthorById() {
        Author author = new Author();
        author.setId("id");
        author.setName("elnur");
        author.setNationality("azer");

        Mockito.when(authorRepository.findById("id")).thenReturn(Optional.of(author));

        AuthorDTO foundAuthor = authorService.findById("id");
        Assertions.assertThat(foundAuthor.getName()).isEqualTo("elnur");
    }

}
