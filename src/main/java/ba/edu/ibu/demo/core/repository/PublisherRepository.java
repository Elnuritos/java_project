package ba.edu.ibu.demo.core.repository;

import ba.edu.ibu.demo.core.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PublisherRepository extends MongoRepository<Publisher, String> {
}
