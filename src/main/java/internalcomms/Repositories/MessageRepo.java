package internalcomms.Repositories;

import internalcomms.Entities.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<MessageEntity, Long> {
}
