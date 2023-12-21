package ba.edu.ibu.demo.core.service;

import ba.edu.ibu.demo.core.exceptions.general.BadRequestException;
import ba.edu.ibu.demo.core.exceptions.general.NotFoundException;
import ba.edu.ibu.demo.core.model.Author;
import ba.edu.ibu.demo.core.model.AuthorGroup;
import ba.edu.ibu.demo.core.repository.AuthorRepository;
import ba.edu.ibu.demo.rest.dto.AuthorRequestDTO;
import org.springframework.stereotype.Service;
import ba.edu.ibu.demo.rest.dto.AuthorDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public List<AuthorDTO> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(AuthorDTO::new)
                .collect(Collectors.toList());
    }

    public AuthorDTO findById(String id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return new AuthorDTO(author);
    }

    public AuthorDTO save(AuthorRequestDTO requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getNationality() == null) {
            throw new BadRequestException("Name and Nationality should not be null");
        }
        Author author = requestDTO.toEntity();
        Author savedAuthor = authorRepository.save(author);
        return new AuthorDTO(savedAuthor);
    }

    public void delete(String id) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        authorRepository.deleteById(id);
    }

    public AuthorDTO update(String id, AuthorRequestDTO requestDTO) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Author not found");
        }
        Author author=requestDTO.toEntity();
        author.setId(id);
        Author updatedAuthor = authorRepository.save(author);
        return new AuthorDTO(updatedAuthor);
    }

    public List<AuthorDTO> findAuthorsByName(String name) {
        List<Author> authors = authorRepository.findByName(name);
        return authors.stream().map(AuthorDTO::new).collect(Collectors.toList());
    }

    public List<AuthorDTO> findAuthorsByNationality(String nationality) {
        List<Author> authors = authorRepository.findByNationality(nationality);
        return authors.stream().map(AuthorDTO::new).collect(Collectors.toList());
    }
    public List<AuthorGroup> findAuthorsByCustomCriteria(String namePattern, String nationality) {
        return authorRepository.findAuthorsByCustomCriteria(namePattern, nationality);
    }
}
