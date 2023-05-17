package internalcomms.Entities;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import internalcomms.Models.Group;
import internalcomms.Models.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = "group_entity")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION",columnDefinition = "TEXT")
    private String description;
    @Column(name = "CREAT")
    private Boolean creatable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<UserEntity> users = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    private List<TaskEntity> tasks = new ArrayList<>();
    public GroupEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Group entityToModel(){
        List<Task> createdTasks = new ArrayList<>();
        List<Task> executableTasks = new ArrayList<>();
        for (var t:tasks) {
            if(t.getGroupsForTask().stream().filter(x->x.getGroupID()==id).findFirst().get().getIsHead()) {
                createdTasks.add(t.entityToModel());
            }else{
                executableTasks.add(t.entityToModel());
            }
        }
        return new Group(id,name,description,creatable,createdTasks,executableTasks);
    }
}
