package internalcomms.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data@NoArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;
    private String fileType;
    @ManyToOne
    @JoinColumn(name="TASK_ID")
    private TaskEntity task;
    @ManyToOne
    @JoinColumn(name="GROUP_ID")
    private GroupEntity group;
    @Lob
    private byte[] data;

    public Attachment(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }


    public Attachment(String fileName, String fileType, TaskEntity task, GroupEntity group, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.task = task;
        this.group = group;
        this.data = data;
    }
}
