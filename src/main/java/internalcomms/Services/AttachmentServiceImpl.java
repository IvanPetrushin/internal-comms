package internalcomms.Services;

import internalcomms.Entities.Attachment;
import internalcomms.Entities.TaskEntity;
import internalcomms.Repositories.AttachmentRepository;
import internalcomms.Repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService{
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private TaskRepo taskRepo;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            Attachment attachment
                    = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes());
            return attachmentRepository.save(attachment);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }
    @Override
    public Attachment saveAttachment(MultipartFile file, TaskEntity task) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            Attachment attachment
                    = new Attachment(fileName,
                    file.getContentType(),
                    task,
                    file.getBytes());
            return attachmentRepository.save(attachment);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    @Override
    public List<Attachment> saveAttachments(List<MultipartFile> files, Long taskID) throws Exception {
        TaskEntity task = taskRepo.findById(taskID).get();
        List<Attachment> attachments = new ArrayList<>();
        for (var f:files) {
            attachments.add(saveAttachment(f, task));
        }

        return attachments;
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }

    @Override
    public List<Attachment> getAttachments(List<String> filesId) throws Exception {
        List<Attachment> attachments = new ArrayList<>();
        for (var f:filesId) {
            attachments.add(getAttachment(f));
        }
        return attachments;
    }

}
