package internalcomms.Services;

import internalcomms.Entities.Attachment;
import internalcomms.Entities.GroupEntity;
import internalcomms.Entities.TaskEntity;
import internalcomms.Repositories.AttachmentRepository;
import internalcomms.Repositories.GroupRepo;
import internalcomms.Repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сервис для файлов
 */
@Service
public class AttachmentServiceImpl implements AttachmentService{
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private GroupRepo groupRepo;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    /**
     * Сохранение файла
     * @return Attachment
     */
    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
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

    /**
     * Сохранение файла для конкретного задания и группы
     * @return Attachment
     */
    @Override
    public Attachment saveAttachment(MultipartFile file, TaskEntity task, GroupEntity group) throws Exception {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            Attachment attachment
                    = new Attachment(fileName,
                    file.getContentType(),
                    task,
                    group,
                    file.getBytes());
            return attachmentRepository.save(attachment);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    /**
     * Сохранение списка файлов для конкретного задания и группы
     * @return List<Attachment>
     */
    @Override
    public List<Attachment> saveAttachments(List<MultipartFile> files, Long taskID, Long groupID) throws Exception {
        TaskEntity task = taskRepo.findById(taskID).get();
        GroupEntity group = groupRepo.findById(groupID).get();
        List<Attachment> attachments = new ArrayList<>();
        for (var f:files) {
            attachments.add(saveAttachment(f, task, group));
        }
        task.getGroupsForTask().stream().filter(x-> Objects.equals(x.getGroupID(), groupID)).findFirst().get().setFiles(attachments);
        taskRepo.save(task);
        return attachments;
    }

    /**
     * Получение файла по ID
     * @return Attachment
     */
    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }

    /**
     * Получение списка файлов по ID задания и группы
     * @return List<Attachment>
     */
    @Transactional
    @Override
    public List<Attachment> getAttachments(Long taskID, Long groupID) {
        return attachmentRepository.findByTaskIDAndGroupID(taskID,groupID);
    }
}