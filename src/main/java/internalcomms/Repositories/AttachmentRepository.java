package internalcomms.Repositories;

import internalcomms.Entities.Attachment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttachmentRepository extends CrudRepository<Attachment, String> {
    /**
     * Берет из базы данных файлы конкретного задания и группы
     * @return List<Attachment>
     */
    @Query(value = "SELECT * FROM public.attachment a WHERE a.task_id= :t and a.group_id= :g", nativeQuery = true)
    List<Attachment> findByTaskIDAndGroupID(Long t, Long g);
}
