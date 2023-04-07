package internalcomms.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_entity")
public class TaskEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id private Long id;
    private String name;
    private String description;
    private String time;
    private String condition;
    private String priority;
    private GroupEntity group;

    public TaskEntity(String name, String description, String time, String condition, String priority, GroupEntity group) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.condition = condition;
        this.priority = priority;
        this.group = group;
    }

    public TaskEntity(String name, String description, String time, String condition, String priority) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.condition = condition;
        this.priority = priority;
    }

    @ManyToOne
    @JoinColumn(name="GROUP_ID", nullable=false)
    public GroupEntity getGroup() {
        return group;
    }
}
