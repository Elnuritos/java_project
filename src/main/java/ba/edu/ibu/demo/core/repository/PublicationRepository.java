package ba.edu.ibu.demo.core.repository;

import ba.edu.ibu.demo.core.model.Publication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends MongoRepository<Publication, String> {

}