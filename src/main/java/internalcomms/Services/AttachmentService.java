package internalcomms.Services;

import internalcomms.Entities.Attachment;
import internalcomms.Entities.TaskEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {

    Attachment saveAttachment(MultipartFile file, TaskEntity task) throws Exception;

    List<Attachment> saveAttachments(List<MultipartFile> files, Long taskID) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;

    List<Attachment> getAttachments(List<String> filesId) throws Exception;

    Attachment saveAttachment(MultipartFile file) throws Exception;
}
