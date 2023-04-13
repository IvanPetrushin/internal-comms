package internalcomms.Models;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @NonNull private Long id;
    @NonNull private String username;
    private String password;
    private String mail;
    private String description;
    private Group group;
    private List<Question> questions;

    public User(@NonNull Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(@NonNull Long id, @NonNull String username, String password, String mail, Group group) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.group = group;
    }
}
