package internalcomms.Models;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Group {
    @NonNull private Long id;
    @NonNull private String name;
    private String description;
    private List<Long> users;
    private List<Long> tasks;
}
