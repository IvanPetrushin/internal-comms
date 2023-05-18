package internalcomms.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import internalcomms.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель задания для отправки JSON файлом
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String name;
    private String description;
    private String deadline;
    private Integer priority;
    private Group owner;
    private List<ResponseData> ownerFiles = new ArrayList<>();
    private List<Long> groupsId;
    private List<GroupForTask> groups;
    private List<Message> messages;
    public Task(Long id, String name, String description, String deadline, Integer priority, Group owner, List<ResponseData> ownerFiles, List<GroupForTask> groups, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.owner = owner;
        this.ownerFiles = ownerFiles;
        this.groups = groups;
        this.messages = messages;
    }

    public Task(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Класс для хранения группы, состояния её выполнения задания и её файлов для данного задания
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @TypeDef(name = "json", typeClass = JsonStringType.class)
    public static class GroupForTask {
        @JsonProperty("condition")
        private Boolean condition = false;
        @JsonProperty("group")
        private Group group;
        @JsonProperty("files")
        private List<ResponseData> files = new ArrayList<>();
    }
}
