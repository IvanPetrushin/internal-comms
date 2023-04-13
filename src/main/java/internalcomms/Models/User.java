package internalcomms.Models;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password = "";
    private String mail = "";
    private String description = "";
    private Group group = new Group();
    //private List<Question> questions;

    public User(@NonNull Long id, @NonNull String username, String password) {
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

    public User(Long id, String username, String password, String mail, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.description = description;
    }
}
