package ba.edu.ibu.demo.core.service;

import ba.edu.ibu.demo.core.exceptions.general.NotFoundException;
import ba.edu.ibu.demo.core.model.Publication;
import ba.edu.ibu.demo.core.repository.PublicationRepository;
import ba.edu.ibu.demo.rest.dto.PublicationDTO;
import ba.edu.ibu.demo.rest.dto.PublicationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    public List<PublicationDTO> findAll() {
        return publicationRepository.findAll().stream()
                .map(PublicationDTO::new)
                .collect(Collectors.toList());
    }

    public PublicationDTO findById(String id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Publication not found"));
        return new PublicationDTO(publication);
    }

    public PublicationDTO save(PublicationRequestDTO requestDTO) {
        Publication publication = requestDTO.toEntity();
        return new PublicationDTO(publicationRepository.save(publication));
    }

    public PublicationDTO update(String id, PublicationRequestDTO requestDTO) {
        if (!publicationRepository.existsById(id)) {
            throw new NotFoundException("Publication not found");
        }
        Publication publication = requestDTO.toEntity();
        publication.setId(id);
        return new PublicationDTO(publicationRepository.save(publication));
    }

    public void delete(String id) {
        if (!publicationRepository.existsById(id)) {
            throw new NotFoundException("Publication not found");
        }
        publicationRepository.deleteById(id);
    }

}
