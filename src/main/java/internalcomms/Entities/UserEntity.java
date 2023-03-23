package internalcomms.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_entity")
public class UserEntity {
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    @Id private Long id;
    private String username;
    private String password;
    private String mail;
    private String role; //Нужен отдельный класс для ролей
    private String description;
    private GroupEntity group;
    private List<QuestionEntity> questions;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name="GROUP_ID", nullable=false)
    public GroupEntity getGroup() {
        return group;
    }
    @OneToMany(cascade=ALL, mappedBy="user")
    public List<QuestionEntity> getQuestions() {
        return questions;
    }
}
