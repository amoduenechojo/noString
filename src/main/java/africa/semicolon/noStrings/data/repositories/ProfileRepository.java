package africa.semicolon.noStrings.data.repositories;

import africa.semicolon.noStrings.data.models.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends MongoRepository<Optional, String> {}