package internalcomms.Repositories;

import internalcomms.Entities.Attachment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttachmentRepository extends CrudRepository<Attachment, String> {
    @Query(value = "SELECT * FROM public.attachment WHERE attachment.task_id=task_id and attachment.group_id=group_id",nativeQuery = true)
    List<Attachment> findByTaskIDAndGroupID(@Param("task_id")Long t, @Param("group_id")Long g);
}
