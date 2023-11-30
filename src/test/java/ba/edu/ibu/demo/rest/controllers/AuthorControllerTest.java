package ba.edu.ibu.demo.rest.controllers;

import ba.edu.ibu.demo.core.model.Author;
import ba.edu.ibu.demo.core.model.AuthorGroup;
import ba.edu.ibu.demo.core.service.AuthorService;
import ba.edu.ibu.demo.core.service.JwtService;
import ba.edu.ibu.demo.core.service.UserService;
import ba.edu.ibu.demo.rest.configuration.SecurityConfiguration;
import ba.edu.ibu.demo.rest.dto.AuthorDTO;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@AutoConfigureMockMvc
@WebMvcTest(AuthorController.class)
@Import(SecurityConfiguration.class)
public class AuthorControllerTest {

    @Autowired
     MockMvc mockMvc;

    @MockBean
     AuthorService authorService;

    @MockBean
    JwtService jwtService;

    @MockBean
    UserService userService;

    @MockBean
    AuthenticationProvider authenticationProvider;

    @Test

    void shouldReturnAllAuthors() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("elnur");

        Mockito.when(authorService.findAll()).thenReturn(List.of(authorDTO));

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/authors")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals(1, (Integer) JsonPath.read(response, "$.length()"));
        Assertions.assertEquals("elnur", JsonPath.read(response, "$.[0].name"));
    }
    @Test
    void shouldReturnAuthorById() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId("1");
        authorDTO.setName("elnur");

        Mockito.when(authorService.findById("1")).thenReturn(authorDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("elnur"));
    }
    @Test
    void shouldReturnAuthorsByName() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("elnur");

        Mockito.when(authorService.findAuthorsByName("John")).thenReturn(List.of(authorDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/search/byName?name=John"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("elnur"));
    }
    @Test
    void shouldReturnAuthorsByCustomCriteria() throws Exception {
        Author author = new Author();
        author.setId("1");
        author.setName("elnur");
        author.setNationality("azer");

        AuthorGroup authorGroup = new AuthorGroup() {
            @Override
            public String getId() {
                return "azer";
            }

            @Override
            public List<Author> getAuthors() {
                return List.of(author);
            }
        };

        Mockito.when(authorService.findAuthorsByCustomCriteria("John", "azer")).thenReturn(List.of(authorGroup));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/customSearch?namePattern=John&nationality=azer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("azer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].authors[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].authors[0].name").value("elnur"));
    }
}
