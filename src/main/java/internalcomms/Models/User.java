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
    private String role; //Нужен отдельный класс для ролей
    private String description;
    private Group group;
    private List<Question> questions;

    public User(@NonNull Long id, String username, String password, List<Question> questions) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.questions = questions;
    }

    public User(@NonNull Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(@NonNull Long id, @NonNull String username, String password, String mail, String role, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.description = description;
    }
}
