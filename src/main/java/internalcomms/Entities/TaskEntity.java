package internalcomms.Entities;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import internalcomms.Models.Group;
import internalcomms.Models.Message;
import internalcomms.Models.Task;
import lombok.*;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "DESCRIPTION", nullable = false)
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

    public Task entityToModel() {
        Group owner = null;
        List<Task.GroupForTask> groups = new ArrayList<>();
        for(var g: groupsForTask){
            GroupEntity ge = this.groups.stream().filter(x->x.getId()==g.groupID).findFirst().get();
            if(g.getIsHead()) owner = new Group(ge.getId(),ge.getName());
            else groups.add(new Task.GroupForTask(g.condition,new Group(ge.getId(),ge.getName())));
        }
        List<Message> messages = this.messages.stream().map(x->x.entityToModel()).toList();
        return new Task(id,name, description, deadline,priority, owner, groups, messages);
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
        //todo files
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
