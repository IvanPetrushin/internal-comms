package internalcomms.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@Entity
@Table(name = "group_entity")
public class GroupEntity {
    @NonNull
    @Id private Long id;
    private String name;
    private String description;
    private List<UserEntity> users;
    private List<TaskEntity> tasks;

    public GroupEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }


    @OneToMany(cascade=ALL, mappedBy="group")
    public List<TaskEntity> getTasks() {
        return tasks;
    }
    @OneToMany(cascade=ALL, mappedBy="group")
    public List<UserEntity> getUsers() {
        return users;
    }
}
