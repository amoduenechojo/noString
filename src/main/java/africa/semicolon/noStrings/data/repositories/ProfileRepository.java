package africa.semicolon.noStrings.data.repositories;

import africa.semicolon.noStrings.data.models.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProfileRepository extends MongoRepository<Profile, String> {}