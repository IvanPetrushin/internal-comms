package internalcomms.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Group {
    @NonNull private Long id;
    @NonNull private String name;
    private String description;
    private List<User> users;
    private List<Task> tasks;


}
