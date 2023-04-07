package internalcomms.Repositories;

import internalcomms.Entities.QuestionEntity;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepo extends CrudRepository<QuestionEntity, Long> {
}
