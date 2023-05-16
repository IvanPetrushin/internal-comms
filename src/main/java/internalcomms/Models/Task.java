package internalcomms.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;

import java.util.List;

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
    //todo private List<MultipartFile> ownerFiles;
    private List<Long> groupsId;
    private List<GroupForTask> groups;
    private List<Message> messages;

    public Task(Long id, String name, String description, String deadline, Integer priority, Group owner, List<GroupForTask> groups, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.owner = owner;
        this.groups = groups;
        this.messages = messages;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @TypeDef(name = "json", typeClass = JsonStringType.class)
    public static class GroupForTask {
        @JsonProperty("condition")
        private Boolean condition = false;
        @JsonProperty("group")
        private Group group;
        //todo files
    }
}
