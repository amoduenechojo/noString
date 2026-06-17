package africa.semicolon.noStrings.data.repositories;

import africa.semicolon.noStrings.data.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MessageRepository extends MongoRepository<Message, String> {}
