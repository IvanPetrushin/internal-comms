package internalcomms.Entities;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = "task_entity")
public class TaskEntity {
    private Long id;
    private String name;
    private String description;
    private String deadline;
    private Integer priority;
    private Long owner;
    private List<String> ownerFiles;//файлы заказчика
    private Map<Long, String> files;//файлы исполнителей(мапа)
    private Map<Long, Boolean> groups = new HashMap(); //Группа и состояние выполнения

    public TaskEntity(String name, String description, String deadline, Integer priority, Long owner, Map<Long, Boolean> groups) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.owner = owner;
        this.groups = groups;
    }

    public TaskEntity(String name, String description, String deadline, Integer priority, Long owner) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.owner = owner;
    }

    @Column
    public Long getOwner() {
        return owner;
    }
    @Column
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }
    @Column
    public String getName() {
        return name;
    }
    @Column
    public String getDescription() {
        return description;
    }
    @Column
    public String getDeadline() {
        return deadline;
    }
    @Column
    public Integer getPriority() {
        return priority;
    }
    @Type(type = "json")
    @Column
    public Map<Long, Boolean> getGroups() {
        return groups;
    }
    @Type(type = "json")
    @Column
    public List<String> getOwnerFiles() {
        return ownerFiles;
    }
    @Type(type = "json")
    @Column
    public Map<Long, String> getFiles() {
        return files;
    }
}
