package internalcomms.Repositories;

import internalcomms.Entities.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskCondRepo extends CrudRepository<TaskEntity.GroupForTask, Long> {
}
