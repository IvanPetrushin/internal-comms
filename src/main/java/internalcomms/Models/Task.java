package internalcomms.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
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
    private Map<Long, Boolean> groups = new HashMap();
}
