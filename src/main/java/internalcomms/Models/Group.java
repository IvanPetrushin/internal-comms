package internalcomms.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private Long id;
    private String name;
    private String description;
    private List<User> users;
    private List<Task> createdTasks;
    private List<Task> executableTasks;

    public Group(Long id, String name, String description, List<Task> createdTasks, List<Task> executableTasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdTasks = createdTasks;
        this.executableTasks = executableTasks;
    }

    public Group(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Group(Long id) {
        this.id = id;
    }

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
