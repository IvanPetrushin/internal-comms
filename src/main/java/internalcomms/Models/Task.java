package internalcomms.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Task {
    @NotNull private Long id;
    @NotNull private String name;
    private String description;
    private String deadline;
    private Integer priority;
    private Group owner;
    private List<MultipartFile> ownerFiles;
    private Map<Group, String> files = new HashMap();
    private Map<Long, Boolean> groups = new HashMap();

    public Task(@NotNull Long id, @NotNull String name, String description, String deadline, Integer priority, Group owner, Map<Long, Boolean> groups) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.owner = owner;
        this.groups = groups;
    }
}
