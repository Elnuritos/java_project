    package ba.edu.ibu.demo.core.repository;

    import ba.edu.ibu.demo.core.model.Author;

    import org.springframework.data.mongodb.repository.MongoRepository;
    import org.springframework.stereotype.Repository;



    @Repository
    public interface AuthorRepository extends MongoRepository<Author, String> {
    }

