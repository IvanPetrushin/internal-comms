package internalcomms.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Модель группы для отправки JSON файлом
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private Long id;
    private String name;
    private String description;
    private Boolean creatable;
    private List<User> users;
    private List<Task> createdTasks;
    private List<Task> executableTasks;

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(Long id, String name, String description, Boolean creatable, List<Task> createdTasks, List<Task> executableTasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatable = creatable;
        this.createdTasks = createdTasks;
        this.executableTasks = executableTasks;
    }
}
