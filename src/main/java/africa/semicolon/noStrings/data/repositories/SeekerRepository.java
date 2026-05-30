package africa.semicolon.noStrings.data.repositories;

import africa.semicolon.noStrings.data.models.Seeker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeekerRepository extends MongoRepository<Seeker, String>{
}









