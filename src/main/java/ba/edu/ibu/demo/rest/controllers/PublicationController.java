package ba.edu.ibu.demo.rest.controllers;

import ba.edu.ibu.demo.core.exceptions.general.NotFoundException;
import ba.edu.ibu.demo.core.service.PublicationService;
import ba.edu.ibu.demo.rest.dto.PublicationDTO;
import ba.edu.ibu.demo.rest.dto.PublicationRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {


    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping
    public ResponseEntity<List<PublicationDTO>> getAllPublications() {
        return new ResponseEntity<>(publicationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getPublicationById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(publicationService.findById(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PublicationDTO> createPublication(@RequestBody PublicationRequestDTO requestDTO) {
        return new ResponseEntity<>(publicationService.save(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationDTO> updatePublication(@PathVariable String id, @RequestBody PublicationRequestDTO requestDTO) {
        try {
            return new ResponseEntity<>(publicationService.update(id, requestDTO), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable String id) {
        try {
            publicationService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
