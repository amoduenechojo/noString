package africa.semicolon.noStrings.data.repositories;

import africa.semicolon.noStrings.data.models.Hobby;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface HobbyRepository extends MongoRepository<Hobby, String> {}
