package internalcomms.Repositories;

import internalcomms.Entities.Attachment;
import org.springframework.data.repository.CrudRepository;

public interface AttachmentRepository extends CrudRepository<Attachment, String> {
}
