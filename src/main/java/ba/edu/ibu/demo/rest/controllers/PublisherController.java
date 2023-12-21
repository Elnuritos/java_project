package ba.edu.ibu.demo.rest.controllers;

import ba.edu.ibu.demo.core.exceptions.general.BadRequestException;
import ba.edu.ibu.demo.core.exceptions.general.NotFoundException;
import ba.edu.ibu.demo.core.service.PublisherService;
import ba.edu.ibu.demo.rest.dto.PublisherDTO;
import ba.edu.ibu.demo.rest.dto.PublisherRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAllPublishers() {
        return new ResponseEntity<>(publisherService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(publisherService.findById(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PublisherDTO> createPublisher(@RequestBody PublisherRequestDTO requestDTO) {
        try {
            return new ResponseEntity<>(publisherService.save(requestDTO), HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO> updatePublisher(@PathVariable String id, @RequestBody PublisherRequestDTO requestDTO) {
        try {
            return new ResponseEntity<>(publisherService.update(id, requestDTO), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable String id) {
        try {
            publisherService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/send-to-all")
    public String sendEmailToAllUsers(@RequestParam String message) {
        return publisherService.sendEmailToAllUsers(message);
    }
}
