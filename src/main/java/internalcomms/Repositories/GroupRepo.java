package internalcomms.Repositories;

import internalcomms.Entities.GroupEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GroupRepo extends CrudRepository<GroupEntity, Long> {
    @Query("SELECT g FROM GroupEntity g WHERE g.name=:name")
    GroupEntity findByName(@Param("name")String g);
}
