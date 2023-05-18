package internalcomms.Controllers;

import internalcomms.Entities.Attachment;
import internalcomms.ResponseData;
import internalcomms.Services.AttachmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest-контроллер запросов для файлов
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/files")
public class AttachmentController {
    @Autowired
    private AttachmentServiceImpl attachmentServiceImpl;

    public AttachmentController(AttachmentServiceImpl attachmentServiceImpl) {
        this.attachmentServiceImpl = attachmentServiceImpl;
    }

    /**
     * POST-запрос на сохранение файла
     * @return Информацию о файле: Имя файла, ссылку на скачивание, тип файла, размер файлы
     */
    @PostMapping("/upload")
    public ResponseData uploadFiles(@RequestParam("file")MultipartFile file) throws Exception {
        Attachment attachment;
        String downloadURl;
        attachment = attachmentServiceImpl.saveAttachment(file);
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();

        return new ResponseData(attachment.getFileName(),
                downloadURl,
                file.getContentType(),
                file.getSize());
    }

    /**
     * POST-запрос на сохранение списка файлов для конкретного заданий и группы
     * @return Информацию о файлах: Имена файлов, ссылки на скачивание, типы файлов, размеры файлов
     */
    @PostMapping("/uploads")
    public List<ResponseData> uploadFiles(@RequestParam("files")List<MultipartFile> files, @RequestParam("taskID")Long taskID, @RequestParam("groupID")Long groupID) throws Exception {
        List<ResponseData> responseData = new ArrayList<>();
        List<Attachment> attachments = attachmentServiceImpl.saveAttachments(files, taskID, groupID);
        for (int i = 0; i < files.size(); i++) {
            String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(attachments.get(i).getId())
                    .toUriString();
            responseData.add(new ResponseData(attachments.get(i).getFileName(),
                    downloadURl,
                    files.get(i).getContentType(),
                    files.get(i).getSize()));
        }
        return responseData;
    }

    /**
     * GET-запрос на получение файла про его ID
     * @return Информацию о файле: Имя файла, ссылку на скачивание, тип файла, размер файлы
     */
    @GetMapping("/load/{fileId}")
    public ResponseData loadFile(@PathVariable String fileId) throws Exception {
        ResponseData responseData;
        Attachment attachment = attachmentServiceImpl.getAttachment(fileId);
        String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();
        responseData= new ResponseData(attachment.getFileName(),
                downloadURl,
                attachment.getFileType(),
                attachment.getData().length);

        return responseData;
    }

    /**
     * GET-запрос на получение списка файлов по ID задания и ID группы
     * @return Информацию о файлах: Имена файлов, ссылки на скачивание, типы файлов, размеры файлов
     */
    @GetMapping("/{taskID}/{groupID}")
    public List<ResponseData> loadFiles(@PathVariable Long taskID,@PathVariable Long groupID) {
        List<ResponseData> responseData = new ArrayList<>();
        List<Attachment> attachments = attachmentServiceImpl.getAttachments(taskID, groupID);
        for (var a:attachments) {
            String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(a.getId())
                    .toUriString();
            responseData.add(new ResponseData(a.getFileName(),
                    downloadURl,
                    a.getFileType(),
                    a.getData().length));
        }
        return responseData;
    }

    /**
     * Возвращает ссылку по ID файла для дальнейшего скачивания
     * @return Ссылку на скачивание
     */
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = attachmentServiceImpl.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}
