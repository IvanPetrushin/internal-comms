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
    private Long id;
    private String username;
    private String password;
    private String mail;
    private String description;
    private Long group;
    private List<QuestionEntity> questions;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity(String username, String password, String mail, Long group) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.group = group;
    }

    @Column
    public Long getGroup() {
        return group;
    }
    @OneToMany(cascade=ALL, mappedBy="user")
    public List<QuestionEntity> getQuestions() {
        return questions;
    }
    @Column
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }
    @Column
    public String getUsername() {
        return username;
    }
    @Column
    public String getPassword() {
        return password;
    }
    @Column
    public String getMail() {
        return mail;
    }
    @Column
    public String getDescription() {
        return description;
    }
}
