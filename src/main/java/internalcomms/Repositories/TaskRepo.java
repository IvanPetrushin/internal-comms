package internalcomms.Repositories;

import internalcomms.Entities.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<TaskEntity, Long> {
}
