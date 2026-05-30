package africa.semicolon.noStrings.data.repositories;


import africa.semicolon.noStrings.data.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {}
