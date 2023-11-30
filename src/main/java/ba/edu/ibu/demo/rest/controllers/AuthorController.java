package ba.edu.ibu.demo.rest.controllers;

import ba.edu.ibu.demo.core.exceptions.general.BadRequestException;
import ba.edu.ibu.demo.core.exceptions.general.NotFoundException;
import ba.edu.ibu.demo.core.model.AuthorGroup;
import ba.edu.ibu.demo.core.service.AuthorService;
import ba.edu.ibu.demo.rest.dto.AuthorDTO;
import ba.edu.ibu.demo.rest.dto.AuthorRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(authorService.findById(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorRequestDTO requestDTO) {
        try {
            return new ResponseEntity<>(authorService.save(requestDTO), HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable String id, @RequestBody AuthorRequestDTO requestDTO) {
        try {
            return new ResponseEntity<>(authorService.update(id, requestDTO), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {
        try {
            authorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/byName")
    public List<AuthorDTO> findAuthorsByName(@RequestParam String name) {
        return authorService.findAuthorsByName(name);
    }

    @GetMapping("/search/byNationality")
    public List<AuthorDTO> findAuthorsByNationality(@RequestParam String nationality) {
        return authorService.findAuthorsByNationality(nationality);
    }

    @GetMapping("/customSearch")
    public List<AuthorGroup> findAuthorsByCustomCriteria(@RequestParam String namePattern,
                                                         @RequestParam String nationality) {
        return authorService.findAuthorsByCustomCriteria(namePattern, nationality);
    }
}

