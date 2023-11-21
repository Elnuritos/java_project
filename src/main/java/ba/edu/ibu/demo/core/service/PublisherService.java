package ba.edu.ibu.demo.core.service;

import ba.edu.ibu.demo.core.api.mailsender.EmailService;
import ba.edu.ibu.demo.core.exceptions.general.NotFoundException;
import ba.edu.ibu.demo.core.model.Publisher;
import ba.edu.ibu.demo.core.repository.PublisherRepository;
import ba.edu.ibu.demo.rest.dto.PublisherDTO;
import ba.edu.ibu.demo.rest.dto.PublisherRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;
    @Autowired
    private EmailService apiSender;

    @Autowired
    private EmailService smtpSender;


    // @Autowired
    // private EmailService emailSender;
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<PublisherDTO> findAll() {
        return publisherRepository.findAll().stream()
                .map(PublisherDTO::new)
                .collect(Collectors.toList());
    }

    public PublisherDTO findById(String id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Publisher not found"));
        return new PublisherDTO(publisher);
    }

    public PublisherDTO save(PublisherRequestDTO requestDTO) {
        Publisher publisher = requestDTO.toEntity();
        Publisher savedPublisher = publisherRepository.save(publisher);
        return new PublisherDTO(savedPublisher);
    }

    public PublisherDTO update(String id, PublisherRequestDTO requestDTO) {
        if (!publisherRepository.existsById(id)) {
            throw new NotFoundException("Publisher not found");
        }

        Publisher publisher = requestDTO.toEntity();
        publisher.setId(id);
        Publisher updatedPublisher = publisherRepository.save(publisher);
        return new PublisherDTO(updatedPublisher);
    }

    public void delete(String id) {
        if (!publisherRepository.existsById(id)) {
            throw new NotFoundException("Publisher not found");
        }
        publisherRepository.deleteById(id);
    }

    public String sendEmailToAllUsers(String message) {
        List<Publisher> publishers = publisherRepository.findAll();

       // return apiSender.send(publishers, message);
         return smtpSender.send(publishers, message);

        // Method 2: The appropriate implementation is decided based on configuration
        // return emailSender.send(publishers, message);
    }
}
