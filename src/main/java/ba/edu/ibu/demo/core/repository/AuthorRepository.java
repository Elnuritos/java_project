    package ba.edu.ibu.demo.core.repository;

    import ba.edu.ibu.demo.core.model.Author;

    import ba.edu.ibu.demo.core.model.AuthorGroup;
    import org.springframework.data.mongodb.repository.Aggregation;
    import org.springframework.data.mongodb.repository.MongoRepository;
    import org.springframework.stereotype.Repository;

    import java.util.List;


    @Repository
    public interface AuthorRepository extends MongoRepository<Author, String> {
        @Aggregation(pipeline = {
                "{ $match: { name: { $regex: ?0 }, nationality: ?1 } }",
                "{ $group: { _id: '$nationality', authors: { $push: '$$ROOT' } } }"
        })
        List<AuthorGroup> findAuthorsByCustomCriteria(String namePattern, String nationality);
        List<Author> findByName(String name);
        List<Author> findByNationality(String nationality);
    }

