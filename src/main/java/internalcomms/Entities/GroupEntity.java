package internalcomms.Entities;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "group_entity")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class GroupEntity {
    private Long id;
    private String name;
    private String description;
    private List<Long> users;
    private List<Long> tasks;

    public GroupEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }


    @Type(type = "json")
    @Column
    public List<Long> getTasks() {
        return tasks;
    }
    @Type(type = "json")
    @Column
    public List<Long> getUsers() {
        return users;
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
}
