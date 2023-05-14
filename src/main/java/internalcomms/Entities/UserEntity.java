package internalcomms.Entities;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import internalcomms.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = "user_entity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String username;
    @Column(name = "PASS", nullable = false)
    private String password;
    @Column(name = "MAIL", nullable = false)
    private String mail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    private GroupEntity group;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<MessageEntity> messages = new ArrayList<>();
    public UserEntity(String username, String password, String mail, GroupEntity group) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.group = group;
    }
    public User entityToModel(){
        return new User(id, username, password, mail, group.entityToModel());
    }
}
