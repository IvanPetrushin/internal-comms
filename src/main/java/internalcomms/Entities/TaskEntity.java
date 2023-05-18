package internalcomms.Entities;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import internalcomms.Models.Group;
import internalcomms.Models.Message;
import internalcomms.Models.Task;
import internalcomms.ResponseData;
import org.springframework.web.client.RestTemplate;
import lombok.*;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = "task_entity")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "DEADLINE", nullable = false)
    private String deadline;
    @Column(name = "PRIOR", nullable = false)
    private Integer priority;
    @OneToMany(cascade=ALL, mappedBy = "task_cond")
    private List<GroupForTask> groupsForTask = new ArrayList<>(); //Группа и состояние выполнения
    @OneToMany(cascade = ALL, mappedBy = "task")
    private List<MessageEntity> messages = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="TASK_GROUP")
    private List<GroupEntity> groups = new ArrayList<>();
    public TaskEntity(String name, String description, String deadline, Integer priority) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }
    private static RestTemplate restTemplate = new RestTemplate();

    public Task entityToModel() {
        Group owner = null;
        List<ResponseData> ownerFiles = new ArrayList<>();
        List<Task.GroupForTask> groups = new ArrayList<>();
        for(var g: groupsForTask){
            GroupEntity ge = this.groups.stream().filter(x-> Objects.equals(x.getId(), g.groupID)).findFirst().get();
            List<ResponseData> rd = restTemplate.getForObject("http://localhost:8080/files/" + id+"/"+g.groupID, List.class);
            if(g.getIsHead()) {
                owner = new Group(ge.getId(), ge.getName());
                ownerFiles = rd;
            }
            else {
                groups.add(new Task.GroupForTask(g.condition, new Group(ge.getId(), ge.getName()), rd));
            }
        }
        List<Message> messages = this.messages.stream().map(MessageEntity::entityToModel).toList();
        return new Task(id,name, description, deadline,priority, owner, ownerFiles, groups, messages);
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "groupForTask")
    public static class GroupForTask {
        @Id
        @GeneratedValue(strategy=GenerationType.SEQUENCE)
        private Long id;
        @Column(name="ISHEAD")
        private Boolean isHead = false;
        @Column(name="COND")
        private Boolean condition = false;
        @Column(name="GROUP_ID")
        private Long groupID;
        @OneToMany(cascade=ALL, mappedBy="task")
        private List<Attachment> files = new ArrayList<>();
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="TASK_ID")
        private TaskEntity task_cond;
        public GroupForTask(Boolean isHead, Boolean condition, Long groupID, TaskEntity task_cond) {
            this.isHead = isHead;
            this.condition = condition;
            this.groupID = groupID;
            this.task_cond = task_cond;
        }
    }
}
