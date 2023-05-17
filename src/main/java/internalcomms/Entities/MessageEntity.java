package internalcomms.Entities;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import internalcomms.Models.Group;
import internalcomms.Models.Message;
import internalcomms.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = "message_entity")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "TEXT", nullable = false,columnDefinition = "TEXT")
    private String text;
    @Column(name = "TIME", nullable = false)
    private String time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_ID")
    private TaskEntity task;
    public MessageEntity(String text, String time, UserEntity user, TaskEntity task) {
        this.text = text;
        this.time = time;
        this.user = user;
        this.task = task;
    }
    public Message entityToModel() {
        return new Message(id,text,time,new User(user.getId(),user.getUsername(),new Group(user.getGroup().getId(),user.getGroup().getName())));
    }
}
