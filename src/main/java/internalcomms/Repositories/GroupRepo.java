package internalcomms.Repositories;

import internalcomms.Entities.GroupEntity;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepo extends CrudRepository<GroupEntity, Long> {
}
